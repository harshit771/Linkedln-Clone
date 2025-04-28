package com.practice.linkedln.uploaderservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploaderService {

    String upload(MultipartFile file);

}
