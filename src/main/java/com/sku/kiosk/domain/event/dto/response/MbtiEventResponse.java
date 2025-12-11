/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "MbtiEventResponse: MBTI별 이벤트 추천 반환 응답 DTO")
public class MbtiEventResponse {

  @Schema(description = "이벤트 식별자", example = "1")
  private Long eventId;

  @Schema(description = "행사 제목", example = "인사동 아트페어")
  private String title;

  @Schema(description = "메인 이미지", example = "image.png")
  private String mainImage;
}
