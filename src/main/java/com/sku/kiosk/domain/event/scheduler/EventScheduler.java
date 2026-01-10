/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.scheduler;

import java.time.LocalDate;

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

  @Scheduled(cron = "0 30 0 * * *")
  public void weeklyHardDeletion() {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    if (tomorrow.getMonth() != today.getMonth()) {
      eventService.schedulingHardDeleteEvent();
    }
  }

  @Scheduled(cron = "0 15 0 * * *")
  public void dailyChangeComingToOnGoingEvent() {
    eventService.schedulingChangeComingToOnGoingEvent();
  }
}
