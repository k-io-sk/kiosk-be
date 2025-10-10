/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "UpdateEventRequest: 이벤트 수정 요청 DTO")
public class UpdateEventRequest {

  @Schema(description = "서울문화포털 문화코드", example = "123123")
  private Long cultCode;

  @NotBlank(message = "행사 제목은 필수입니다.")
  @Schema(description = "행사 제목", example = "인사동 전시회")
  private String title;

  @NotBlank(message = "행사 장소는 필수입니다.")
  @Schema(description = "행사 장소", example = "종로 3가역")
  private String location;

  @Schema(description = "행사 시작 기간", example = "2025-09-24")
  private LocalDate startDate;

  @Schema(description = "행사 종료 기간", example = "2025-09-26")
  private LocalDate endDate;

  @Schema(description = "행사 시작 시간", example = "16:00")
  private String eventTime;

  @Schema(description = "모집 대상", example = "전체")
  private String recruitTarget;

  @Schema(description = "가격", example = "무료")
  private String price;

  @Schema(description = "문의", example = "050-1234")
  private String inquiry;

  @Schema(description = "메인 이미지URL", example = "image3.png")
  private String mainImage;

  @Schema(description = "행사 위치 주소", example = "종로3가 98-3")
  private String address;

  @Schema(description = "행사 위도 좌표", example = "435.23")
  private Double latitude;

  @Schema(description = "행사 경도 좌표", example = "352.35")
  private Double longitude;
}
