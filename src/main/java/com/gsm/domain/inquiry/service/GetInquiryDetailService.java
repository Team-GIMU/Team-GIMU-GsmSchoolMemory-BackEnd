package com.gsm.domain.inquiry.service;

import com.gsm.domain.inquiry.entity.Inquiry;
import com.gsm.domain.inquiry.exception.InquiryNotFoundException;
import com.gsm.domain.inquiry.presentation.dto.response.DetailInquiryResponse;
import com.gsm.domain.inquiry.repository.InquiryRepository;
import com.gsm.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class GetInquiryDetailService {

    private final InquiryRepository inquiryRepository;

    public DetailInquiryResponse execute(Long id) {

        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(InquiryNotFoundException::new);

        return DetailInquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .createdDate(inquiry.getCreatedDate())
                .inquiryType(inquiry.getInquiryType())
                .name(inquiry.getName())
                .build();
    }
}
