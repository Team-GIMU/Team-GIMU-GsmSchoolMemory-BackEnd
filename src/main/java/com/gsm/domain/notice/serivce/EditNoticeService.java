package com.gsm.domain.notice.serivce;

import com.gsm.domain.notice.entity.Notice;
import com.gsm.domain.notice.presentation.dto.request.EditNoticeRequest;
import com.gsm.global.annotation.RollbackService;
import com.gsm.global.utils.NoticeUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RollbackService
public class EditNoticeService {

    private final NoticeUtil noticeUtil;

    public void execute(Long id, EditNoticeRequest editNoticeRequest){

        Notice notice = noticeUtil.findNoticeById(id);

        notice.update(editNoticeRequest);
    }
}
