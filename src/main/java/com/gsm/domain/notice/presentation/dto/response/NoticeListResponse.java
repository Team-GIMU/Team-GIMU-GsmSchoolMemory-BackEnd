package com.gsm.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class NoticeListResponse {

    private List<NoticeResponse> noticeList;

}