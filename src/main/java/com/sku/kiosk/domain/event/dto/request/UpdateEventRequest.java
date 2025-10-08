/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "UpdateEventRequest: 이벤트 수정 요청 DTO")
public class UpdateEventRequest {

  @Schema(description = "", example = "123456")
  private Long cultCode;

  @NotBlank(message = "")
  @Schema(description = "", example = "인사 전시회")
  private String title;

  @NotBlank(message = "")
  @Schema(description = "", example = "종로3가역")
  private String location;

  @Schema(description = "", example = "2025-11-02")
  private LocalDate startDate;

  @Schema(description = "", example = "2025-11-03")
  private LocalDate endDate;

  @Schema(description = "", example = "16:00")
  private String eventTime;

  @Schema(description = "", example = "전체")
  private String recruitTarget;

  @Schema(description = "", example = "무료")
  private String price;

  @Schema(description = "", example = "050-1234")
  private String inquiry; // 문의

  @Schema(description = "", example = "image.png")
  private String mainImage;

  @Schema(description = "", example = "종로4가 23-4")
  private String address;

  @Schema(description = "", example = "123.23")
  private Double latitude;

  @Schema(description = "", example = "456.35")
  private Double longitude;
}
