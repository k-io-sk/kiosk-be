/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;

import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
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

  @Column(name = "cult_code", nullable = false, unique = true)
  private Long cultCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_classification", nullable = false)
  private EventClassification eventClassification;

  @Column(name = "gu", nullable = false, length = 50)
  private String gu;

  @Column(name = "title", nullable = false, length = 200)
  private String title;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "org_name")
  private String orgName;

  @Column(name = "recruit_target")
  private String recruitTarget;

  @Column(name = "price")
  private String price;

  @Column(name = "inquiry")
  private String inquiry;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "org_link", length = 1000)
  private String orgLink;

  @Column(name = "main_image")
  private String mainImage;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "latitude")
  private Double latitude;

  @Column(name = "longitude")
  private Double longitude;

  @Column(name = "is_free")
  private Boolean isFree;

  @Column(name = "event_time")
  private String eventTime;

  @Column(name = "origin_image_url")
  private String originImageUrl;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_category", nullable = false)
  private EventCategory eventCategory;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @Builder.Default
  private Status status = Status.ONGOING;

  @Column(name = "random_count", nullable = false)
  @Builder.Default
  private Long randomCount = 0L;

  @Column(name = "recommend_count", nullable = false)
  @Builder.Default
  private Long recommendCount = 0L;

  public void updateEvent(UpdateEventRequest updateEventRequest, EventCategory eventCategory) {
    this.title = updateEventRequest.getTitle();
    this.location = updateEventRequest.getLocation();
    this.startDate = updateEventRequest.getStartDate();
    this.endDate = updateEventRequest.getEndDate();
    this.eventTime = updateEventRequest.getEventTime();
    this.eventCategory = eventCategory;
    this.recruitTarget = updateEventRequest.getRecruitTarget();
    this.price = updateEventRequest.getPrice();
    this.inquiry = updateEventRequest.getInquiry();
    this.mainImage = updateEventRequest.getMainImage();
    this.latitude = updateEventRequest.getLatitude();
    this.longitude = updateEventRequest.getLongitude();
  }

  public void updateMainImage(String s3ImageUrl) {
    this.mainImage = s3ImageUrl;
  }

  public void updateStatus(Status status) {
    this.status = status;
  }

  public void plusRandomCount() {
    this.randomCount++;
  }

  public void plusRecommendCount() {
    this.recommendCount++;
  }

  public boolean isSameContent(Event newEvent) {
    return Objects.equals(title, newEvent.title)
        && Objects.equals(location, newEvent.location)
        && Objects.equals(startDate, newEvent.startDate)
        && Objects.equals(endDate, newEvent.endDate)
        && Objects.equals(eventTime, newEvent.eventTime)
        && Objects.equals(eventCategory, newEvent.eventCategory)
        && Objects.equals(eventClassification, newEvent.eventClassification)
        && Objects.equals(gu, newEvent.gu)
        && Objects.equals(orgName, newEvent.orgName)
        && Objects.equals(recruitTarget, newEvent.recruitTarget)
        && Objects.equals(price, newEvent.price)
        && Objects.equals(inquiry, newEvent.inquiry)
        && Objects.equals(description, newEvent.description)
        && Objects.equals(orgLink, newEvent.orgLink)
        && Objects.equals(latitude, newEvent.latitude)
        && Objects.equals(longitude, newEvent.longitude)
        && Objects.equals(isFree, newEvent.isFree);
  }

  public void updateFrom(Event newEvent) {
    this.title = newEvent.title;
    this.location = newEvent.location;
    this.startDate = newEvent.startDate;
    this.endDate = newEvent.endDate;
    this.eventTime = newEvent.eventTime;
    this.eventCategory = newEvent.eventCategory;
    this.eventClassification = newEvent.eventClassification;
    this.gu = newEvent.gu;
    this.orgName = newEvent.orgName;
    this.recruitTarget = newEvent.recruitTarget;
    this.price = newEvent.price;
    this.inquiry = newEvent.inquiry;
    this.description = newEvent.description;
    this.orgLink = newEvent.orgLink;
    this.latitude = newEvent.latitude;
    this.longitude = newEvent.longitude;
    this.isFree = newEvent.isFree;
  }
}
