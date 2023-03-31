package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPost(int pageNo, int pageSize
            ,String sortBy
            ,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto,long id);

    String deletePost(long id);
}
