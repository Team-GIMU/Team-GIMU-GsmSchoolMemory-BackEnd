package com.gsm.domain.comment.presentation.controller;

import com.gsm.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.gsm.domain.comment.presentation.dto.request.EditCommentRequest;
import com.gsm.domain.comment.presentation.dto.response.CommentResponse;
import com.gsm.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        CommentResponse response = commentService.createComment(createCommentRequest, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}")
    public List<CommentResponse> getComments(@PathVariable Long boardId) {
        return commentService.getComments(boardId);
    }

    @PatchMapping("/{commentId}")
    public void editComment(@PathVariable Long commentId, @RequestBody EditCommentRequest request) {
        commentService.editComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
