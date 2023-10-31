package com.shivam.blog.service.impl;

import com.shivam.blog.entity.Post;
import com.shivam.blog.exception.ResourceNotFoundException;
import com.shivam.blog.payload.PostDto;
import com.shivam.blog.payload.PostResponse;
import com.shivam.blog.repository.PostRepository;
import com.shivam.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert postDto to entity
        Post post = mapToEntity(postDto);
        // save post
        Post savedPost = postRepository.save(post);
        //convert to dto
        return mapToDto(savedPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        // get content from page object
        List<Post> postsList = page.getContent();
        List<PostDto> content = postsList.stream().map(this::mapToDto).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setContent(content);
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setLast(page.isLast());
        postResponse.setTotalPages(page.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", String.valueOf(id)));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return mapToDto(post);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepository.delete(post);
    }

    //converted entity to dto
    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }

    //converted dto to entity
    private Post mapToEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }
}
