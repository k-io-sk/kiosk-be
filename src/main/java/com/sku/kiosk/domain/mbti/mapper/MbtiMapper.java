/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.mapper;

import org.springframework.stereotype.Component;

import com.sku.kiosk.domain.mbti.dto.response.MbtiRequestCountResponse;

@Component
public class MbtiMapper {

  public MbtiRequestCountResponse toMbtiRequestCountResponse(String mbti, Long requestCount) {
    return MbtiRequestCountResponse.builder().mbti(mbti).requestCount(requestCount).build();
  }
}
