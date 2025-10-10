/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MainEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperMainEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventPeriod;
import com.sku.kiosk.global.page.response.PageResponse;

public interface EventService {

  /**
   * 메인화면에서 카테고리별 대표 이벤트 이미지 3개를 반환하는 메서드
   *
   * @return 카테고리별 MainEventResponse 리스트
   */
  List<WrapperMainEventResponse<MainEventResponse>> getMainEventList();

  /**
   * 전체or카테고리/기간/검색 필터를 통한 이벤트 페이지 반환 메서드
   *
   * @param eventCategory 이벤트 카테고리
   * @param eventPeriod 이벤트 기간
   * @param keyword 검색어
   * @param pageable 페이징 요청 객체
   * @return
   */
  PageResponse<ListEventResponse> getEventList(
      EventCategory eventCategory, EventPeriod eventPeriod, String keyword, Pageable pageable);

  /**
   * 특정 이벤트 상세 조회 메서드
   *
   * @param eventId 이벤트 식별자
   * @return 이벤트 상세 DetailEventResponse
   */
  DetailEventResponse getEventDetail(Long eventId);

  /**
   * 특정 이벤트 생성 메서드
   *
   * @param createEventRequest 이벤트 생성 요청 객체
   * @param eventCategory 생성할 이벤트 카테고리
   * @return 이벤트 상세 DetailEventResponse
   */
  DetailEventResponse createEvent(
      CreateEventRequest createEventRequest, EventCategory eventCategory);

  /**
   * 특정 이벤트 수정 메서드
   *
   * @param eventId 이벤트 식별자
   * @param updateEventRequest 이벤트 수정 요청 객체
   * @param eventCategory 수정할 이벤트 카테고리
   * @return 이벤트 상세 DetailEventResponse
   */
  DetailEventResponse updateEvent(
      Long eventId, UpdateEventRequest updateEventRequest, EventCategory eventCategory);

  /**
   * 특정 이벤트 삭제 메서드
   *
   * @param eventId 이벤트 식별자
   */
  void deleteEvent(Long eventId);
}
