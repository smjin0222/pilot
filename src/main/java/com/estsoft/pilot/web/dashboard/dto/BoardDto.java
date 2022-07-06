package com.estsoft.pilot.web.dashboard.dto;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardDto {

    private Long boardId;

    private String title;

    private String content;

    private BoardStatus boardStatus;

    private Integer grp;

    private Integer seq;

    private Integer indent;

    public static BoardDto of(Board board) {
        return BoardDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardStatus(board.getBoardStatus())
                .grp(board.getGroups())
                .seq(board.getSeq())
                .indent(board.getIndent())
                .build();
    }
}
