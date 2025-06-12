package com.gsm.domain.inquiry.service;

import com.gsm.domain.inquiry.entity.Inquiry;
import com.gsm.domain.inquiry.enums.InquiryType;
import com.gsm.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import com.gsm.domain.inquiry.repository.InquiryRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.util.UserUtil;
import com.gsm.global.annotation.RollbackService;
import com.gsm.global.webhook.util.DiscordUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RollbackService
@RequiredArgsConstructor
public class CreateInquiryService {

    private final UserUtil userUtil;

    private final InquiryRepository inquiryRepository;

    private final DiscordUtil discordUtil;

    public void execute(InquiryWriteRequest inquiryWriteRequest) {

        User user = userUtil.currentUser();

        Inquiry inquiry = Inquiry.builder()
                .title(inquiryWriteRequest.getTitle())
                .content(inquiryWriteRequest.getContent())
                .name(user.getName())
                .user(user)
                .inquiryType(InquiryType.from(inquiryWriteRequest.getInquiryType()))
                .createdDate(LocalDateTime.now())
                .build();

        inquiryRepository.save(inquiry);

        sendDiscordMessage(inquiryWriteRequest);
    }

    private void sendDiscordMessage(InquiryWriteRequest inquiryWriteRequest) {

        String inquiryMessage = discordUtil.createInquiryMessage(inquiryWriteRequest.getTitle(), inquiryWriteRequest.getInquiryType());

        discordUtil.sendDiscordMessage(
                inquiryMessage
        );
    }
}
