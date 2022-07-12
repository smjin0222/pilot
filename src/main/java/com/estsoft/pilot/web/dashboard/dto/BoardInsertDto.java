package com.estsoft.pilot.web.dashboard.dto;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BoardInsertDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @NotNull(message = "groupNo은 필수값입니다.")
    private Long groupNo;

    @NotNull(message = "groupSeq은 필수값입니다.")
    private Integer groupSeq;

    @NotNull(message = "indent는 필수값입니다.")
    private Integer indent;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .status(BoardStatus.NORMAL)
                .viewCnt(0)
                .groupNo(groupNo)
                .groupSeq(groupSeq)
                .indent(indent)
                .build();
    }
}
