package com.gsm.domain.notice.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditNoticeRequest {

    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다")
    private String content;
}
