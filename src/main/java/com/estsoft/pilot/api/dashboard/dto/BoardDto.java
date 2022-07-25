package com.estsoft.pilot.api.dashboard.dto;

import com.estsoft.pilot.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {

    private Long boardId;

    private String title;

    private Integer indent;

    private String writer;

    private Integer viewCnt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static BoardDto of(Board board) {
        BoardDto boardDto = null;

        if(board.isDeleted()) {
            boardDto = BoardDto.builder()
                    .boardId(board.getId())
                    .title("삭제 된 게시글입니다.")
                    .writer("")
                    .indent(0)
                    .viewCnt(0)
                    .createTime(null)
                    .updateTime(null)
                    .build();
        } else {
            boardDto = BoardDto.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
                    .indent(board.getIndent())
                    .viewCnt(board.getHit())
                    .createTime(board.getCreateTime())
                    .updateTime(board.getUpdateTime())
                    .build();
        }

        return boardDto;
    }
}
