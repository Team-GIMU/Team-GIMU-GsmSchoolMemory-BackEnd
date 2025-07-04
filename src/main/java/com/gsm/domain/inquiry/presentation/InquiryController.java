package com.gsm.domain.inquiry.presentation;

import com.gsm.domain.inquiry.presentation.dto.request.InquirySendRequest;
import com.gsm.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import com.gsm.domain.inquiry.presentation.dto.response.DetailInquiryResponse;
import com.gsm.domain.inquiry.presentation.dto.response.ListInquiryResponse;
import com.gsm.domain.inquiry.service.*;
import com.gsm.global.annotation.RestRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestRequestService("/inquiry")
public class InquiryController {
    private final CreateInquiryService createInquiryService;
    private final ListInquiryService listInquiryService;
    private final GetInquiryDetailService getInquiryDetailService;
    private final InquiryApproveService inquiryApproveService;
    private final InquiryRefusalService inquiryRefusalService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid InquiryWriteRequest inquiryWriteRequest) {
        createInquiryService.execute(inquiryWriteRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListInquiryResponse> findAll() {
        var list = listInquiryService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailInquiryResponse> findDetailOne(@PathVariable Long id) {
        DetailInquiryResponse oneFindById = getInquiryDetailService.execute(id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<Void> approveSend(@PathVariable Long id) {
        inquiryApproveService.execute(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/refusal/{id}")
    public ResponseEntity<Void> refusalSend(@PathVariable Long id, @RequestBody @Valid InquirySendRequest inquirySendRequest) {
        inquiryRefusalService.execute(id, inquirySendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
