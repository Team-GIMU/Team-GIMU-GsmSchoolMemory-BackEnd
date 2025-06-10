package com.gsm.domain.inquiry.presentation.dto.response;

import com.gsm.domain.inquiry.enums.InquiryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DetailInquiryResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private InquiryType inquiryType;

    private String name;
}

