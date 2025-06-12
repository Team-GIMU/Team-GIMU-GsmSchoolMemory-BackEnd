package com.gsm.domain.comment.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 100, message = "댓글은 100자를 초과할 수 없습니다.")

    private String content;

    private Long boardId;
}
