package com.practice.linkedln.postService.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.linkedln.postService.dto.PostCreatRequestDto;
import com.practice.linkedln.postService.dto.PostDto;
import com.practice.linkedln.postService.entity.Post;
import com.practice.linkedln.postService.repository.PostRepository;
import com.practice.linkedln.postService.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public PostDto creatPost(PostCreatRequestDto postCreatRequestDto,Long userId) {
        log.info("PostService :: createPost method started ");
        Post post = modelMapper.map(postCreatRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        log.info("PostService :: createPost method ended ");
        return modelMapper.map(post, PostDto.class);
    }

}
