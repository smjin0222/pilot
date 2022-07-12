package com.estsoft.pilot.web.dashboard.service;

import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.board.service.BoardService;
import com.estsoft.pilot.domain.comment.service.CommentService;
import com.estsoft.pilot.web.dashboard.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardApiService {

    private final BoardService boardService;
    private final CommentService commentService;

    public Page<BoardListDto> getBoardDtoPage(BoardSearchDto boardSearchDto, Pageable pageable) {
        Page<Board> results = boardService.searchBoardPage(boardSearchDto.getSearchQuery(), pageable);
        List<BoardListDto> boardListDtos = results.map(BoardListDto::of)
                .stream()
                .toList();

        return new PageImpl<>(boardListDtos, pageable, results.getTotalElements());
    }

    @Transactional
    public BoardDetailDto getBoardDetail(Long boardId) {
        Board findBoard = boardService.findBoardDetail(boardId);
        return BoardDetailDto.of(findBoard);
    }

    @Transactional
    public Long registerBoard(BoardInsertDto boardInsertDto) {
        return boardService.saveBoard(boardInsertDto.toEntity());
    }

    @Transactional
    public Long registerReplyBoard(BoardReplyDto boardReplyDto) {
        return boardService.saveReply(boardReplyDto.toEntity());
    }

    @Transactional
    public Long modifyBoard(Long boardId, BoardModifyDto boardModifyDto) {
        Board updateBoard = boardService.updateBoard(
                boardId,
                boardModifyDto.getTitle(),
                boardModifyDto.getContent());

        return updateBoard.getId();
    }

    @Transactional
    public void removeBoard(Long boardId) {
        boardService.deleteBoard(boardId);
    }

    @Transactional
    public void registerComment(Long boardId, CommentInsertDto commentInsertDto) {
        Board findBoard = boardService.findBoardById(boardId);
        commentService.saveComment(findBoard, commentInsertDto.toEntity());
    }

    @Transactional
    public void modifyComment(Long boardId,
                              Long commentId,
                              CommentModifyDto commentModifyDto) {
        commentService.updateComment(boardId,
                commentId,
                commentModifyDto.getContent());
    }

    @Transactional
    public void removeComment(Long boardId, Long commentId) {
        commentService.deleteComment(boardId, commentId);
    }
}
