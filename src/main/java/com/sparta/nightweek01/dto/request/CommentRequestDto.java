package com.sparta.nightweek01.dto.request;


import com.sparta.nightweek01.entity.Comment;
import com.sparta.nightweek01.entity.Post;

public class CommentRequestDto {
    private String content;

//    public CommentRequestDto() {
//    }

//    public CommentRequestDto(Post post,String content) {
//
//        this.content = content;
//    }

    public String getContent() {
        return content;
    }

    public Comment toEntity(Post post){

        return new Comment(
                post,
                this.content
        );
    }
}
