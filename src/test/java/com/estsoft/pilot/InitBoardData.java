package com.estsoft.pilot;

import com.estsoft.pilot.api.dashboard.dto.BoardInsertDto;
import com.estsoft.pilot.domain.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InitBoardData {

    @Autowired
    private BoardService boardService;

    @Test
    void initBoardData() {

        for(int i=1; i<975; i++) {
            BoardInsertDto boardInsertDto = new BoardInsertDto();
            boardInsertDto.setTitle("제목 " + i);
            boardInsertDto.setContent("내용 " + i);
            boardInsertDto.setGroupNo(0L);
            boardInsertDto.setGroupSeq(0);
            boardInsertDto.setIndent(0);

            // boardService.saveBoard(boardInsertDto.toEntity());
        }
    }
}