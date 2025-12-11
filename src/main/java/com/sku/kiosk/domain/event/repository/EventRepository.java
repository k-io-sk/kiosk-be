/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sku.kiosk.domain.event.entity.Event;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventClassification;
import com.sku.kiosk.domain.event.entity.Status;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  List<Event> findTop3ByEventCategoryOrderByEndDateAsc(EventCategory eventCategory);

  Page<Event>
      findByTitleContainingAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusOrderByEndDateAsc(
          String keyword,
          LocalDate endOfWeek,
          LocalDate startOfWeek,
          Status status,
          Pageable pageable);

  Page<Event>
      findByEventCategoryAndTitleContainingAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStatusOrderByEndDateAsc(
          EventCategory eventCategory,
          String keyword,
          LocalDate endOfWeek,
          LocalDate startOfWeek,
          Status status,
          Pageable pageable);

  Page<Event> findByTitleContainingAndStatusOrderByEndDateAsc(
      String keyword, Status status, Pageable pageable);

  Page<Event> findByEventCategoryAndTitleContainingAndStatusOrderByEndDateAsc(
      EventCategory eventCategory, String keyword, Status status, Pageable pageable);

  List<Event> findAllByStatus(Status status);

  List<Event> findByEventCategoryAndRandomCount(EventCategory eventCategory, Long randomCount);

  @Query(
      """
    SELECT e.eventCategory, MIN(e.randomCount)
    FROM Event e
    GROUP BY e.eventCategory
    """)
  List<Object[]> findMinRandomCountPerCategory();

  Optional<Event> findFirstByEventClassificationOrderByRecommendCountAsc(
      EventClassification eventClassification);
}
