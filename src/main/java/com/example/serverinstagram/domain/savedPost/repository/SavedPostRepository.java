package com.example.serverinstagram.domain.savedPost.repository;

import com.example.serverinstagram.domain.savedPost.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SavedPostRepository  extends JpaRepository<SavedPost, Long> {
    @Query("Select sp from SavedPost sp where sp.post.id =:postId  and sp.user.id =:userId")
    SavedPost findByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}
