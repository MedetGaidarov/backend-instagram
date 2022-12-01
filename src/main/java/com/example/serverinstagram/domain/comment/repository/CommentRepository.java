package com.example.serverinstagram.domain.comment.repository;


import com.example.serverinstagram.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
