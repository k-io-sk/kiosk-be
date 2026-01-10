/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.HomeEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MbtiEventResponse;
import com.sku.kiosk.domain.event.dto.response.SummaryEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperHomeEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventClassification;
import com.sku.kiosk.global.page.response.PageResponse;

public interface EventService {

  /**
   * 메인화면에서 카테고리별 대표 이벤트 이미지 3개를 반환하는 메서드
   *
   * @return 카테고리별 MainEventResponse 리스트
   */
  List<WrapperHomeEventResponse<HomeEventResponse>> getMainEventList();

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
      EventCategory eventCategory, String keyword, Pageable pageable);

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

  /**
   * 이벤트 분류 문자열을 이벤트 소분류 enum값으로 반환하는 메서드
   *
   * @param codename 이벤트 분류 문자열
   * @return 이벤트 소분류 enum값
   */
  EventClassification initClassification(String codename);

  /**
   * 이벤트 분류 문자열을 대분류 enum값으로 반환하는 메서드
   *
   * @param codename 이벤트 분류 문자열
   * @return 이벤트 대분류 enum값
   */
  EventCategory initCategory(String codename);

  /**
   * MBTI를 입력해 해당 mbti에 맞는 이벤트를 반환하는 메서드
   *
   * @param mbti MBTI 문자열
   * @return MBTI를 통해 추천 받은 이벤트 리스트
   */
  List<MbtiEventResponse> getRecommend(String mbti);

  /**
   * mbti를 입력하여 추천 받은 2개의 이벤트들의 요약을 반환하는 메서드
   *
   * @param eventIds 추천 받은 2개의 이벤트 식별자들
   * @return 해당 식별자를 pk로 갖는 이벤트들의 요약 응답 리스트
   */
  List<SummaryEventResponse> getRecommendSummary(List<Long> eventIds);

  /**
   * 메인 화면에서 카테고리별 랜덤 5개 띄울 이벤트 리스트를 반환하는 메서드
   *
   * @return 카테고리별 랜덤 5개의 이벤트
   */
  List<ListEventResponse> getRandomByCategory();

  /** 매일 00시 10분마다 끝난 행사를 soft delete 하기 위한 메서드 */
  void schedulingSoftDeleteEvent();

  /** 매월 마지막 날 00시 30분마다 soft deleted된 행사를 hard delete 하기 위한 메서드 */
  void schedulingHardDeleteEvent();

  /** 매일 00시 15분마다 다가올 예정인 행사를 오늘의 행사로 변경하기 위한 메서드 */
  void schedulingChangeComingToOnGoingEvent();

  public void initStatus();
}
