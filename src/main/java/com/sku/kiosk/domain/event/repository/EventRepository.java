/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sku.kiosk.domain.event.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query("SELECT e FROM Event e")
  Page<Event> findByAll(Pageable pageable);

  // 제목으로 검색 (부분 일치)
  List<Event> findByTitleContainingIgnoreCase(String title);

  // 기간별
  // Page<Event> findPeriodAllNoCategory(Pageable pageable); // 카테고리 x all

  //  Page<Event> findByTodayNoCategory(Pageable pageable);
  //
  //  Page<Event> findByThisWeekNoCategory(Pageable pageable);
  //
  //  Page<Event> findByThisMonthNoCategory(Pageable pageable);

  //
}
