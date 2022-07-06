package com.estsoft.pilot.domain.board.entity;

import com.estsoft.pilot.domain.base.BaseTimeEntity;
import com.estsoft.pilot.domain.board.constant.BoardStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_status", nullable = false)
    private BoardStatus boardStatus;

    @Column(name = "view_cnt", nullable = false)
    private Integer viewCnt;

    @Column(nullable = false)
    private Integer groups;

    @Column(nullable = false)
    private Integer seq;

    @Column(nullable = false)
    private Integer indent;
}
