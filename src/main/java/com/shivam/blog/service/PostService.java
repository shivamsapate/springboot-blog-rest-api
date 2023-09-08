package com.shivam.blog.service;

import com.shivam.blog.payload.PostDto;
import com.shivam.blog.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto dto , long id);

    void deletePost(long id);
}
