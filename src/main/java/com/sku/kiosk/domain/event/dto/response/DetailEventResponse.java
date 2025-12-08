/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.time.LocalDate;

import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventClassification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(title = "DetailEventResponse: 이벤트 상세보기 반환 응답 DTO")
@Builder
public class DetailEventResponse {

  @Schema(description = "이벤트 식별자", example = "1")
  private Long eventId;

  @Schema(description = "이벤트 코드", example = "12345")
  private Long cultCode;

  @Schema(description = "행사 제목", example = "인사동 아트페어")
  private String title;

  @Schema(description = "행사 장소", example = "인사동")
  private String location;

  @Schema(description = "행사 시작 기간", example = "2025-09-24")
  private LocalDate startDate;

  @Schema(description = "행사 종료 기간", example = "2025-09-26")
  private LocalDate endDate;

  @Schema(description = "행사 시간", example = "10:00 ~ 18:00")
  private String eventTime;

  @Schema(description = "행사 카테고리", example = "MUSICAL_OPERA")
  private EventCategory eventCategory;

  @Schema(description = "행사 분류", example = "EDU_EXPERIENCE")
  private EventClassification eventClassification;

  @Schema(description = "행사 지역(구)", example = "종로구")
  private String gu;

  @Schema(description = "주최 기관명", example = "종로문화재단")
  private String orgName;

  @Schema(description = "모집 대상", example = "전체")
  private String recruitTarget;

  @Schema(description = "가격", example = "무료")
  private String price;

  @Schema(description = "문의", example = "050-1234")
  private String inquiry;

  @Schema(description = "행사 설명")
  private String description;

  @Schema(description = "기관 링크 URL", example = "https://example.com")
  private String orgLink;

  @Schema(description = "메인 이미지URL", example = "image.png")
  private String mainImage;

  @Schema(description = "행사 위도 좌표", example = "37.5662952")
  private Double latitude;

  @Schema(description = "행사 경도 좌표", example = "126.9779451")
  private Double longitude;

  @Schema(description = "무료 여부", example = "false")
  private Boolean isFree;
}
