package com.sparta.nightweek01.controller;

import com.sparta.nightweek01.domain.Post;
import com.sparta.nightweek01.dto.PostRequestDto;
import com.sparta.nightweek01.dto.PostResponseDto;
import com.sparta.nightweek01.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/post")
    private PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/post")
    private List<PostResponseDto> readPostAll(){
        return postService.readPostAll();
    }

    @GetMapping("/api/post/{id}")
    private PostResponseDto readPost(@PathVariable Long id){
        return postService.readPost(id);
    }
}
