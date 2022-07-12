package com.estsoft.pilot.domain.board.service;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.board.repository.BoardRepository;
import com.estsoft.pilot.global.error.exception.EntityNotFoundException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> searchBoardPage(String searchQuery, Pageable pageable) {
        return boardRepository.findBoardPage(searchQuery, pageable);
    }

    @Transactional
    public Board findBoardDetail(Long boardId) {
        Board findBoard = boardRepository.findBoardDetailById(boardId);
        // querydsl 사용할 경우 JPA와 다르게 Optional 반환하지 않아 null 처리에 일관성이 없어짐..
        if(findBoard == null) {
            throw new EntityNotFoundException(ErrorCode.NOT_EXISTS_BOARD);
        }
        findBoard.plusViewCnt();

        return findBoard;
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_BOARD));
    }

    @Transactional
    public Long saveBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        saveBoard.changeGroupNo();
        return saveBoard.getId();
    }

    @Transactional
    public Long saveReply(Board board) {
        // 답글의 위치에 따라 기존 답글들의 위치 조정
        boardChangePosition(board.getGroupNo(), board.getGroupSeq());
        return boardRepository.save(board).getId();
    }

    private void boardChangePosition(Long groupNo, Integer groupSeq) {
        Integer maxGroupSeq = boardRepository.findMaxGroupSeqByGroupNo(groupNo);
        if(groupSeq <= maxGroupSeq) {
            boardRepository.updateAllGroupSeq(groupNo, groupSeq);
        }
    }

    @Transactional
    public Board updateBoard(Long boardId, String title, String content) {
        Board findBoard = findBoardById(boardId);
        findBoard.updateBoardInfo(title, content);
        return findBoard;
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_BOARD));

        findBoard.updateBoardStatus(BoardStatus.DELETE);
    }
}
