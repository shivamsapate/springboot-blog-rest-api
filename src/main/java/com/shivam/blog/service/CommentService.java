package com.shivam.blog.service;

import com.shivam.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto dto);

    void deleteComment(long postId, long commentId);

}
