package com.example.serverinstagram.domain.likes.repository;


import com.example.serverinstagram.domain.likes.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
