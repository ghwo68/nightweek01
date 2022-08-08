package com.sparta.nightweek01.service;

import com.sparta.nightweek01.domain.Post;
import com.sparta.nightweek01.dto.PostRequestDto;
import com.sparta.nightweek01.dto.PostResponseDto;
import com.sparta.nightweek01.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .cotent(requestDto.getContent())
                .author(requestDto.getAuthor())
                .password(requestDto.getPassword())
                .build();
        postRepository.save(post);

        return PostResponseDto.builder()
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getCotent())
                .author(post.getAuthor())
                .build();
    }

    public List<PostResponseDto> readPostAll() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList){
            postResponseDto.add(
            PostResponseDto.builder()
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getCotent())
                    .author(post.getAuthor())
                    .build()
           );
        }
        return postResponseDto;
    }

    public PostResponseDto readPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 게시글입니다.")
        );
        return PostResponseDto.builder()
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getCotent())
                .author(post.getAuthor())
                .build();
    }
}
