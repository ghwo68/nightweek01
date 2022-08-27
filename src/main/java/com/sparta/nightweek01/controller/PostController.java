package com.sparta.nightweek01.controller;

import com.sparta.nightweek01.dto.request.PostRequestDto;
import com.sparta.nightweek01.dto.response.PostResponseDto;
import com.sparta.nightweek01.dto.response.PostsResponseDto;
import com.sparta.nightweek01.dto.response.ResponseDto;
import com.sparta.nightweek01.entity.Post;
import com.sparta.nightweek01.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto requestDto){

        Post post = postService.createPost(requestDto);

        return ResponseEntity.ok()
                .body(post);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostsResponseDto>> readPostAll(){

        List<PostsResponseDto> responseDtoList = postService.readPostAll();

        return ResponseEntity.ok()
                .body(responseDtoList);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<?> readPost(@PathVariable Long postId){

        PostResponseDto responseDto = postService.readPost(postId);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){

        postService.updatepost(postId, postRequestDto);

        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){

        postService.deletepost(postId);

        return ResponseEntity.ok()
                .build();
    }

}
