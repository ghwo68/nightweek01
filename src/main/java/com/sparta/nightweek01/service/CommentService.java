package com.sparta.nightweek01.service;

import com.sparta.nightweek01.dto.request.CommentRequestDto;
import com.sparta.nightweek01.dto.response.CommentResponseDto;
import com.sparta.nightweek01.dto.response.ResponseDto;
import com.sparta.nightweek01.entity.Comment;
import com.sparta.nightweek01.entity.Post;
import com.sparta.nightweek01.repository.CommentRepository;
import com.sparta.nightweek01.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public ResponseDto<?> createComment(Long postId, CommentRequestDto requestDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

//        Comment comment = new Comment(post, requestDto.getContent());
        Comment comment = requestDto.toEntity(post);
        Comment savedComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(savedComment);

        return ResponseDto.success("댓글이 작성되었습니다.", commentResponseDto );
    }

    public ResponseDto<?> readAllComment(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        List<Comment> comments = commentRepository.findAllByPost(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : comments){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        return ResponseDto.success("해당 게시글의 댓글을 전체조회합니다.", commentResponseDtoList);
    }

    @Transactional
    public ResponseDto<?> updateComment(Long commentId, CommentRequestDto requestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        comment.update(requestDto);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return ResponseDto.success("댓글이 수정되었습니다.", commentResponseDto);
    }

    @Transactional
    public ResponseDto<?> deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        commentRepository.delete(comment);

        return ResponseDto.success("댓글이 삭제되었습니다.", null);
    }
}
