package com.gsm.domain.inquiry.service;

import com.gsm.domain.inquiry.entity.Inquiry;
import com.gsm.domain.inquiry.presentation.dto.response.InquiryResponse;
import com.gsm.domain.inquiry.presentation.dto.response.ListInquiryResponse;
import com.gsm.domain.inquiry.repository.InquiryRepository;
import com.gsm.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class ListInquiryService {

    private final InquiryRepository inquiryRepository;

    public ListInquiryResponse execute() {

        List<Inquiry> inquiries = inquiryRepository.findAll();

        return ListInquiryResponse.builder()
                .inquiryList(
                        inquiries.stream()
                                .map(InquiryResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
