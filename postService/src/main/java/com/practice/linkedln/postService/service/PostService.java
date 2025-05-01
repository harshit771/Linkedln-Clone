package com.practice.linkedln.postService.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.practice.linkedln.postService.dto.PostCreateRequestDto;
import com.practice.linkedln.postService.dto.PostDto;

public interface PostService {

    PostDto creatPost(PostCreateRequestDto postCreatRequestDto, MultipartFile file);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostOfUser(Long userId);

}
