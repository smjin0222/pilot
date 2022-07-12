package com.estsoft.pilot.domain.comment.service;

import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.comment.entity.Comment;
import com.estsoft.pilot.domain.comment.repository.CommentRepository;
import com.estsoft.pilot.global.error.exception.BusinessException;
import com.estsoft.pilot.global.error.exception.EntityNotFoundException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Board board, Comment comment) {
        comment.setBoard(board);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long boardId, Long commentId, String content) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_COMMENT));

        if(!findComment.getBoard().getId().equals(boardId)) {
            throw new BusinessException(ErrorCode.MISMATCHED_BOARD_COMMENT_ID);
        }

        findComment.changeContent(content);
    }

    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_COMMENT));

        if(!findComment.getBoard().getId().equals(boardId)) {
            throw new BusinessException(ErrorCode.MISMATCHED_BOARD_COMMENT_ID);
        }

        commentRepository.delete(findComment);
    }
}
