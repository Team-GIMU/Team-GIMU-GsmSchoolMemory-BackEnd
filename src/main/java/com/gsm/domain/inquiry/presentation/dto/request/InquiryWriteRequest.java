package com.gsm.domain.inquiry.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryWriteRequest {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    private String content;

    @NotBlank(message = "글 종류는 필수 선택값입니다.")
    private String inquiryType;
}
