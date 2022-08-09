package com.sparta.nightweek01.controller;

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
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/post")
    public List<PostResponseDto> readPostAll(){
        return postService.readPostAll();
    }

    @GetMapping("/api/post/{id}")
    public PostResponseDto readPost(@PathVariable Long id){
        return postService.readPost(id);
    }

    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatepost(id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public String deletePost(@PathVariable Long id){
        return postService.deletepost(id);
    }

}
