package com.gsm.domain.notice.serivce;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.presentation.dto.response.NoticeListResponse;
import com.gsm.domain.notice.presentation.dto.response.NoticeResponse;
import com.gsm.domain.notice.repository.NoticeRepository;
import com.gsm.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ReadOnlyService
public class ListNoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeListResponse execute() {

        List<Notice> notices = noticeRepository.findAll();

        return NoticeListResponse.builder()
                .noticeList(
                        notices.stream()
                                .map(NoticeResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
