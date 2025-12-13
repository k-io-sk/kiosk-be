/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.service;

import com.sku.kiosk.domain.mbti.dto.response.MbtiRequestCountResponse;

public interface MbtiService {

  /**
   * Mbti별 요청 시에 해당 mbti의 요청 카운트를 증가시키는 메서드
   *
   * @param mbti 요청 카운트를 증가시킬 mbti
   */
  void increaseCount(String mbti);

  /**
   * Mbti 각 알파벳을 입력 받아 해당 mbti 요소의 추천 요청 횟수를 반환하는 메서드
   *
   * @param EI E, J
   * @param SN S, N
   * @param TF T, F
   * @param JP J, P
   * @return 해당 mbti의 추천 요청 횟수
   */
  MbtiRequestCountResponse getRequestCountByMbti(String EI, String SN, String TF, String JP);
}
