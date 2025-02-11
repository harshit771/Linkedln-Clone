package com.practice.linkedln.postService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.linkedln.postService.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

}
