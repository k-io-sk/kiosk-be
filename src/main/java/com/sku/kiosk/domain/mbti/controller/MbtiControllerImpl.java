/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sku.kiosk.domain.mbti.dto.response.MbtiRequestCountResponse;
import com.sku.kiosk.domain.mbti.service.MbtiService;
import com.sku.kiosk.global.exception.CustomException;
import com.sku.kiosk.global.exception.GlobalErrorCode;
import com.sku.kiosk.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MbtiControllerImpl implements MbtiController {

  private final MbtiService mbtiService;

  @Override
  public ResponseEntity<BaseResponse<MbtiRequestCountResponse>> getRequestCountByMbti(
      @RequestParam(required = false) String EI,
      @RequestParam(required = false) String SN,
      @RequestParam(required = false) String TF,
      @RequestParam(required = false) String JP) {
    if (validateMbti(EI, SN, TF, JP)) {
      return ResponseEntity.status(200)
          .body(
              BaseResponse.success(
                  200,
                  "mbti별 추천 요청 횟수 조회에 성공했습니다.",
                  mbtiService.getRequestCountByMbti(EI, SN, TF, JP)));
    } else {
      throw new CustomException(GlobalErrorCode.INVALID_INPUT_VALUE);
    }
  }

  private boolean validateMbti(String EI, String SN, String TF, String JP) {
    if (EI == null && SN == null && TF == null && JP == null) {
      return false;
    }
    return (EI == null || EI.matches("[EI]$"))
        && (SN == null || SN.matches("[SN]$"))
        && (TF == null || TF.matches("[TF]$"))
        && (JP == null || JP.matches("[JP]$"));
  }
}
