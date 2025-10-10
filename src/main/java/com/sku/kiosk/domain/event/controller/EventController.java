/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.controller;

import java.util.List;

import jakarta.validation.Valid;

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
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MainEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperMainEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventPeriod;
import com.sku.kiosk.global.page.response.PageResponse;
import com.sku.kiosk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/events")
@Tag(name = "이벤트", description = "이벤트 관련 API")
public interface EventController {

  @Operation(summary = "메인화면 이벤트 이미지 반환", description = "메인화면에서 카테고리별 3개 이벤트 이미지 반환하는 API")
  @GetMapping("/main")
  ResponseEntity<BaseResponse<List<WrapperMainEventResponse<MainEventResponse>>>> getMainPage();

  @Operation(summary = "전체or카테고리/기간/검색 필터 이벤트 페이지 반환", description = "사용자에게 이벤트 페이지를 반환하는 API")
  @GetMapping
  ResponseEntity<BaseResponse<PageResponse<ListEventResponse>>> getEventList(
      @Parameter(description = "이벤트 카테고리", example = "ALL") @RequestParam
          EventCategory eventCategory,
      @Parameter(description = "이벤트 기간", example = "ALL") @RequestParam EventPeriod eventPeriod,
      @Parameter(description = "검색어") @RequestParam(required = false) String keyword,
      @Parameter(description = "페이지 번호", example = "1") @RequestParam Integer pageNum,
      @Parameter(description = "페이지 크기", example = "12") @RequestParam Integer pageSize);

  @Operation(summary = "특정 이벤트 상세보기", description = "특정 이벤트 상세보기 API")
  @GetMapping("/{event-id}")
  ResponseEntity<BaseResponse<DetailEventResponse>> getEventDetail(
      @PathVariable(value = "event-id") Long eventId);

  @Operation(summary = "특정 이벤트 생성", description = "관리자가 특정 이벤트 생성을 요청하는 API")
  @PostMapping
  ResponseEntity<BaseResponse<DetailEventResponse>> createEvent(
      @RequestBody @Valid CreateEventRequest createEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "SHOW") @RequestParam
          EventCategory eventCategory);

  @Operation(summary = "특정 이벤트 수정", description = "관리자가 특정 이벤트 수정을 요청하는 API")
  @PutMapping("/{event-id}")
  ResponseEntity<BaseResponse<DetailEventResponse>> updateEvent(
      @PathVariable(value = "event-id") Long eventId,
      @RequestBody @Valid UpdateEventRequest updateEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "ETC") @RequestParam
          EventCategory eventCategory);

  @Operation(summary = "특정 이벤트 삭제", description = "관리자가 특정 이벤트를 삭제를 요청하는 API")
  @DeleteMapping("/{event-id}")
  ResponseEntity<BaseResponse<Void>> deleteEvent(@PathVariable(value = "event-id") Long eventId);
}
