package com.gsm.domain.notice.presentation.dto.response;

import com.gsm.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class NoticeResponse {

    private Long id;

    private String title;

    private LocalDateTime createdDate;

    public static NoticeResponse toResponse(Notice notice) {

        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .createdDate(notice.getCreatedDate())
                .build();
    }
}
