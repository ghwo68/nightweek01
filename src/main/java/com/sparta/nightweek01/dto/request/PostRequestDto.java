package com.sparta.nightweek01.dto.request;

import com.sparta.nightweek01.entity.Post;

public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;

//    public PostRequestDto(String title, String content, String author, String password) {
//        this.title = title;
//        this.content = content;
//        this.author = author;
//        this.password = password;
//    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }

    /*Dto를 Entity로 변환시켜주는 메소드
      이 메소드가 없다면 service에서 변환을 시켜줘야하는데
      이렇게 Dto안에 메소드를 만들면 변경이 있을 때 여기서 수정을 하면 됨!
     */
    public Post toEntity(){

        return new Post(
                this.title,
                this.content,
                this.author,
                this.password
        );
    }
}
