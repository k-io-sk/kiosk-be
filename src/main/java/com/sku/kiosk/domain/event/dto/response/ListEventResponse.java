/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "ListEventResponse: 이벤트 리스트 반환 응답 DTO")
public class ListEventResponse {

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

  @Schema(description = "", example = "image.png")
  private String mainImage;
}
