/* 
 * Copyright (c) LikeLion13th Problem not Found 
 */
package com.sku.kiosk.global.exception;

import com.likelion13.artium.global.exception.model.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
