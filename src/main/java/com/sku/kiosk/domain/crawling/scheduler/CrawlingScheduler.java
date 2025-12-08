/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.crawling.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sku.kiosk.domain.crawling.service.CrawlingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlingScheduler {

  private final CrawlingService crawlingService;

  @Scheduled(cron = "0 0 0 * * *")
  public void dailyCrawling() {
    crawlingService.updateCrawlingEvent();
  }
}
