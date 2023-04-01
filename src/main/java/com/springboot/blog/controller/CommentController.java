package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment
            (@PathVariable("postId") long postId
            ,@Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>
                (commentService.createComment(postId, commentDto)
                        , HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public List<CommentDto> getCommentById(
            @PathVariable("postId") long postId) {
        List<CommentDto> commentsById =
                commentService.getCommentsById(postId);
        return commentsById;
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ){
        return ResponseEntity.ok(commentService
                .getCommentsById(postId,commentId));
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId,
            @Valid @RequestBody CommentDto commentDto
    ){
        CommentDto commentDto1 = commentService
                .updateComment(postId, commentId, commentDto);
        return ResponseEntity.ok(commentDto1);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("comment deleted successfully",
                HttpStatus.OK);
    }
}
