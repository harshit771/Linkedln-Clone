package com.practice.linkedln.postService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.linkedln.postService.dto.PostCreatRequestDto;
import com.practice.linkedln.postService.dto.PostDto;
import com.practice.linkedln.postService.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreatRequestDto postCreatRequestDto ,
                                        HttpServletRequest httpServletRequest){
        PostDto postDto = postService.creatPost(postCreatRequestDto , 1L);

        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        
    }


}
