package com.shivam.blog.service;

import com.shivam.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto dto , long id);

    void deletePost(long id);
}
