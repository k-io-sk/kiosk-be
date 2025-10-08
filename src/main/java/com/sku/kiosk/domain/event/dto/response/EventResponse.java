/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "EventResponse DTO", description = "이벤트에 대한 응답 반환")
public class EventResponse {
  private Long id;
  private String title;
  private Long cultCode;

  private String location;
  private LocalDate startDate;
  private LocalDate endDate;
  private String eventTime;

  private EventCategory eventCategory;

  private String recruitTarget;
  private String price;
  private String inquiry;
  private String mainImage;
  private String address;
  private Double latitude;
  private Double longitude;
  private Status status;
}
