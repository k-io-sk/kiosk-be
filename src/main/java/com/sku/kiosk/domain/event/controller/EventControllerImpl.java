/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sku.kiosk.domain.event.dto.request.CreateEventRequest;
import com.sku.kiosk.domain.event.dto.request.UpdateEventRequest;
import com.sku.kiosk.domain.event.dto.response.DetailEventResponse;
import com.sku.kiosk.domain.event.dto.response.ListEventResponse;
import com.sku.kiosk.domain.event.dto.response.MainEventResponse;
import com.sku.kiosk.domain.event.dto.response.WrapperMainEventResponse;
import com.sku.kiosk.domain.event.entity.EventCategory;
import com.sku.kiosk.domain.event.entity.EventPeriod;
import com.sku.kiosk.domain.event.service.EventService;
import com.sku.kiosk.global.exception.CustomException;
import com.sku.kiosk.global.page.exception.PageErrorStatus;
import com.sku.kiosk.global.page.response.PageResponse;
import com.sku.kiosk.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

  private final EventService eventService;

  @Override
  public ResponseEntity<BaseResponse<List<WrapperMainEventResponse<MainEventResponse>>>>
      getMainPage() {
    return ResponseEntity.status(200)
        .body(BaseResponse.success(200, "메인 화면 이벤트 리스트 반환 성공", eventService.getMainEventList()));
  }

  @Override
  public ResponseEntity<BaseResponse<PageResponse<ListEventResponse>>> getEventList(
      @RequestParam EventCategory eventCategory,
      @RequestParam EventPeriod eventPeriod,
      @RequestParam(required = false) String keyword,
      @RequestParam Integer pageNum,
      @RequestParam Integer pageSize) {

    Pageable pageable = validatePageable(pageNum, pageSize);

    return ResponseEntity.status(200)
        .body(
            BaseResponse.success(
                200,
                "전체 이벤트 리스트 반환 성공",
                eventService.getEventList(eventCategory, eventPeriod, keyword, pageable)));
  }

  @Override
  public ResponseEntity<BaseResponse<DetailEventResponse>> getEventDetail(
      @PathVariable(value = "event-id") Long eventId) {
    return ResponseEntity.status(200)
        .body(BaseResponse.success(200, "이벤트 상세보기 반환 성공", eventService.getEventDetail(eventId)));
  }

  @Override
  public ResponseEntity<BaseResponse<DetailEventResponse>> createEvent(
      @RequestBody @Valid CreateEventRequest createEventRequest,
      @RequestParam EventCategory eventCategory) {

    return ResponseEntity.status(201)
        .body(
            BaseResponse.success(
                201, "이벤트 생성 성공", eventService.createEvent(createEventRequest, eventCategory)));
  }

  @Override
  public ResponseEntity<BaseResponse<DetailEventResponse>> updateEvent(
      @PathVariable(value = "event-id") Long eventId,
      @RequestBody @Valid UpdateEventRequest updateEventRequest,
      @RequestParam EventCategory eventCategory) {

    return ResponseEntity.status(200)
        .body(
            BaseResponse.success(
                200,
                "이벤트 수정 성공",
                eventService.updateEvent(eventId, updateEventRequest, eventCategory)));
  }

  @Override
  public ResponseEntity<BaseResponse<Void>> deleteEvent(
      @PathVariable(value = "event-id") Long eventId) {
    eventService.deleteEvent(eventId);
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
