package com.sku.kiosk.domain.crawling.controller;

import com.sku.kiosk.domain.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crawling")
public class CrawlingController {

    private final CrawlingService crawlingService;

    @PostMapping("/update")
    public void updateCrawling() {
        crawlingService.updateCrawlingEvent();
    }
}
