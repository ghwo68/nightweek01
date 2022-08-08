package com.sparta.nightweek01.dto;

import lombok.Builder;

@Builder
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private Long password;

    public PostRequestDto(String title, String content, String author, Long password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
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

    public Long getPassword() {
        return password;
    }
}
