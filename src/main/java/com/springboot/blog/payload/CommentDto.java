package com.springboot.blog.payload;

import com.springboot.blog.entity.Comments;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
//    private Set<Comments> comments;
}
