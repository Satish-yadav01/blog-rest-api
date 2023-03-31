package com.springboot.blog.repository;

import com.springboot.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByPostId(long postId);
}
