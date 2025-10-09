/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.EventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.exception.EventErrorCode;
import com.sku.kiosk.domain.event.repository.EventRepository;
import com.sku.kiosk.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

  private final EventRepository eventRepository;

  // -------------------------조회-----------------------------
  // 전체보기
  @Transactional(readOnly = true)
  public List<ListEventResponse> getAllEvents() {
    List<Event> eventList = eventRepository.findAll();

    List<ListEventResponse> result = new ArrayList<>();
    for (Event e : eventList) {
      result.add(toListEventResponse(e));
    }
    log.info("event 전체보기가 성공적으로 조회되었습니다.");
    return result;
  }

  // 카테고리별 조회
  @Transactional(readOnly = true)
  public List<ListEventResponse> getEventsByEventCategory(EventCategory eventCategory) {
    List<Event> eventList = eventRepository.findByEventCategory(eventCategory);
    List<ListEventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toListEventResponse(e));
    }
    log.info("{} 카테고리가 성공적으로 조회되었습니다.", eventCategory.name());
    return result;
  }

  // title로 검색
  @Transactional(readOnly = true)
  public List<EventResponse> searchEvents(String title) {
    List<Event> eventList = eventRepository.findByTitleContainingIgnoreCase(title);
    List<EventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toDetailResponse(e));
    }
    log.info("검색이 성공적으로...흠 ");
    return result;
  }

  // detail 페이지 조회
  @Transactional(readOnly = true)
  public EventResponse getEventDetail(Long id) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  log.error("{}번 이벤트를 찾을 수 없습니다.", id);
                  return new CustomException(EventErrorCode.EVENT_NOT_FOUND);
                });
    log.info("{}번 이벤트를 성공적으로 조회했습니다.", id);
    return toDetailResponse(event);
  }

  // ---------------------------CRUD---------------------------------

  @Transactional
  public EventResponse createEvent(
      CreateEventRequest createEventRequest, EventCategory eventCategory) {
    Event event = toCreateEvent(createEventRequest, eventCategory);
    eventRepository.save(event);

    log.info(event.getId() + "번 이벤트가 성공적으로 생성되었습니다");
    return toDetailResponse(event);
  }

  @Transactional
  public EventResponse updateEvent(
      Long id, UpdateEventRequest updateEventRequest, EventCategory eventCategory) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  log.error(id + "번 이벤트가 존재하지 않습니다.");
                  return new CustomException(EventErrorCode.EVENT_NOT_FOUND);
                });

    event.updateEvent(updateEventRequest, eventCategory);
    log.info("{}번 이벤트가 성공적으로 수정됐습니다.", event.getId());
    return toDetailResponse(event);
  }

  public void deleteEvent(Long id) {
    if (!eventRepository.existsById(id)) {
      log.error("{}번 이벤트가 존재하지 않습니다.", id);
      throw new CustomException(EventErrorCode.EVENT_NOT_FOUND);
    }
    log.info("{}번 이벤트가 성공적으로 삭제됐습니다.", id);
    eventRepository.deleteById(id);
  }

  private Event toCreateEvent(CreateEventRequest createEventRequest, EventCategory eventCategory) {
    return Event.builder()
        .title(createEventRequest.getTitle())
        .cultCode(createEventRequest.getCultCode())
        .location(createEventRequest.getLocation())
        .startDate(createEventRequest.getStartDate())
        .endDate(createEventRequest.getEndDate())
        .eventTime(createEventRequest.getEventTime())
        .recruitTarget(createEventRequest.getRecruitTarget())
        .price(createEventRequest.getPrice())
        .inquiry(createEventRequest.getInquiry())
        .mainImage(createEventRequest.getMainImage())
        .address(createEventRequest.getAddress())
        .latitude(createEventRequest.getLatitude())
        .longitude(createEventRequest.getLongitude())
        .eventCategory(eventCategory)
        .build();
  }

  // ------------------------DTO Response------------------------------
  // List 패이지 반환값
  private ListEventResponse toListEventResponse(Event event) {
    ListEventResponse listEventResponse =
        ListEventResponse.builder()
            .title(event.getTitle())
            .location(event.getLocation())
            .startDate(event.getStartDate())
            .endDate(event.getEndDate())
            .mainImage(event.getMainImage())
            .build();
    return listEventResponse;
  }

  // detail 페이지 반환값
  private EventResponse toDetailResponse(Event event) {
    EventResponse eventResponse =
        EventResponse.builder()
            .id(event.getId())
            .cultCode(event.getCultCode())
            .title(event.getTitle())
            .location(event.getLocation())
            .startDate(event.getStartDate())
            .endDate(event.getEndDate())
            .eventCategory(event.getEventCategory())
            .mainImage(event.getMainImage())
            .price(event.getPrice())
            .address(event.getAddress())
            .inquiry(event.getInquiry())
            .latitude(event.getLatitude())
            .longitude(event.getLongitude())
            .status(event.getStatus())
            .build();
    return eventResponse;
  }
}
