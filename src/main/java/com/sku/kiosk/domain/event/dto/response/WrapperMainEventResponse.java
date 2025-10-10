/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.dto.response;

import java.util.List;

import com.sku.kiosk.domain.event.entity.EventCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "WrapperMainEventResponse: 메인 화면 이벤트 리스트를 감싸는 반환 응답 DTO")
public class WrapperMainEventResponse<T> {

  @Schema(description = "행사 카테고리", example = "SHOW")
  private EventCategory eventCategory;

  @Schema(
      description = "행사 데이터 리스트",
      example =
          "[{\"eventId\": 2, \"mainImage\": \"image.png\", \"eventCategory\": \"SHOW\"}, {\"eventId\": 3, \"mainImage\": \"image.png\", \"eventCategory\": \"SHOW\"}]")
  private List<T> events;
}
