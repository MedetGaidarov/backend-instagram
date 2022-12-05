package com.example.serverinstagram.domain.post.service;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.domain.follow.repository.FollowRepository;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.post.repository.PostRepository;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.infrastructure.exception.ResourceNotFoundException;
import com.example.serverinstagram.infrastructure.mapper.ModelMapper;
import com.example.serverinstagram.infrastructure.service.FileStorageService;
import com.example.serverinstagram.ui.dto.DefaultResponseDto;
import com.example.serverinstagram.ui.dto.PagedResponse;
import com.example.serverinstagram.ui.dto.post.request.PostRequestDto;
import com.example.serverinstagram.ui.dto.post.response.PostResponseDto;
import com.example.serverinstagram.ui.dto.post.response.SavedPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class PostServiceImpl implements PostService {

    private final FollowRepository followRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

//    private final FileStorageService fileStorageService;
    // TODO: do filestorageservice

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PagedResponse<PostResponseDto> getAllPosts(int page, int size, UserPrincipal currentUser) {

        List<Follow> followingList = followRepository.findAllByFollowerId(currentUser.getId());

        List<Long> followingIdsList = new ArrayList<>();
        followingList.forEach(follow -> followingIdsList.add(follow.getFollowing().getId()));


        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "createdAt");
        Page<Post> posts = postRepository.findAllByFollowedUsers(followingIdsList, pageable);

        if (posts.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(),
                    posts.getNumber(), posts.getSize(), posts.getTotalElements(),
                    posts.getTotalPages(), posts.isLast());
        }

        // Map Posts to PostResponses containing photos and post creator details
        Map<Long, User> creatorMap = getPostCreatorMap(posts.getContent());

        List<PostResponseDto> postResponses = posts.map(
                post -> ModelMapper.mapPostToPostResponse(post, creatorMap.get(post.getCreatedBy()))
        ).getContent();

        return new PagedResponse<>(postResponses, posts.getNumber(),
                posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());

    }

    @Override
    public ResponseEntity<?> createPost(PostRequestDto postRequestDto, MultipartFile image) throws Exception {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() ->
        {
            throw new IllegalArgumentException("WTF");
        });


//        String fileName = fileStorageService.storeFile(image);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("posts/images")
//                .path(fileName)
//                .toUriString();

        Post post = new Post();
        post.setDescription(postRequestDto.getDescription());
        post.setImagePath("sdfsdsdsdfsd");
        post.setCreatedBy(user.getId());
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{postId}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new DefaultResponseDto("Success", "Post created"));

    }

//    @Override
//    public SavedPostResponse savePostForUser(Long postId, UserPrincipal currentUser) {
//        Post post = postRepository.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post", "id", postId));
//        User user = userRepository.getOne(currentUser.getId());
//        // TODO: stopped here!
//
//
//    }


    public Map<Long, User> getPostCreatorMap(List<Post> posts) {
        // Get Poll Creator details of the given list of polls
        List<Long> creatorIds = posts.stream()
                .map(Post::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        return creators.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
