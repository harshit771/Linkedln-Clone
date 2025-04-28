package com.practice.linkedln.uploaderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practice.linkedln.uploaderservice.service.UploaderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
@RestController
public class UploaderController {

    private final UploaderService uploaderService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam MultipartFile file){
        log.info("inside uploader controller");
        String url = uploaderService.upload(file);
        return ResponseEntity.ok(url);
    }

}
