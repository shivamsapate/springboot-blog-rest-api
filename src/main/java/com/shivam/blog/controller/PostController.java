package com.shivam.blog.controller;

import com.shivam.blog.payload.PostDto;
import com.shivam.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    //get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    //update post with it's id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }

    // Deleting post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
