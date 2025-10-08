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
import com.sku.kiosk.domain.event.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;

  // -------------------------조회-----------------------------
  // 전체보기
  @Transactional
  public List<ListEventResponse> getAllEvents() {
    List<Event> eventList = eventRepository.findAll();

    List<ListEventResponse> result = new ArrayList<>();
    for (Event e : eventList) {
      result.add(toListEventResponse(e));
    }
    return result;
  }

  // 카테고리별 조회
  @Transactional
  public List<ListEventResponse> getEventsByEventCategory(EventCategory eventCategory) {
    List<Event> eventList = eventRepository.findByEventCategory(eventCategory);
    List<ListEventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toListEventResponse(e));
    }
    return result;
  }

  // title로 검색
  @Transactional
  public List<EventResponse> searchEvents(String title) {
    List<Event> eventList = eventRepository.findByTitleContainingIgnoreCase(title);
    List<EventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      // result.add(toListEventResponse(e));
    }
    return result;
  }

  // detail 페이지 조회
  @Transactional
  public EventResponse getEventDetail(Long id) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
    return toDetailResponse(event);
  }

  // ---------------------------CRUD---------------------------------

  @Transactional
  public EventResponse createEvent(
      CreateEventRequest createEventRequest, EventCategory eventCategory) {
    Event event = toCreateEvent(createEventRequest, eventCategory);
    eventRepository.save(event);
    return toDetailResponse(event);
  }

  @Transactional
  public EventResponse updateEvent(
      Long id, UpdateEventRequest updateEventRequest, EventCategory eventCategory) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));

    toUpdateEvent(event, updateEventRequest, eventCategory);
    eventRepository.save(event);
    return toDetailResponse(event);
  }

  public void deleteEvent(Long id) {
    if (!eventRepository.existsById(id)) {
      throw new IllegalArgumentException("삭제할 이벤트를 찾을 수 없습니다.");
    }
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

  private void toUpdateEvent(
      Event event, UpdateEventRequest updateEventRequest, EventCategory eventCategory) {

    event.setTitle(updateEventRequest.getTitle());
    event.setLocation(updateEventRequest.getLocation());
    event.setEventCategory(eventCategory);
    event.setStartDate(updateEventRequest.getStartDate());
    event.setEndDate(updateEventRequest.getEndDate());
    event.setPrice(updateEventRequest.getPrice());
    event.setInquiry(updateEventRequest.getInquiry());
    event.setAddress(updateEventRequest.getAddress());
    event.setLatitude(updateEventRequest.getLatitude());
    event.setLongitude(updateEventRequest.getLongitude());
    event.setMainImage(updateEventRequest.getMainImage());
  }

  // ------------------------DTO Response------------------------------
  // List 패이지 반환값
  private ListEventResponse toListEventResponse(Event event) {
    return null;
  }

  // detail 페이지 반환값
  private EventResponse toDetailResponse(Event event) {
    EventResponse response =
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
    return response;
  }
}
