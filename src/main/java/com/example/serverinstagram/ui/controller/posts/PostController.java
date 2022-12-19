package com.example.serverinstagram.ui.controller.posts;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.post.service.PostService;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.infrastructure.constants.AppConstants;
import com.example.serverinstagram.ui.dto.PagedResponse;
import com.example.serverinstagram.ui.dto.like.LikeCountResponse;
import com.example.serverinstagram.ui.dto.like.LikeResponse;
import com.example.serverinstagram.ui.dto.post.request.PostRequestDto;
import com.example.serverinstagram.ui.dto.post.response.PostResponseDto;
import com.example.serverinstagram.ui.dto.savedPost.response.SavedPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    @GetMapping()
    public PagedResponse<PostResponseDto> getAllPosts(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postService.getAllPosts(page, size, currentUser);
    }

    @PostMapping()
    public ResponseEntity<?> createPost(@ModelAttribute PostRequestDto postRequestDto, @RequestParam("image") MultipartFile image) throws Exception {
        try {

            return postService.createPost(postRequestDto, image);
        } catch (Exception e) {
            throw new Exception(e.getCause() + e.getMessage());
        }
    }

    @PostMapping("{postId}/like")
    public ResponseEntity<LikeResponse> addPostLike(@PathVariable Long postId) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.addPostLike(postId, currentUser));
    }

    @GetMapping("{postId}/like/exists")
    public ResponseEntity<LikeResponse> isPostLikedByCurrentUser(@PathVariable Long postId)
    {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.checkIfPostLiked(postId, currentUser));
    }

    @GetMapping("{postId}/like/count")
    public LikeCountResponse postLikeCount(@PathVariable Long postId)
    {
        return postService.likeCount(postId);
    }

    @PostMapping("{postId}/save")
    public SavedPostResponse savePost(@PathVariable Long postId)
    {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postService.savePostForUser(postId, currentUser);
    }


}
