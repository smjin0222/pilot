package com.estsoft.pilot.web.dashboard.dto;

import com.estsoft.pilot.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardListDto {

    private Long boardId;

    private String title;

    private Integer indent;

    private Integer viewCnt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static BoardListDto of(Board board) {
        BoardListDto boardListDto = null;

        if(board.isDeleted()) {
            boardListDto = BoardListDto.builder()
                    .boardId(board.getId())
                    .title("삭제 된 게시글입니다.")
                    .indent(0)
                    .viewCnt(0)
                    .createTime(board.getCreateTime())
                    .updateTime(board.getUpdateTime())
                    .build();
        } else {
            boardListDto = BoardListDto.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .indent(board.getIndent())
                    .viewCnt(board.getViewCnt())
                    .createTime(board.getCreateTime())
                    .updateTime(board.getUpdateTime())
                    .build();
        }

        return boardListDto;
    }
}
