package com.practice.linkedln.postService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "uploaderService" , path = "/uploads/file" , url = "${UPLOADER_SERVICE_URI:}")
public interface UploaderClient {

     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     ResponseEntity<String> upload(@RequestPart MultipartFile file);

}
