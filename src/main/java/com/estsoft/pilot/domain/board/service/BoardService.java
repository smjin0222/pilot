package com.estsoft.pilot.domain.board.service;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.board.repository.BoardRepository;
import com.estsoft.pilot.global.error.exception.BusinessException;
import com.estsoft.pilot.global.error.exception.EntityNotFoundException;
import com.estsoft.pilot.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class  BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> searchBoardPage(String searchQuery, Pageable pageable) {
        return boardRepository.findBoardPage(searchQuery, pageable);
    }

    @Transactional
    public Board findBoardDetail(Long boardId) {
        Board findBoard = Optional.ofNullable(boardRepository.findBoardDetailById(boardId))
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_BOARD));

        findBoard.plusHit();

        return findBoard;
    }

    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_BOARD));
    }

    @Transactional
    public void saveBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        saveBoard.changeGroupNo();
    }

    @Transactional
    public void saveReply(Board board) {
        // 답글의 위치에 따라 기존 답글들의 위치 조정
        boardChangePosition(board.getGroupNo(), board.getGroupOrder());
        boardRepository.save(board);
    }

    private void boardChangePosition(Long groupNo, Integer groupSeq) {
        Integer maxGroupSeq = boardRepository.findMaxGroupSeqByGroupNo(groupNo);
        if(groupSeq <= maxGroupSeq) {
            boardRepository.updateAllGroupSeq(groupNo, groupSeq);
        }
    }

    @Transactional
    public void updateBoard(Board board, String title, String content) {
        if(board.isDeleted()) {
            throw new BusinessException(ErrorCode.IS_DELETED_BOARD);
        }
        board.updateBoardInfo(title, content);
    }

    @Transactional
    public void deleteBoard(Board board) {
        if(board.isDeleted()) {
            throw new BusinessException(ErrorCode.IS_DELETED_BOARD);
        }
        board.updateBoardStatus(BoardStatus.DELETE);
    }
}
