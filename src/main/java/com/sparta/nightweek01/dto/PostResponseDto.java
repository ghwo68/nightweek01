package com.sparta.nightweek01.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class PostResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, Long id, String title, String content, String author) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
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
}
