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

  private final String code;

  private final String message;

  private final HttpStatus status;
}
