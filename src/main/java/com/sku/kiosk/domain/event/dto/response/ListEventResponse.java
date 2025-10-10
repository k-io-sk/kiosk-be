/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import com.sku.kiosk.domain.event.entity.EventCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "ListEventResponse: 이벤트 리스트 반환 응답 DTO")
@Builder
public class ListEventResponse {

  @Schema(description = "이벤트 식별자", example = "1")
  private Long eventId;

  @Schema(description = "행사 제목", example = "인사동 아트페어")
  private String title;

  @Schema(description = "행사 장소", example = "인사동")
  private String location;

  @Schema(description = "행사 시작 기간", example = "2025-09-24")
  private LocalDate startDate;

  @Schema(description = "행사 종료 기간", example = "2025-09-26")
  private LocalDate endDate;

  @Schema(description = "행사 카테고리", example = "SHOW")
  private EventCategory eventCategory;

  @Schema(description = "", example = "image.png")
  private String mainImage;
}
