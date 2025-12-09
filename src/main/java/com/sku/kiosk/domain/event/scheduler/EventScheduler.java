/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sku.kiosk.domain.event.service.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventScheduler {

  private final EventService eventService;

  @Scheduled(cron = "0 10 0 * * *")
  public void dailySoftDeletion() {
    eventService.schedulingSoftDeleteEvent();
  }

  @Scheduled(cron = "0 30 0 * * 5")
  public void weeklyHardDeletion() {
    eventService.schedulingHardDeleteEvent();
  }
}
