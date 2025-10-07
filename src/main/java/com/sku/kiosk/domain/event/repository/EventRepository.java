/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.entity.EventCategory;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  // 카테고리별
  List<Event> findByCategory(EventCategory category);

  // 제목으로 검색 (부분 일치)
  List<Event> findByTitleContainingIgnoreCase(String title);

  // 기간별
}
