package com.estsoft.pilot.api.dashboard.dto;

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

    private String writer;

    private Integer viewCnt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static BoardListDto of(Board board) {
        BoardListDto boardListDto = null;

        if(board.isDeleted()) {
            boardListDto = BoardListDto.builder()
                    .boardId(board.getId())
                    .title("삭제 된 게시글입니다.")
                    .writer("")
                    .indent(0)
                    .viewCnt(0)
                    .createTime(board.getCreateTime())
                    .updateTime(board.getUpdateTime())
                    .build();
        } else {
            boardListDto = BoardListDto.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .writer(board.getMember().getName())
                    .indent(board.getIndent())
                    .viewCnt(board.getHit())
                    .createTime(board.getCreateTime())
                    .updateTime(board.getUpdateTime())
                    .build();
        }

        return boardListDto;
    }
}
