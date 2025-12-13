/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "mbti")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mbti {

  @Id
  @Column(length = 4)
  String mbti;

  @Column(name = "ei", length = 1, nullable = false)
  String ei;

  @Column(name = "sn", length = 1, nullable = false)
  String sn;

  @Column(name = "tf", length = 1, nullable = false)
  String tf;

  @Column(name = "jp", length = 1, nullable = false)
  String jp;

  @Column(name = "requestCount", nullable = false)
  @Builder.Default
  Long requestCount = 0L;

  public void increaseRequestCount() {
    requestCount++;
  }
}
