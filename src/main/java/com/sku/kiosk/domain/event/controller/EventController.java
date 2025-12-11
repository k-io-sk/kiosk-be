/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.HomeEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MbtiEventResponse;
import com.sku.kiosk.domain.event.dto.response.SummaryEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperHomeEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.global.page.response.PageResponse;
import com.sku.kiosk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/events")
@Tag(name = "이벤트", description = "이벤트 관련 API")
public interface EventController {

  @Operation(summary = "메인화면 이벤트 이미지 반환", description = "메인화면에서 카테고리별 3개 이벤트 이미지 반환하는 API")
  @GetMapping(value = "/main", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<List<WrapperHomeEventResponse<HomeEventResponse>>>> getMainPage();

  @Operation(summary = "전체or카테고리/검색 필터 이벤트 페이지 반환", description = "사용자에게 이벤트 페이지를 반환하는 API")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<PageResponse<ListEventResponse>>> getEventList(
      @Parameter(description = "이벤트 카테고리", example = "ALL") @RequestParam
          EventCategory eventCategory,
      @Parameter(description = "검색어") @RequestParam(required = false) String keyword,
      @Parameter(description = "페이지 번호", example = "1") @RequestParam Integer pageNum,
      @Parameter(description = "페이지 크기", example = "12") @RequestParam Integer pageSize);

  @Operation(summary = "특정 이벤트 상세보기", description = "특정 이벤트 상세보기 API")
  @GetMapping(value = "/{event-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<DetailEventResponse>> getEventDetail(
      @PathVariable(value = "event-id") Long eventId);

  @Operation(summary = "특정 이벤트 생성 [관리자]", description = "관리자가 특정 이벤트 생성을 요청하는 API")
  @PostMapping
  ResponseEntity<BaseResponse<DetailEventResponse>> createEvent(
      @RequestBody @Valid CreateEventRequest createEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "SHOW") @RequestParam
          EventCategory eventCategory);

  @Operation(summary = "특정 이벤트 수정 [관리자]", description = "관리자가 특정 이벤트 수정을 요청하는 API")
  @PutMapping("/{event-id}")
  ResponseEntity<BaseResponse<DetailEventResponse>> updateEvent(
      @PathVariable(value = "event-id") Long eventId,
      @RequestBody @Valid UpdateEventRequest updateEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "ETC") @RequestParam
          EventCategory eventCategory);

  @Operation(summary = "특정 이벤트 삭제 [관리자]", description = "관리자가 특정 이벤트를 삭제를 요청하는 API")
  @DeleteMapping("/{event-id}")
  ResponseEntity<BaseResponse<Void>> deleteEvent(@PathVariable(value = "event-id") Long eventId);

  @Operation(summary = "MBTI별 이벤트 추천", description = "MBTI를 입력해 2개의 이벤트 추천을 요청하는 API")
  @GetMapping(value = "/recommend", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<List<MbtiEventResponse>>> getRecommend(@RequestParam String mbti);

  @Operation(
      summary = "추천한 2개의 이벤트 요약 보기(QR)",
      description = "MBTI별 추천 받은 이벤트에서 식별자 2개를 받아 미리보기를 반환하는 API")
  @GetMapping(value = "/recommend/summary", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<List<SummaryEventResponse>>> getRecommendSummary(
      @RequestParam List<Long> eventIds);

  @Operation(summary = "카테고리별 랜덤 이벤트 5개 응답", description = "메인화면에서 보일 카테고리별 랜덤 이벤트 5개를 반환하는 API")
  @GetMapping(value = "/random-by-category", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BaseResponse<List<ListEventResponse>>> getRandomByCategory();
}
