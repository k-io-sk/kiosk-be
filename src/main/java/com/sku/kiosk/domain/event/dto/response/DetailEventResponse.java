/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "DetailEventResponse: 이벤트 상세보기 반환 응답 DTO")
@Builder
public class DetailEventResponse {
  @Schema(description = "", example = "2342124")
  private Long cultCode;

  @NotBlank(message = "")
  @Schema(description = "", example = "인사동 아트페어")
  private String title;

  @NotBlank(message = "")
  @Schema(description = "", example = "인사동")
  private String location;

  @Schema(description = "", example = "2025-09-24")
  private LocalDate startDate;

  @Schema(description = "", example = "2025-09-26")
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

  @Schema(description = "", example = "종로3가 98-3")
  private String address;

  @Schema(description = "", example = "435.23")
  private Double latitude;

  @Schema(description = "", example = "352.35")
  private Double longitude;
}
