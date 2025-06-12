package com.gsm.domain.comment.service;

import com.gsm.domain.board.entity.Board;
import com.gsm.domain.board.repository.BoardRepository;
import com.gsm.domain.comment.entity.Comment;
import com.gsm.domain.comment.exception.CommentContentTooLongException;
import com.gsm.domain.auth.exception.UserNotFoundException;
import com.gsm.domain.board.exception.BoardNotFoundException;
import com.gsm.domain.comment.exception.CommentNotFoundException;

import com.gsm.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.gsm.domain.comment.presentation.dto.request.EditCommentRequest;
import com.gsm.domain.comment.presentation.dto.response.CommentResponse;
import com.gsm.domain.comment.repository.CommentRepository;
import com.gsm.domain.user.entity.User.User;
import com.gsm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.process.internal.UserTypeMutabilityPlanAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public CommentResponse createComment(CreateCommentRequest request, String username) {
        if (request.getContent().length() > 100) {
            throw new CommentContentTooLongException();
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);

        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(BoardNotFoundException::new);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .board(board)
                .build();

        Comment saved = commentRepository.save(comment);

        return CommentResponse.toResponse(saved);
    }

    public List<CommentResponse> getComments(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);

        return commentRepository.findAllByBoard(board).stream()
                .map(CommentResponse::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public void editComment(Long commentId, EditCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        comment.update(request);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
    }
}
