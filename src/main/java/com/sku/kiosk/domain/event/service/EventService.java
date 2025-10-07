/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sku.kiosk.domain.event.dto.response.EventResponse;
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
  public List<EventResponse> getAllEvents() {
    List<Event> eventList = eventRepository.findAll();
    List<EventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toListResponse(e));
    }
    return result;
  }

  // 카테고리별 조회
  public List<EventResponse> getEventsByEventCategory(EventCategory category) {
    List<Event> eventList = eventRepository.findByEventCategory(category);
    List<EventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toListResponse(e));
    }
    return result;
  }

  // title로 검색
  public List<EventResponse> searchEvents(String title) {
    List<Event> eventList = eventRepository.findByTitleContainingIgnoreCase(title);
    List<EventResponse> result = new ArrayList<>();

    for (Event e : eventList) {
      result.add(toListResponse(e));
    }
    return result;
  }

  // detail 페이지 조회
  public EventResponse getEventDetail(Long id) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
    return toDetailResponse(event);
  }

  // ---------------------------CRUD---------------------------------

  public EventResponse createEvent(Event event) {
    Event saved = eventRepository.save(event);
    return toDetailResponse(saved);
  }

  public EventResponse updateEvent(Long id, Event updatedEvent) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));

    event.setTitle(updatedEvent.getTitle());
    event.setLocation(updatedEvent.getLocation());
    event.setEventCategory(updatedEvent.getEventCategory());
    event.setStartDate(updatedEvent.getStartDate());
    event.setEndDate(updatedEvent.getEndDate());
    event.setPrice(updatedEvent.getPrice());
    event.setInquiry(updatedEvent.getInquiry());
    event.setAddress(updatedEvent.getAddress());
    event.setLatitude(updatedEvent.getLatitude());
    event.setLongitude(updatedEvent.getLongitude());
    event.setStatus(updatedEvent.getStatus());
    event.setMainImage(updatedEvent.getMainImage());

    Event saved = eventRepository.save(event);
    return toDetailResponse(saved);
  }

  public void deleteEvent(Long id) {
    if (!eventRepository.existsById(id)) {
      throw new IllegalArgumentException("삭제할 이벤트를 찾을 수 없습니다.");
    }
    eventRepository.deleteById(id);
  }

  // ------------------------DTO Response------------------------------
  // List 패이지 반환값
  private EventResponse toListResponse(Event event) {
    EventResponse response =
        EventResponse.builder()
            .id(event.getId())
            .title(event.getTitle())
            .cultCode(event.getCultCode())
            .location(event.getLocation())
            .startDate(event.getStartDate())
            .endDate(event.getEndDate())
            .eventCategory(event.getEventCategory())
            .mainImage(event.getMainImage())
            .status(event.getStatus())
            .build();
    return response;
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
