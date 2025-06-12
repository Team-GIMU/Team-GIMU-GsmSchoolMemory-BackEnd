package com.gsm.domain.comment.entity;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.comment.presentation.dto.request.EditCommentRequest;
import com.gsm.domain.user.entity.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @NotNull
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime editedDate;

    public void update(EditCommentRequest editCommentRequest) {
        this.content = editCommentRequest.getContent();
    }
}
