/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.global.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public HttpClient httpClient() {
    return HttpClient.newHttpClient();
  }
}
