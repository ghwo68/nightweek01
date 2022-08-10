package com.sparta.nightweek01.service;

import com.sparta.nightweek01.domain.Post;
import com.sparta.nightweek01.dto.PostRequestDto;
import com.sparta.nightweek01.dto.PostResponseDto;
import com.sparta.nightweek01.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        if (requestDto.getTitle().isEmpty()){
            throw new RuntimeException("제목을 입력해야 합니다.");
        }
        if (requestDto.getContent().isEmpty()){
            throw new RuntimeException("내용을 입력해야 합니다.");
        }
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

// 왜 이코드는 실행이 안됬을까?       if (requestDto.getPassword() != post.getPassword())
        if(!Objects.equals(requestDto.getPassword(), post.getPassword())){
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
        }

        if (requestDto.getTitle().isEmpty()){
            throw new RuntimeException("제목을 입력해야 합니다.");
        }
        if (requestDto.getContent().isEmpty()){
            throw new RuntimeException("내용을 입력해야 합니다.");
        }

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
