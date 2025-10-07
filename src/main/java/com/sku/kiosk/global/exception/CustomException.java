/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.global.exception;

import com.sku.kiosk.global.exception.model.BaseErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
