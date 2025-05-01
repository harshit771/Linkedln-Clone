package com.practice.linkedln.postService.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.linkedln.postService.auth.AuthContextHolder;
import com.practice.linkedln.postService.dto.PostCreateRequestDto;
import com.practice.linkedln.postService.dto.PostDto;
import com.practice.linkedln.postService.exception.ApiError;
import com.practice.linkedln.postService.exception.BadRequestException;
import com.practice.linkedln.postService.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/core")
public class PostController {

    private final PostService postService;
    private final ObjectMapper modelMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDto> createPost(@RequestPart("post") String postJson,
                                               @RequestPart("file") MultipartFile file ){

        PostCreateRequestDto postCreatRequestDto;
        try {
            postCreatRequestDto = modelMapper.readValue(postJson, PostCreateRequestDto.class);
            log.info("content value :{} "+postCreatRequestDto.getContent());                             
        PostDto postDto = postService.creatPost(postCreatRequestDto,file);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        } catch (JsonMappingException e) {
            log.error("Exception JsonMappingException {} "+e.getLocalizedMessage());
            throw new BadRequestException("Error "+e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            log.error("Exception JsonProcessingException {} "+e.getLocalizedMessage());
            throw new BadRequestException("Error "+e.getLocalizedMessage());
        }
        
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        log.info("User ID {} "+AuthContextHolder.getCurrentUserId());
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostOfUser(@PathVariable Long userId){
        List<PostDto> posts = postService.getAllPostOfUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

}
