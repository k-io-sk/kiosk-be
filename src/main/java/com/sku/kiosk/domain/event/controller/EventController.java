/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.EventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventPeriod;
import com.sku.kiosk.domain.event.service.EventService;
import com.sku.kiosk.global.exception.CustomException;
import com.sku.kiosk.global.page.exception.PageErrorStatus;
import com.sku.kiosk.global.page.response.PageResponse;
import com.sku.kiosk.global.response.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  @Operation(summary = "전체/카테고리/기간 별 이벤트 리스트 반환", description = "사용자에게 이벤트 리스트 반환하는 api")
  @GetMapping
  public ResponseEntity<BaseResponse<PageResponse<ListEventResponse>>> getEventList(
      @Parameter(description = "전체 조회 기준", example = "true") @RequestParam Boolean showAll,
      @Parameter(description = "이벤트 카테고리", example = "SHOW", required = false) @RequestParam
          EventCategory eventCategory,
      @Parameter(description = "이벤트 기간", example = "TODAY") @RequestParam EventPeriod eventPeriod,
      @Parameter(description = "페이지 번호", example = "1") @RequestParam Integer pageNum,
      @Parameter(description = "페이지 크기", example = "12") @RequestParam Integer pageSize) {

    Pageable pageable = PageRequest.of(pageNum, pageSize);

    return ResponseEntity.status(200)
        .body(
            BaseResponse.success(
                200,
                "전체 이벤트 리스트 반환 성공",
                eventService.getEventList(showAll, eventCategory, eventPeriod, pageable)));
  }

  // 상세보기
  @Operation(summary = "이벤트 상세보기", description = "이벤트 상세보기 api")
  @GetMapping("/{event-id}")
  public ResponseEntity<BaseResponse<EventResponse>> getEventDetail(
      @PathVariable(value = "event-id") Long id) {
    return ResponseEntity.status(200)
        .body(BaseResponse.success(200, "이벤트 상세보기 반환 성공", eventService.getEventDetail(id)));
  }

  // 제목으로 검색
  @Operation(summary = "이벤트 제목으로 검색", description = "사용자가 이벤트 제목으로 검색을 요청하는 API")
  @GetMapping("/search/{title}")
  public List<EventResponse> searchEvents(@PathVariable String title) {
    return eventService.searchEvents(title);
  }

  @Operation(summary = "이벤트 생성", description = "관리자가 이벤트 업로드를 요청하는 API")
  @PostMapping
  public ResponseEntity<BaseResponse<EventResponse>> createEvent(
      @RequestBody @Valid CreateEventRequest createEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "SHOW") @RequestParam
          EventCategory eventCategory) {

    return ResponseEntity.status(201)
        .body(
            BaseResponse.success(
                201, "이벤트 생성 성공", eventService.createEvent(createEventRequest, eventCategory)));
  }

  @Operation(summary = "이벤트 수정", description = "관리자가 이벤트 수정을 요청하는 api")
  @PutMapping("/{event-id}")
  public ResponseEntity<BaseResponse<EventResponse>> updateEvent(
      @PathVariable(value = "event-id") Long id,
      @RequestBody @Valid UpdateEventRequest updateEventRequest,
      @Parameter(description = "이벤트 카테고리", example = "ETC") @RequestParam
          EventCategory eventCategory) {

    return ResponseEntity.status(200)
        .body(
            BaseResponse.success(
                200, "이벤트 수정 성공", eventService.updateEvent(id, updateEventRequest, eventCategory)));
  }

  @Operation(summary = "이벤트 삭제", description = "관리자가 이벤트를 삭제를 요청하는 api")
  @DeleteMapping("/{event-id}")
  public ResponseEntity<BaseResponse<Void>> deleteEvent(@PathVariable(value = "event-id") Long id) {
    eventService.deleteEvent(id);
    return ResponseEntity.status(200).body(BaseResponse.success(204, "이벤트 삭제 성공", null));
  }

  private Pageable validatePageable(Integer pageNum, Integer pageSize) {
    if (pageNum == null || pageNum < 1) {
      throw new CustomException(PageErrorStatus.PAGE_NOT_FOUND);
    }
    if (pageSize == null || pageSize < 1) {
      throw new CustomException(PageErrorStatus.PAGE_SIZE_ERROR);
    }
    return PageRequest.of(pageNum - 1, pageSize);
  }
}
