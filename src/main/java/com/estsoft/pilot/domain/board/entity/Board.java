package com.estsoft.pilot.domain.board.entity;

import com.estsoft.pilot.domain.base.BaseTimeEntity;
import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private BoardStatus status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;

    @Column(name = "view_cnt", nullable = false)
    private Integer viewCnt;

    @Column(name = "group_no", nullable = false)
    private Long groupNo;

    @Column(name = "group_seq", nullable = false)
    private Integer groupSeq;

    @Column(nullable = false)
    private Integer indent;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title,
                 String content,
                 BoardStatus status,
                 Integer viewCnt,
                 Long groupNo,
                 Integer groupSeq,
                 Integer indent) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.viewCnt = viewCnt;
        this.groupNo = groupNo;
        this.groupSeq = groupSeq;
        this.indent = indent;
    }

    public void changeGroupNo() {
        this.groupNo = this.id;
    }

    public void updateBoardInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateBoardStatus(BoardStatus status) {
        this.status = status;
    }

    public void plusViewCnt() {
        this.viewCnt++;
    }

    public boolean isDeleted() {
        return BoardStatus.DELETE == this.status;
    }
}
