package com.gsm.domain.notice.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNoticeRequest {

    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;

    private String content;
}
