package com.sparta.nightweek01.repository;

import com.sparta.nightweek01.entity.Comment;
import com.sparta.nightweek01.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
