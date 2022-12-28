package com.example.serverinstagram.ui.controller.comment;


import com.example.serverinstagram.domain.comment.model.Comment;
import com.example.serverinstagram.domain.comment.service.CommentService;
import com.example.serverinstagram.ui.dto.post.comment.request.CommentRequest;
import com.example.serverinstagram.ui.dto.post.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;



    @GetMapping()
    public ResponseEntity<?> getComments(@RequestParam("postId") Long postId) {
        CommentResponse body = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.ok(body);
    }


    @PostMapping()
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest)
    {
         Comment comment =  commentService.saveComment(commentRequest);
         return ResponseEntity.ok(comment);

    }

}
