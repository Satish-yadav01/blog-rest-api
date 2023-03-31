package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comments;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentsRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentsRepository commentsRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentsRepository commentsRepository
            , PostRepository postRepository
            ,ModelMapper mapper) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId,
                                    CommentDto commentDto) {
        //map from commentDto to Comments
        Comments comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException
                        ("post", "id", postId));

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to DB
        Comments newComment = commentsRepository.save(comment);

        //map from Comments to CommentDto

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsById(long postId) {
        //retrieve comments from postId
        List<Comments> comments = commentsRepository
                .findByPostId(postId);
        List<CommentDto> collect = comments.stream().map(
                (comment -> mapToDto(comment))
        ).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto getCommentsById(long postId,
                                      long commentId) {
        //retrieve post entity by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Post", "id", postId);
                        }
                );

        //retrieve comments by Id
        Comments comments = commentsRepository
                .findById(commentId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Comment", "id", commentId);
                        }
                );

        //logic for checking whether comment belongs to post or not
        if(!comments.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST
                    ,"comment does not belong to post");
        }

        return mapToDto(comments);

    }

    @Override
    public CommentDto updateComment(long postId,long commentId ,CommentDto commentDto) {
        //retrieve post entity by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Post", "id", postId);
                        }
                );

        //retrieve comments by Id
        Comments comments = commentsRepository
                .findById(commentId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Comment", "id", commentId);
                        }
                );

        //logic for checking whether comment belongs to post or not
        if(!comments.getPost().getId().equals(post.getId())){
             throw new BlogApiException(HttpStatus.BAD_REQUEST
                    ,"comment does not belong to post");
        }

        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setBody(commentDto.getBody());
        Comments updatedComment = commentsRepository.save(comments);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrieve post entity by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Post", "id", postId);
                        }
                );

        //retrieve comments by Id
        Comments comments = commentsRepository
                .findById(commentId)
                .orElseThrow(
                        () -> {
                            return new ResourceNotFoundException
                                    ("Comment", "id", commentId);
                        }
                );

        //logic for checking whether comment belongs to post or not
        if(!comments.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST
                    ,"comment does not belong to post");
        }

        commentsRepository.delete(comments);
    }

    private CommentDto mapToDto(Comments comment) {
        CommentDto commentDto = mapper.map(comment,CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comments mapToEntity(CommentDto commentDto) {
        Comments comment = mapper.map(commentDto,Comments.class);
//        Comments comment = new Comments();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return comment;
    }
}
