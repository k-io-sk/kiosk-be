/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.crawling.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class JongnoCrawlingProperties {

  @Value("${crawling.openapi.url}")
  private String requestUrl;

  @Value("${crawling.authentication.key}")
  private String authenticationKey;

  @Value("${crawling.data.type}")
  private String dataType;

  @Value("${crawling.data.service}")
  private String dataService;

  private final Integer startIndex = 1;
  private final Integer endIndex = 400;

  private final String codeName = "%20";
  private final String title = "%20";

  private final String gu = "종로구";
}
