package com.example.serverinstagram.domain.follow.repository;

import com.example.serverinstagram.domain.follow.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

}
