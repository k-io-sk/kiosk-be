/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sku.kiosk.domain.mbti.dto.response.MbtiRequestCountResponse;
import com.sku.kiosk.domain.mbti.entity.Mbti;
import com.sku.kiosk.domain.mbti.exception.MbtiErrorCode;
import com.sku.kiosk.domain.mbti.mapper.MbtiMapper;
import com.sku.kiosk.domain.mbti.repository.MbtiRepository;
import com.sku.kiosk.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MbtiServiceImpl implements MbtiService {

  private final MbtiRepository mbtiRepository;
  private final MbtiMapper mbtiMapper;

  @Override
  @Transactional
  public void increaseCount(String mbti) {
    Mbti mbtiInstance =
        mbtiRepository
            .findById(mbti)
            .orElseThrow(() -> new CustomException(MbtiErrorCode.MBTI_NOT_FOUND));

    mbtiInstance.increaseRequestCount();
  }

  @Override
  @Transactional(readOnly = true)
  public MbtiRequestCountResponse getRequestCountByMbti(
      String EI, String SN, String TF, String JP) {
    String mbti =
        convertToString(EI) + convertToString(SN) + convertToString(TF) + convertToString(JP);
    return mbtiMapper.toMbtiRequestCountResponse(
        mbti, mbtiRepository.sumRequestCountByMbti(EI, SN, TF, JP));
  }

  private String convertToString(String alphabet) {
    if (alphabet == null) {
      return "";
    }
    return alphabet;
  }
}
