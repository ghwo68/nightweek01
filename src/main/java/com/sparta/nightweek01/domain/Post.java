package com.sparta.nightweek01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.nightweek01.dto.PostRequestDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@Entity
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String cotent;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Long password;

    public Post(Long id, String title, String cotent, String author, Long password) {
        this.id = id;
        this.title = title;
        this.cotent = cotent;
        this.author = author;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCotent() {
        return cotent;
    }

    public String getAuthor() {
        return author;
    }

    public Long getPassword() {
        return password;
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.cotent = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }


}
