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

  @Column(name = "cult_code", nullable = false)
  private Long cultCode;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "event_time")
  private String eventTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_category", nullable = false)
  private EventCategory eventCategory;

  @Column(name = "recruit_target")
  private String recruitTarget;

  @Column(name = "price")
  private String price;

  @Column(name = "inquiry")
  private String inquiry; // 문의

  @Column(name = "main_image")
  private String mainImage;

  @Column(name = "address")
  private String address;

  @Column(name = "latitude")
  private Double latitude;

  @Column(name = "longitude")
  private Double longitude;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @Builder.Default
  private Status status = Status.ONGOING;
}
