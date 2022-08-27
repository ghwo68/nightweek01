package com.sparta.nightweek01.service;

import com.sparta.nightweek01.dto.request.PostRequestDto;
import com.sparta.nightweek01.dto.response.PostResponseDto;
import com.sparta.nightweek01.dto.response.PostsResponseDto;
import com.sparta.nightweek01.dto.response.ResponseDto;
import com.sparta.nightweek01.entity.Post;
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
    public Post createPost(PostRequestDto requestDto) {

        Post post = requestDto.toEntity();

//        Post post = Post.builder()
//                .title(requestDto.getTitle())
//                .cotent(requestDto.getContent())
//                .author(requestDto.getAuthor())
//                .password(requestDto.getPassword())
//                .build();

        if (requestDto.getTitle().isEmpty()){
            throw new IllegalArgumentException("제목을 입력해야 합니다.");
        }
        if (requestDto.getContent().isEmpty()){
            throw new IllegalArgumentException("내용을 입력해야 합니다.");
        }
        postRepository.save(post);

//        PostResponseDto responseDto = new PostResponseDto(savedPost);

        return post;
    }

    public List<PostsResponseDto> readPostAll() {

        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostsResponseDto> postsResponseDtoList  = new ArrayList<>();
        for (Post post : posts){
            postsResponseDtoList.add(
                    PostsResponseDto.fromEntity(post)
            );
//            PostsResponseDto postsResponseDto = new PostsResponseDto(post);
//            postsResponseDtoList.add(postsResponseDto);
        }

        return postsResponseDtoList;
    }

    public PostResponseDto readPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        PostResponseDto postResponseDto = PostResponseDto.fromEntity(post);

        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatepost(Long postId, PostRequestDto requestDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        if(!post.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

        if (requestDto.getTitle().isEmpty()){
            throw new IllegalArgumentException("제목을 입력해야 합니다.");
        }
        if (requestDto.getContent().isEmpty()){
            throw new IllegalArgumentException("내용을 입력해야 합니다.");
        }

        post.update(requestDto);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @Transactional
    public ResponseDto<?> deletepost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        postRepository.delete(post);

        return ResponseDto.success("게시글이 삭제되었습니다.", null);
    }
}
