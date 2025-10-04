/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.sku.kiosk.global.common.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String location;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EventCategory category;

  @Column(name = "recruit_target")
  private String recruitTarget;

  private String price;

  private String inquiry; // 문의

  @Column(name = "main_image")
  private String mainImage;
}
