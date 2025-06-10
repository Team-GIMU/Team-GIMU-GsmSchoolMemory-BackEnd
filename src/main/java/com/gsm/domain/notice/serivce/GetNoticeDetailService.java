package com.gsm.domain.notice.serivce;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.presentation.dto.response.NoticeDetailResponse;
import com.gsm.global.annotation.ReadOnlyService;
import com.gsm.global.utils.NoticeUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class GetNoticeDetailService {

    private final NoticeUtil noticeUtil;

    public NoticeDetailResponse execute(Long id) {

        Notice notice = noticeUtil.findNoticeById(id);

        return NoticeDetailResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .editedDate(notice.getEditedDate())
                .build();
    }
}
