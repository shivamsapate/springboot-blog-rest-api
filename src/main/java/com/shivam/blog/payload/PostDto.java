package com.shivam.blog.payload;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id;

    //title should not be empty or null
    //title should have at least 2 charters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    //Post description should not be null
    //description should have at least 10 charters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    //Post description should not be null
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
