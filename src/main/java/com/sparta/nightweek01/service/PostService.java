package com.sparta.nightweek01.service;

import com.sparta.nightweek01.domain.Post;
import com.sparta.nightweek01.dto.PostRequestDto;
import com.sparta.nightweek01.dto.PostResponseDto;
import com.sparta.nightweek01.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public PostResponseDto updatepost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 게시글입니다.")
        );

        post.update(requestDto);

        return PostResponseDto.builder()
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getCotent())
                .author(post.getAuthor())
                .build();
    }

    @Transactional
    public String deletepost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 게시글입니다.")
        );

        postRepository.delete(post);

        return "게시글이 삭제되었습니다";
    }
}
