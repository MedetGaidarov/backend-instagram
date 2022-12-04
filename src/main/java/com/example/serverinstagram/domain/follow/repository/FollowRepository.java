package com.example.serverinstagram.domain.follow.repository;


import com.example.serverinstagram.domain.follow.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowerId(Long followerId);

}
