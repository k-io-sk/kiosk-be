/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.Dto;

import java.time.LocalDateTime;

import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.Status;

import lombok.Builder;

@Builder
public class EventResponse {
  private Long id;
  private String title;
  private Long cultCode;

  private String location;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private LocalDateTime eventTime;

  private EventCategory category;

  private String recruitTarget;
  private String price;
  private String inquiry;
  private String mainImage;
  private String address;
  private Double latitude;
  private Double longitude;
  private Status status;
}
