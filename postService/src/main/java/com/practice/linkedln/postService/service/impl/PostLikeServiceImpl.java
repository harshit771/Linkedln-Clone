package com.practice.linkedln.postService.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.linkedln.postService.entity.PostLikes;
import com.practice.linkedln.postService.exception.BadRequestException;
import com.practice.linkedln.postService.exception.ResourceNotFoundException;
import com.practice.linkedln.postService.repository.PostLikeRepository;
import com.practice.linkedln.postService.repository.PostRepository;
import com.practice.linkedln.postService.service.PostLikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void likePost(Long postId) {
        log.info("Like post with id " + postId);
        Long userId = 1L;
        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (hasAlreadyLiked) {
            throw new BadRequestException("Can not like post again");
        }

        PostLikes postLikes = new PostLikes();
        postLikes.setUserId(userId);
        postLikes.setPostId(postId);
        postLikeRepository.save(postLikes);

        // TODO : send notification
    }

    @Override
    @Transactional
    public void unLikePost(Long postId) {
        log.info("UnLike post with id " + postId);
        Long userId = 1L;
        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        
         boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

         if (!hasAlreadyLiked) {
                    throw new BadRequestException("Can not unlike post that you have not liked");
        }
        
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
    }

}
