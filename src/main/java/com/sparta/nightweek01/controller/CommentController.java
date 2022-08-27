package com.sparta.nightweek01.controller;

import com.sparta.nightweek01.dto.request.CommentRequestDto;
import com.sparta.nightweek01.dto.response.ResponseDto;
import com.sparta.nightweek01.service.CommentService;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comments/{postId}")
    public ResponseDto<?> createComment(@PathVariable Long postId,
                                        @RequestBody CommentRequestDto requestDto){

        return commentService.createComment(postId, requestDto);
    }

    @GetMapping("api/comments/{postId}")
    public ResponseDto<?> readAllComment(@PathVariable Long postId){

        return commentService.readAllComment(postId);
    }

    @PutMapping("/api/comments/{commentId}")
    public ResponseDto<?> updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentRequestDto requestDto){

        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long commentId){

        return commentService.deleteComment(commentId);
    }
}
