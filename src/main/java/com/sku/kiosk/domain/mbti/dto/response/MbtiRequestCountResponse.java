/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MbtiRequestCountResponse {

  @Schema(description = "조회 요청한 mbti", example = "ISTP")
  private String mbti;

  @Schema(description = "해당 mbti의 요청 횟수", example = "30")
  private Long requestCount;
}
