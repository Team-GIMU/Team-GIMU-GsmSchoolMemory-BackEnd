package com.gsm.domain.notice.entity;

import com.gsm.domain.notice.presentation.dto.request.EditNoticeRequest;
import com.gsm.domain.user.entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime editedDate;

    public void update(EditNoticeRequest editNoticeRequest) {
        this.title = editNoticeRequest.getTitle();
        this.content = editNoticeRequest.getContent();
    }

}
