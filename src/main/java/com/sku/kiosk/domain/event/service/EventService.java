/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public Event getEventById(Long id) {
    return eventRepository.findById(id).orElse(null);
  }

  public Event createEvent(Event event) {
    return eventRepository.save(event);
  }

  // 이벤트 수정
  public Event updateEvent(Long id, Event updatedEvent) {
    Event event = getEventById(id);
    event.setTitle(updatedEvent.getTitle());
    event.setLocation(updatedEvent.getLocation());
    event.setCategory(updatedEvent.getCategory());
    event.setStartDate(updatedEvent.getStartDate());
    event.setEndDate(updatedEvent.getEndDate());
    event.setPrice(updatedEvent.getPrice());
    event.setInquiry(updatedEvent.getInquiry());
    // event.setMain_Image(updatedEvent.getMain_Image());
    return eventRepository.save(event);
  }

  // 이벤트 삭제
  public void deleteEvent(Long id) {
    eventRepository.deleteById(id);
  }
}
