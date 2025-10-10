/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MainEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperMainEventResponse;
import com.sku.kiosk.domain.event.entity.Event;

@Component
public class EventMapper {

  public ListEventResponse toListEventResponse(Event event) {
    return ListEventResponse.builder()
        .eventId(event.getId())
        .title(event.getTitle())
        .location(event.getLocation())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .eventCategory(event.getEventCategory())
        .mainImage(event.getMainImage())
        .build();
  }

  public DetailEventResponse toDetailResponse(Event event) {
    return DetailEventResponse.builder()
        .eventId(event.getId())
        .title(event.getTitle())
        .location(event.getLocation())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .eventTime(event.getEventTime())
        .eventCategory(event.getEventCategory())
        .recruitTarget(event.getRecruitTarget())
        .mainImage(event.getMainImage())
        .price(event.getPrice())
        .address(event.getAddress())
        .inquiry(event.getInquiry())
        .latitude(event.getLatitude())
        .longitude(event.getLongitude())
        .build();
  }

  public WrapperMainEventResponse<MainEventResponse> toWrapperMainEventResponse(
      List<Event> events) {
    return WrapperMainEventResponse.<MainEventResponse>builder()
        .eventCategory(events.getFirst().getEventCategory())
        .events(events.stream().map(this::toMainEventResponse).toList())
        .build();
  }

  private MainEventResponse toMainEventResponse(Event event) {
    return MainEventResponse.builder()
        .eventId(event.getId())
        .mainImage(event.getMainImage())
        .eventCategory(event.getEventCategory())
        .build();
  }
}
