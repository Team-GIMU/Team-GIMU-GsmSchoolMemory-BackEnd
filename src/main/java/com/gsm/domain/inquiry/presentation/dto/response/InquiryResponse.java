package com.gsm.domain.inquiry.presentation.dto.response;

import com.gsm.domain.inquiry.entity.Inquiry;
import com.gsm.domain.inquiry.enums.InquiryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class InquiryResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private InquiryType inquiryType;

    public static InquiryResponse toResponse(Inquiry inquiry) {

        return InquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .createdDate(inquiry.getCreatedDate())
                .inquiryType(inquiry.getInquiryType())
                .build();
    }
}
