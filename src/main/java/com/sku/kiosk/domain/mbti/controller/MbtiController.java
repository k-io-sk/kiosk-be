/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sku.kiosk.domain.mbti.dto.response.MbtiRequestCountResponse;
import com.sku.kiosk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/mbtis")
@Tag(name = "MBTI", description = "MBTI 관련 API [관리자]")
public interface MbtiController {

  @Operation(summary = "Mbti별 추천 요청 횟수 조회 [관리자]", description = "관리자가 mbti별 요청 횟수 조회를 위해 사용하는 API")
  @GetMapping(value = "/request-count", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<MbtiRequestCountResponse>> getRequestCountByMbti(
      @RequestParam(required = false) String EI,
      @RequestParam(required = false) String SN,
      @RequestParam(required = false) String TF,
      @RequestParam(required = false) String JP);
}
