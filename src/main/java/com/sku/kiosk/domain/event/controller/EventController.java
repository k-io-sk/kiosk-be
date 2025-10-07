/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sku.kiosk.domain.event.dto.response.EventResponse;
import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  @GetMapping
  public List<EventResponse> getAllEvents() {
    return eventService.getAllEvents();
  }

  @GetMapping("/category/{category}")
  public List<EventResponse> getEventsByEventCategory(@PathVariable EventCategory category) {
    return eventService.getEventsByEventCategory(category);
  }

  // 상세보기
  @GetMapping("/{id}")
  public EventResponse getEventDetail(@PathVariable Long id) {
    return eventService.getEventDetail(id);
  }

  // 제목으로 검색
  @GetMapping("/search/{title}")
  public List<EventResponse> searchEvents(@PathVariable String title) {
    return eventService.searchEvents(title);
  }

  @PostMapping
  public EventResponse createEvent(@RequestBody Event event) {
    return eventService.createEvent(event);
  }

  @PutMapping("/{id}")
  public EventResponse updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
    return eventService.updateEvent(id, updatedEvent);
  }

  @DeleteMapping("/{id}")
  public void deleteEvent(@PathVariable Long id) {
    eventService.deleteEvent(id);
  }
}
