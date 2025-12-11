/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.HomeEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MbtiEventResponse;
import com.sku.kiosk.domain.event.dto.response.SummaryEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperHomeEventResponse;
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
        .cultCode(event.getCultCode())
        .eventClassification(event.getEventClassification())
        .gu(event.getGu())
        .title(event.getTitle())
        .location(event.getLocation())
        .orgName(event.getOrgName())
        .recruitTarget(event.getRecruitTarget())
        .price(event.getPrice())
        .inquiry(event.getInquiry())
        .description(event.getDescription())
        .orgLink(event.getOrgLink())
        .mainImage(event.getMainImage())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .latitude(event.getLatitude())
        .longitude(event.getLongitude())
        .isFree(event.getIsFree())
        .eventTime(event.getEventTime())
        .eventCategory(event.getEventCategory())
        .build();
  }

  public WrapperHomeEventResponse<HomeEventResponse> toWrapperMainEventResponse(
      List<Event> events) {
    return WrapperHomeEventResponse.<HomeEventResponse>builder()
        .eventCategory(events.getFirst().getEventCategory())
        .events(events.stream().map(this::toMainEventResponse).toList())
        .build();
  }

  public SummaryEventResponse toSummaryEventResponse(Event event) {
    return SummaryEventResponse.builder()
        .eventId(event.getId())
        .title(event.getTitle())
        .location(event.getLocation())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .eventTime(event.getEventTime())
        .recruitTarget(event.getRecruitTarget())
        .price(event.getPrice())
        .inquiry(event.getInquiry())
        .mainImage(event.getMainImage())
        .build();
  }

  public MbtiEventResponse toMbtiEventResponse(Event event) {
    return MbtiEventResponse.builder()
        .eventId(event.getId())
        .title(event.getTitle())
        .mainImage(event.getMainImage())
        .build();
  }

  private HomeEventResponse toMainEventResponse(Event event) {
    return HomeEventResponse.builder()
        .eventId(event.getId())
        .mainImage(event.getMainImage())
        .eventCategory(event.getEventCategory())
        .build();
  }
}
