package com.estsoft.pilot.domain.board.repository;

import com.estsoft.pilot.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> findBoardPage(String searchQuery, Pageable pageable);

    Integer findMaxGroupSeqByGroupNo(Long groupNo);

    void updateAllGroupSeq(Long groupNo, Integer groupSeq);

    Board findBoardDetailById(Long boardId);
}
