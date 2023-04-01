package com.springboot.blog.payload;

import com.springboot.blog.entity.Comments;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    @NotEmpty(message = "name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10,message = "Comment body must be min of 10 character")
    private String body;
}
