/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MainEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperMainEventResponse;
import com.sku.kiosk.domain.event.entity.*;
import com.sku.kiosk.domain.event.exception.EventErrorCode;
import com.sku.kiosk.domain.event.mapper.EventMapper;
import com.sku.kiosk.domain.event.repository.EventRepository;
import com.sku.kiosk.global.exception.CustomException;
import com.sku.kiosk.global.page.mapper.PageMapper;
import com.sku.kiosk.global.page.response.PageResponse;
import com.sku.kiosk.global.s3.service.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final PageMapper pageMapper;
  private final EventMapper eventMapper;
  private final S3Service s3Service;

  @Override
  @Transactional(readOnly = true)
  public List<WrapperMainEventResponse<MainEventResponse>> getMainEventList() {

    List<Event> exhibitionEvents =
        eventRepository.findTop3ByEventCategoryOrderByEndDateAsc(EventCategory.EXHIBITION);
    List<Event> showEvents =
        eventRepository.findTop3ByEventCategoryOrderByEndDateAsc(EventCategory.SHOW);
    List<Event> festivalEvents =
        eventRepository.findTop3ByEventCategoryOrderByEndDateAsc(EventCategory.FESTIVAL);

    List<WrapperMainEventResponse<MainEventResponse>> result = new ArrayList<>();

    result.add(eventMapper.toWrapperMainEventResponse(exhibitionEvents));
    result.add(eventMapper.toWrapperMainEventResponse(showEvents));
    result.add(eventMapper.toWrapperMainEventResponse(festivalEvents));

    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public PageResponse<ListEventResponse> getEventList(
      EventCategory eventCategory, EventPeriod eventPeriod, String keyword, Pageable pageable) {

    Page<ListEventResponse> page =
        toPageListEvent(eventCategory, eventPeriod, keyword, pageable)
            .map(eventMapper::toListEventResponse);

    log.info("event 전체보기가 성공적으로 조회되었습니다.");
    return pageMapper.toPageListEventResponse(page);
  }

  @Override
  @Transactional(readOnly = true)
  public DetailEventResponse getEventDetail(Long eventId) {
    Event event =
        eventRepository
            .findById(eventId)
            .orElseThrow(
                () -> {
                  log.error("{}번 이벤트를 찾을 수 없습니다.", eventId);
                  return new CustomException(EventErrorCode.EVENT_NOT_FOUND);
                });
    log.info("{}번 이벤트를 성공적으로 조회했습니다.", eventId);
    return eventMapper.toDetailResponse(event);
  }

  @Override
  @Transactional
  public DetailEventResponse createEvent(
      CreateEventRequest createEventRequest, EventCategory eventCategory) {
    Event event = toCreateEvent(createEventRequest, eventCategory);
    eventRepository.save(event);

    log.info("{}번 이벤트가 성공적으로 생성되었습니다", event.getId());
    return eventMapper.toDetailResponse(event);
  }

  @Override
  @Transactional
  public DetailEventResponse updateEvent(
      Long eventId, UpdateEventRequest updateEventRequest, EventCategory eventCategory) {
    Event event =
        eventRepository
            .findById(eventId)
            .orElseThrow(
                () -> {
                  log.error("{}번 이벤트가 존재하지 않아 수정할 수 없습니다.", eventId);
                  return new CustomException(EventErrorCode.EVENT_NOT_FOUND);
                });

    event.updateEvent(updateEventRequest, eventCategory);
    log.info("{}번 이벤트가 성공적으로 수정됐습니다.", event.getId());
    return eventMapper.toDetailResponse(event);
  }

  @Override
  @Transactional
  public void deleteEvent(Long eventId) {
    if (!eventRepository.existsById(eventId)) {
      log.error("{}번 이벤트가 존재하지 않아 삭제할 수 없습니다.", eventId);
      throw new CustomException(EventErrorCode.EVENT_NOT_FOUND);
    }
    log.info("{}번 이벤트가 성공적으로 삭제됐습니다.", eventId);
    eventRepository.deleteById(eventId);
  }

  @Override
  @Transactional
  public void schedulingSoftDeleteEvent() {
    LocalDate now = LocalDate.now();
    long softDeletedCount =
        eventRepository.findAllByStatus(Status.ONGOING).stream()
            .filter(event -> event.getEndDate().isBefore(now))
            .peek(event -> event.updateStatus(Status.ENDED))
            .count();
    log.info("총 {}개의 지난 이벤트 soft delete 완료.", softDeletedCount);
  }

  @Override
  @Transactional
  public void schedulingHardDeleteEvent() {
    List<Event> endedEvents = eventRepository.findAllByStatus(Status.ENDED);
    endedEvents.forEach(
        event -> {
          s3Service.deleteFile(s3Service.extractKetNameFromUrl(event.getMainImage()));
          eventRepository.delete(event);
        });
    log.info("총 {}개의 soft deleted 이벤트 hard delete 완료.", endedEvents.size());
  }

  private Event toCreateEvent(CreateEventRequest createEventRequest, EventCategory eventCategory) {
    return Event.builder()
        .title(createEventRequest.getTitle())
        .location(createEventRequest.getLocation())
        .startDate(createEventRequest.getStartDate())
        .endDate(createEventRequest.getEndDate())
        .eventTime(createEventRequest.getEventTime())
        .recruitTarget(createEventRequest.getRecruitTarget())
        .price(createEventRequest.getPrice())
        .inquiry(createEventRequest.getInquiry())
        .mainImage(createEventRequest.getMainImage())
        .latitude(createEventRequest.getLatitude())
        .longitude(createEventRequest.getLongitude())
        .eventCategory(eventCategory)
        .build();
  }

  private Page<Event> toPageListEvent(
      EventCategory eventCategory, EventPeriod eventPeriod, String keyword, Pageable pageable) {

    Page<Event> page = null;
    if (keyword == null) keyword = "";

    LocalDate today = LocalDate.now();
    LocalDate compareWithStartDate = null;
    LocalDate compareWithEndDate = null;

    switch (eventPeriod) {
      case EventPeriod.ALL:
        compareWithStartDate = today.withDayOfYear(today.lengthOfYear());
        compareWithEndDate = today.withDayOfYear(1);
        break;
      case EventPeriod.TODAY:
        compareWithStartDate = today;
        compareWithEndDate = today;
        break;
      case EventPeriod.THIS_WEEK:
        compareWithStartDate = today.with(DayOfWeek.SUNDAY);
        compareWithEndDate = today.with(DayOfWeek.MONDAY);
        break;
      case EventPeriod.THIS_MONTH:
        compareWithStartDate = today.withDayOfMonth(today.lengthOfMonth());
        compareWithEndDate = today.withDayOfMonth(1);
        break;
      default:
        break;
    }

    if (EventCategory.ALL == eventCategory) {
      page =
          eventRepository
              .findByTitleContainingAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusOrderByEndDateAsc(
                  keyword, compareWithStartDate, compareWithEndDate, Status.ONGOING, pageable);
    } else {
      page =
          eventRepository
              .findByEventCategoryAndTitleContainingAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusOrderByEndDateAsc(
                  eventCategory,
                  keyword,
                  compareWithStartDate,
                  compareWithEndDate,
                  Status.ONGOING,
                  pageable);
    }
    return page;
  }

  @Override
  public EventClassification initClassification(String classification) {

    if (classification == null) return EventClassification.ETC;

    classification = classification.trim();

    return switch (classification) {
      case "교육/체험" -> EventClassification.EDU_EXPERIENCE;
      case "국악" -> EventClassification.KOREAN_TRADITIONAL;
      case "기타" -> EventClassification.ETC;
      case "독주/독창회" -> EventClassification.RECITAL;
      case "무용" -> EventClassification.DANCE;
      case "뮤지컬/오페라" -> EventClassification.MUSICAL_OPERA;
      case "연극" -> EventClassification.PLAY;
      case "영화" -> EventClassification.MOVIE;
      case "전시/미술" -> EventClassification.EXHIBITION_ART;
      case "축제-기타" -> EventClassification.FEST_ETC;
      case "축제-문화/예술" -> EventClassification.FEST_ART;
      case "축제-시민화합" -> EventClassification.FEST_CIVIC;
      case "축제-자연/경관" -> EventClassification.FEST_NATURE;
      case "축제-전통/역사" -> EventClassification.FEST_HISTORY;
      case "콘서트" -> EventClassification.CONCERT;
      case "클래식" -> EventClassification.CLASSICAL;
      default -> EventClassification.ETC;
    };
  }

  @Override
  public EventCategory initCategory(String classification) {
    if (classification == null) return EventCategory.ETC;
    classification = classification.trim();

    return switch (classification) {
      case "교육/체험" -> EventCategory.EDUEXP;
      case "영화", "기타" -> EventCategory.ETC;
      case "국악", "독주/독창회", "무용", "뮤지컬/오페라", "연극", "콘서트", "클래식" -> EventCategory.SHOW;
      case "축제-기타", "축제-문화/예술", "축제-시민화합", "축제-자연/경관", "축제-전통/역사" -> EventCategory.FESTIVAL;
      case "전시/미술" -> EventCategory.EXHIBITION;
      default -> EventCategory.ETC;
    };
  }
}
