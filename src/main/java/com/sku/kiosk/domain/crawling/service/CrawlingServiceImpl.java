/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.crawling.service;

import static java.lang.Double.parseDouble;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.webp.WebpWriter;
import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.repository.EventRepository;
import com.sku.kiosk.domain.event.service.EventService;
import com.sku.kiosk.global.s3.entity.PathName;
import com.sku.kiosk.global.s3.service.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingServiceImpl implements CrawlingService {

  private final EventRepository eventRepository;
  private final EventService eventService;
  private final S3Service s3Service;

  @Value("${crawling.openapi.url}")
  private String requestUrl;

  @Value("${crawling.authentication.key}")
  private String authenticationKey;

  @Value("${crawling.data.type}")
  private String dataType;

  @Value("${crawling.data.service}")
  private String dataService;

  private final Integer startIndex = 1;
  private final Integer endIndex = 400;
  private final String codeName = "%20";
  private final String title = "%20";
  private final YearMonth date = YearMonth.now();

  @Override
  @Transactional
  public void updateCrawlingEvent() {
    long startCrawlingTime = System.currentTimeMillis();
    log.info("[Crawling] Start crawling cultural events");

    List<Event> crawledEvents = crawlingEvents();
    if (crawledEvents.isEmpty()) {
      log.warn("[Crawling] No events found (0 rows)");
      return;
    }

    Map<Long, Event> dbMap =
        eventRepository.findAll().stream().collect(Collectors.toMap(Event::getCultCode, e -> e));

    List<Event> toSave = new ArrayList<>();

    int unchangedSize = 0;
    int newSize = 0;
    int changedSize = 0;
    for (Event newEvent : crawledEvents) {
      Event oldEvent = dbMap.get(newEvent.getCultCode());

      if (oldEvent == null) {
        long startS3UploadTime = System.currentTimeMillis();
        byte[] output = convertToWebp(newEvent.getOriginImageUrl());
        String fileType = "webp";
        String s3ImageUrl =
            s3Service.uploadWebp(
                output, s3Service.createKeyName(PathName.MAIN, fileType), fileType);
        newEvent.updateMainImage(s3ImageUrl);
        toSave.add(newEvent);
        long endS3UploadTime = System.currentTimeMillis();
        newSize += 1;
        log.info("[Crawling] s3 업로드 시간: {}", (endS3UploadTime - startS3UploadTime));
        continue;
      }

      if (!oldEvent.isSameContent(newEvent)) {
        oldEvent.updateFrom(newEvent);
        toSave.add(oldEvent);
        changedSize += 1;
        continue;
      }
      unchangedSize += 1;
    }

    if (!toSave.isEmpty()) eventRepository.saveAll(toSave);

    long endCrawlingTime = System.currentTimeMillis();
    log.info(
        "[Crawling] Finish crawling cultural events. unchangedSize: {}, newSize: {}, changedSize: {}, totalTime: {} ms",
        unchangedSize,
        newSize,
        changedSize,
        (endCrawlingTime - startCrawlingTime));
  }

  private List<Event> crawlingEvents() {
    try {
      String url =
          requestUrl
              + "/"
              + authenticationKey
              + "/"
              + dataType
              + "/"
              + dataService
              + "/"
              + startIndex
              + "/"
              + endIndex
              + "/"
              + codeName
              + "/"
              + title
              + "/"
              + date;

      log.info("[Crawling] events url: {}", url);

      RestTemplate restTemplate = new RestTemplate();
      String responseXml = restTemplate.getForObject(url, String.class);

      if (responseXml == null || responseXml.isBlank()) {
        log.error("[Crawling] events response xml is null or empty");
        return List.of();
      }

      Document document = Jsoup.parse(responseXml, "", Parser.xmlParser());
      Elements rows = document.select("row");

      List<Event> resultEvents = new ArrayList<>();

      for (Element row : rows) {
        if (!getText(row, "GUNAME").equals("종로구")) continue;

        Event event =
            Event.builder()
                .cultCode(extractCultcode(getText(row, "HMPG_ADDR")))
                .eventClassification(eventService.initClassification(getText(row, "CODENAME")))
                .gu(getText(row, "GUNAME"))
                .title(getText(row, "TITLE"))
                .location(getText(row, "PLACE"))
                .orgName(getText(row, "ORG_NAME"))
                .recruitTarget(getText(row, "USE_TRGT"))
                .price(getText(row, "USE_FEE"))
                .inquiry(getText(row, "INQUIRY"))
                .description(getText(row, "PROGRAM"))
                .orgLink(getText(row, "ORG_LINK"))
                .originImageUrl(getText(row, "MAIN_IMG"))
                .startDate(toLocalDate(getText(row, "STRTDATE")))
                .endDate(toLocalDate(getText(row, "END_DATE")))
                .latitude(parseDouble(getText(row, "LAT")))
                .longitude(parseDouble(getText(row, "LOT")))
                .isFree(getText(row, "IS_FREE").equals("무료"))
                .eventTime(getText(row, "PRO_TIME"))
                .eventCategory(eventService.initCategory(getText(row, "CODENAME")))
                .build();

        resultEvents.add(event);
      }

      log.info("[Crawling] Parsed {} rows", resultEvents.size());
      return resultEvents;
    } catch (Exception e) {
      log.error("[Crawling] error", e);
      return List.of();
    }
  }

  private String getText(Element row, String tag) {
    Element e = row.selectFirst(tag);
    return e != null ? e.text() : "";
  }

  private LocalDate toLocalDate(String text) {
    if (text == null || text.isBlank()) return null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    return LocalDateTime.parse(text, formatter).toLocalDate();
  }

  private Long extractCultcode(String url) {
    if (url == null || url.isBlank()) return null;

    Pattern pattern = Pattern.compile("cultcode=([^&]+)");
    Matcher matcher = pattern.matcher(url);

    if (matcher.find()) {
      return Long.parseLong(matcher.group(1)); // cultcode 값만 반환
    }

    return null;
  }

  private byte[] convertToWebp(String imageUrl) {
    try {
      HttpClient client =
          HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(imageUrl)).GET().build();
      HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

      byte[] imgBytes = response.body();
      if (imgBytes == null || imgBytes.length == 0) {
        throw new IOException("이미지 다운로드 실패 (0 byte)");
      }

      ImmutableImage image = ImmutableImage.loader().fromBytes(imgBytes);
      WebpWriter writer = WebpWriter.DEFAULT;

      return image.bytes(writer);

    } catch (Exception e) {
      log.error("[Crawling] 이미지 불러오기 중 오류 발생", e);
      return new byte[0];
    }
  }
}
