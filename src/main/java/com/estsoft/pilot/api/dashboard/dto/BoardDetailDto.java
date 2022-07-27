package com.estsoft.pilot.api.dashboard.dto;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class BoardDetailDto {

    private Long boardId;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime createTime;

    private BoardStatus status;

    private Long groupNo;

    private Integer groupSeq;

    private Integer indent;

    private Integer viewCnt;

    private List<CommentDto> comments;

    public static BoardDetailDto of(Board board, String name) {
        List<CommentDto> comments = List.of();
        BoardDetailDto boardDetailDto = null;

        if(board.isDeleted()) {
            boardDetailDto = BoardDetailDto.builder()
                    .boardId(board.getId())
                    .title("삭제 된 게시글입니다.")
                    .content("삭제 된 게시글입니다.")
                    .writer("")
                    .createTime(board.getCreateTime())
                    .status(board.getStatus())
                    .groupNo(board.getGroupNo())
                    .groupSeq(board.getGroupOrder())
                    .indent(board.getIndent())
                    .viewCnt(board.getHit())
                    // .comments(comments)
                    .build();
        } else {
            // comments = CommentDto.of(board.getComments());
            boardDetailDto = BoardDetailDto.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(name)
                    .createTime(board.getCreateTime())
                    .status(board.getStatus())
                    .groupNo(board.getGroupNo())
                    .groupSeq(board.getGroupOrder())
                    .indent(board.getIndent())
                    .viewCnt(board.getHit())
                    // .comments(comments)
                    .build();
        }

        return boardDetailDto;
    }
}
