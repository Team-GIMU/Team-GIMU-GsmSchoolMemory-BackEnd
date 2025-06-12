package com.gsm.domain.notice.serivce;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.repository.NoticeRepository;
import com.gsm.global.annotation.RollbackService;
import com.gsm.global.utils.NoticeUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RollbackService
public class DeleteNoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeUtil noticeUtil;

    public void execute(Long id){

        Notice notice = noticeUtil.findNoticeById(id);

        noticeRepository.delete(notice);
    }
}
