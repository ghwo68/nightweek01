package com.sparta.nightweek01.entity;

import com.sparta.nightweek01.dto.request.PostRequestDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
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
    private String password;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    public Post(String title, String cotent, String author, String password) {
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

    public String getPassword() {
        return password;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.cotent = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }


}
