package com.estsoft.pilot.api.dashboard.service;

import com.estsoft.pilot.api.dashboard.dto.*;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.board.service.BoardService;
import com.estsoft.pilot.domain.comment.service.CommentService;
import com.estsoft.pilot.domain.member.entity.Member;
import com.estsoft.pilot.domain.member.service.MemberService;
import com.estsoft.pilot.domain.rabbitmq.Sender;
import com.estsoft.pilot.global.error.exception.AuthorizationException;
import com.estsoft.pilot.global.error.exception.BusinessException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DashBoardService {

    private final BoardService boardService;

    private final CommentService commentService;

    private final MemberService memberService;

    private final Sender rabbitMqSender;

    public BoardDtoPage getBoardDtoPage(String searchQuery, int page, int size) {
        List<Board> boards = boardService.findBoardOfPage(searchQuery, page, size);
        Long totalElements = boardService.getTotalCount();

        List<BoardDto> boardDtos = new ArrayList<>();
        boards.forEach((board -> boardDtos.add(BoardDto.of(board))));

        BoardDtoPage boardDtoPage = BoardDtoPage.builder()
                .content(boardDtos)
                .size(20)
                .totalElements(totalElements)
                .build();

        return boardDtoPage;
    }

    @Transactional
    public BoardDetailDto getBoardDetail(Long boardId) {
        Board findBoard = boardService.findBoardDetail(boardId);
        Member findMember = memberService.findMemberByEmail(findBoard.getEmail());

        // 큐에 메시지 전달
        rabbitMqSender.send("boardId:" + boardId);

        return BoardDetailDto.of(findBoard, findMember.getName());
    }

    @Transactional
    public void registerBoard(BoardInsertDto boardInsertDto, String email) {
        Member loginMember = memberService.findMemberByEmail(email);
        boardService.saveBoard(boardInsertDto.toEntity(loginMember.getEmail(), loginMember.getName()));
    }

    @Transactional
    public void registerReplyBoard(BoardReplyDto boardReplyDto, String email) {
        Board parentBoard = boardService.findBoardById(boardReplyDto.getBoardId());
        Member loginMember = memberService.findMemberByEmail(email);

        if(parentBoard.isDeleted()) {
            throw new BusinessException(ErrorCode.DELETED_BOARD_NOT_REPLY);
        }

        boardService.saveReply(boardReplyDto.toEntity(loginMember.getEmail(), loginMember.getName()));
    }

    @Transactional
    public void modifyBoard(Long boardId, BoardModifyDto boardModifyDto, String email) {
        Board findBoard = boardService.findBoardById(boardId);

        if(!email.equals(findBoard.getEmail())) {
            throw new AuthorizationException(ErrorCode.HAS_NOT_AUTHORIZATION);
        }

        boardService.updateBoard(findBoard,
                boardModifyDto.getTitle(),
                boardModifyDto.getContent());
    }

    @Transactional
    public void removeBoard(Long boardId, String email) {
        Board findBoard = boardService.findBoardById(boardId);

        if(!email.equals(findBoard.getEmail())) {
            throw new AuthorizationException(ErrorCode.HAS_NOT_AUTHORIZATION);
        }

        boardService.deleteBoard(findBoard);
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