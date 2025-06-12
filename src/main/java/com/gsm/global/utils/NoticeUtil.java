package com.gsm.global.utils;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.exception.NoticeNotFoundException;
import com.gsm.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeUtil {

    private final NoticeRepository noticeRepository;

    public Notice findNoticeById(Long id) {

        return noticeRepository.findById(id)
                .orElseThrow(NoticeNotFoundException::new);
    }
}
