/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "EventResponse DTO", description = "이벤트에 대한 응답 반환")
public class EventResponse {

  @Schema(description = "", example = "1")
  private Long id;

  @NotBlank(message = "")
  @Schema(description = "", example = "234567")
  private String title;

  @NotBlank(message = "")
  @Schema(description = "", example = "인사동")
  private Long cultCode;

  @NotBlank(message = "")
  @Schema(description = "", example = "인사동")
  private String location;

  @Schema(description = "", example = "2025-09-24")
  private LocalDate startDate;

  @Schema(description = "", example = "2025-09-26")
  private LocalDate endDate;

  @Schema(description = "", example = "16:00")
  private String eventTime;

  @Schema(description = "", example = "SHOW")
  private EventCategory eventCategory;

  @Schema(description = "", example = "전체")
  private String recruitTarget;

  @Schema(description = "", example = "무료")
  private String price;

  @Schema(description = "", example = "-")
  private String inquiry;

  @Schema(description = "", example = "insa.png")
  private String mainImage;

  @Schema(description = "", example = "종로구 32-3")
  private String address;

  @Schema(description = "", example = "231.43")
  private Double latitude;

  @Schema(description = "", example = "123.23")
  private Double longitude;

  @Schema(description = "", example = "ONGOING")
  private Status status;
}
