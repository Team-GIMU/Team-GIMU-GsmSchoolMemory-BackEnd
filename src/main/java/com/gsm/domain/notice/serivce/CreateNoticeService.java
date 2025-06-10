package com.gsm.domain.notice.serivce;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.presentation.dto.request.CreateNoticeRequest;
import com.gsm.domain.notice.repository.NoticeRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.util.UserUtil;
import com.gsm.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RollbackService
public class CreateNoticeService {

    private final UserUtil userUtil;

    private final NoticeRepository noticeRepository;

    public void execute(CreateNoticeRequest createNoticeRequest){

        User user = userUtil.currentUser();

        Notice notice = Notice.builder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .name(user.getName())
                .user(user)
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);
    }
}