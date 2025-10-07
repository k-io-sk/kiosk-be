/* 
 * Copyright (c) LikeLion13th Problem not Found 
 */
package com.sku.kiosk.global.exception.model;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

  String getCode();

  String getMessage();

  HttpStatus getStatus();
}
