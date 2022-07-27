package com.estsoft.pilot.domain.board.repository;

import com.estsoft.pilot.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findBoardPaging(int page, int size);

    Long getCount();

    Integer findMaxGroupSeqByGroupNo(Long groupNo);

    void updateAllGroupSeq(Long groupNo, Integer groupSeq);

    Board findBoardDetailById(Long boardId);

    Page<Board> findBoardPage(String searchQuery, Pageable pageable);
}
