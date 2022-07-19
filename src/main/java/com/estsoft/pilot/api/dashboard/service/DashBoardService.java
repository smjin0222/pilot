package com.estsoft.pilot.api.dashboard.service;

import com.estsoft.pilot.api.dashboard.dto.*;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.board.service.BoardService;
import com.estsoft.pilot.domain.comment.service.CommentService;
import com.estsoft.pilot.domain.member.entity.Member;
import com.estsoft.pilot.domain.member.service.MemberService;
import com.estsoft.pilot.global.error.exception.AuthorizationException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
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
public class DashBoardService {

    private final BoardService boardService;

    private final CommentService commentService;

    private final MemberService memberService;

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
    public void registerBoard(BoardInsertDto boardInsertDto, String email) {
        Member loginMember = memberService.findMemberByEmail(email);
        boardService.saveBoard(boardInsertDto.toEntity(loginMember));
    }

    @Transactional
    public void registerReplyBoard(BoardReplyDto boardReplyDto, String email) {
        Member loginMember = memberService.findMemberByEmail(email);
        boardService.saveReply(boardReplyDto.toEntity(loginMember));
    }

    @Transactional
    public void modifyBoard(Long boardId, BoardModifyDto boardModifyDto, String email) {
        Member loginMember = memberService.findMemberByEmail(email);
        Board findBoard = boardService.findBoardById(boardId);

        if(loginMember != findBoard.getMember()) {
            throw new AuthorizationException(ErrorCode.HAS_NOT_AUTHORIZATION);
        }

        boardService.updateBoard(findBoard,
                boardModifyDto.getTitle(),
                boardModifyDto.getContent());
    }

    @Transactional
    public void removeBoard(Long boardId, String email) {
        Member loginMember = memberService.findMemberByEmail(email);
        Board findBoard = boardService.findBoardById(boardId);

        if(loginMember != findBoard.getMember()) {
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