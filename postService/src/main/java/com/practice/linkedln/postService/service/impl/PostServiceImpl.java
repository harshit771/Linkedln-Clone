package com.practice.linkedln.postService.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.linkedln.postService.auth.AuthContextHolder;
import com.practice.linkedln.postService.client.ConnectionServiceClient;
import com.practice.linkedln.postService.dto.PersonDto;
import com.practice.linkedln.postService.dto.PostCreateRequestDto;
import com.practice.linkedln.postService.dto.PostDto;
import com.practice.linkedln.postService.entity.Post;
import com.practice.linkedln.postService.exception.ResourceNotFoundException;
import com.practice.linkedln.postService.repository.PostRepository;
import com.practice.linkedln.postService.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final ConnectionServiceClient connectionServiceClient;

    @Override
    public PostDto creatPost(PostCreateRequestDto postCreatRequestDto, Long userId) {
        log.info("PostService :: createPost method started ");
        Post post = modelMapper.map(postCreatRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        log.info("PostService :: createPost method ended ");
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        log.info("Getting post by Id with Id " + postId);

        Long userId = AuthContextHolder.getCurrentUserId();

        List<PersonDto> personDtoList = connectionServiceClient.getFistDegreeConnections(userId);
        log.info("personDtoList "+personDtoList.size());
        for (PersonDto personDto : personDtoList) {
            log.info("personDtoList "+personDto.getName());
        }
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id " + postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostOfUser(Long userId) {
        log.info("Getting all post of user with user Id " + userId);
        List<Post> postList = postRepository.findByUserId(userId);
        if(postList == null || postList.size() == 0){
            throw new ResourceNotFoundException("No post found related to user id "+userId);
        }
        return postList.stream().map((element) 
                    -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());
    }

}
