/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "SummaryEventResponse: 추천 이벤트 2개 요약 응답 DTO")
public class SummaryEventResponse {

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

  @Schema(description = "행사 진행 시간", example = "16:00")
  private String eventTime;

  @Schema(description = "행사 대상", example = "만 5세 이상")
  private String recruitTarget;

  @Schema(description = "요금", example = "무료")
  private String price;

  @Schema(description = "문의", example = "02-1234-2313")
  private String inquiry;

  @Schema(description = "메인 이미지", example = "image.png")
  private String mainImage;
}
