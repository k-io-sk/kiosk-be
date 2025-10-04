/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sku.kiosk.domain.event.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}
