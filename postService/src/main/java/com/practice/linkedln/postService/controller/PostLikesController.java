package com.practice.linkedln.postService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.linkedln.postService.service.PostLikeService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/likes")
@RestController
@RequiredArgsConstructor
public class PostLikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePost(@PathVariable Long postId){
        postLikeService.unLikePost(postId);
        return ResponseEntity.noContent().build();
    }

}
