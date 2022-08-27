package com.sparta.nightweek01.dto.response;

import com.sparta.nightweek01.entity.Post;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private List<CommentResponseDto> commentList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getCotent();
        this.author = post.getAuthor();
        this.commentList = post.getCommentList().stream()
                .map(CommentResponseDto::new).collect(Collectors.toList());
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public List<CommentResponseDto> getCommentList() {
        return commentList;
    }

    public static PostResponseDto fromEntity(Post post) {

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getCotent(),
                post.getAuthor(),
                post.getCommentList().stream()
                        .map(CommentResponseDto::new).collect(Collectors.toList()),
                post.getCreatedAt(),
                post.getModifiedAt()
        );
    }

}
