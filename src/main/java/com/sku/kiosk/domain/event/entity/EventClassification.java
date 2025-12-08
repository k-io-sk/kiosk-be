/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventClassification {
  EDU_EXPERIENCE("교육/체험"),
  KOREAN_TRADITIONAL("국악"),
  ETC("기타"),
  RECITAL("독주/독창회"),
  DANCE("무용"),
  MUSICAL_OPERA("뮤지컬/오페라"),
  PLAY("연극"),
  MOVIE("영화"),
  EXHIBITION_ART("전시/미술"),
  FEST_ETC("축제-기타"),
  FEST_ART("축제-문화/예술"),
  FEST_CIVIC("축제-시민화합"),
  FEST_NATURE("축제-자연경관"),
  FEST_HISTORY("축제-전통역사"),
  CONCERT("콘서트"),
  CLASSICAL("클래식");

  private final String ko;
}
