package com.springboot.blog.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotNull
    @Size(min = 2,message = "Post title should have at least 2 character")
    private String title;

    @NotNull
    @Size(min = 10,message = "Post description should have at least 10 character")
    private String description;

    @NotNull
    private String content;
    private Set<CommentDto> comments;
}
