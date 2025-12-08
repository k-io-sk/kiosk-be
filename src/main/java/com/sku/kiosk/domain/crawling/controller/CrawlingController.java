/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.crawling.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sku.kiosk.domain.crawling.service.CrawlingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crawling")
public class CrawlingController {

  private final CrawlingService crawlingService;

  @PostMapping("/update")
  public void updateCrawling() {
    crawlingService.updateCrawlingEvent();
  }
}
