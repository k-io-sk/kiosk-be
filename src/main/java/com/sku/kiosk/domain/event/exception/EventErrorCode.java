/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.exception;

import org.springframework.http.HttpStatus;

import com.sku.kiosk.global.exception.model.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventErrorCode implements BaseErrorCode {
  EVENT_NOT_FOUND("EVENT_4041", "존재하지 않는 이벤트입니다.", HttpStatus.NOT_FOUND);

  // INTERNAL_SERVER_ERROR("SERVER_ERROR", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String code;

  private final String message;

  private final HttpStatus status;
}
