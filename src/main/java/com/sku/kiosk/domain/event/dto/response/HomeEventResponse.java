/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import com.sku.kiosk.domain.event.entity.EventCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "MainEventResponse: 메인 화면 이벤트 리스트 반환 응답 DTO")
public class HomeEventResponse {

  @Schema(description = "이벤트 식별자", example = "2")
  private Long eventId;

  @Schema(description = "메인 이미지URL", example = "image.png")
  private String mainImage;

  @Schema(description = "행사 카테고리", example = "SHOW")
  private EventCategory eventCategory;
}
