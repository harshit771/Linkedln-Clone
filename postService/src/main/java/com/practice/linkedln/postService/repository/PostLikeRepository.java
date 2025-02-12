package com.practice.linkedln.postService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.linkedln.postService.entity.PostLikes;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikes,Long>{

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);

}
