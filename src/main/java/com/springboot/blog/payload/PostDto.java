package com.springboot.blog.payload;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
