package com.gsm.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private Long id;

    private String content;

    private String username;

    private LocalDateTime createDate;

    private LocalDateTime editedDate;
    public LocalDateTime getDisplayTime() {
        return editedDate != null ? editedDate : createDate;
    }

    public static CommentResponse toResponse(com.gsm.domain.comment.entity.Comment comment) {
        return CommentResponse.builder()
                .id(comment.getCommentId())
                .content(comment.getContent())
                .username(comment.getUser().getName())
                .createDate(comment.getCreateDate())
                .editedDate(comment.getEditedDate())
                .build();
    }
}
