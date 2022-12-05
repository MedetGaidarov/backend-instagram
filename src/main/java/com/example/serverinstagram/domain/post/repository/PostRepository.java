package com.example.serverinstagram.domain.post.repository;

import com.example.serverinstagram.domain.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select p from Post p where p.createdBy in :followingIds")
    Page<Post> findAllByFollowedUsers(List<Long> followingIds, Pageable pageable);
}
