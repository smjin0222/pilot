package com.estsoft.pilot.domain.board.entity;

import com.estsoft.pilot.domain.base.BaseTimeEntity;
import com.estsoft.pilot.domain.board.constant.BoardStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "IDX_GROUP", columnList = "group_no ASC, group_order ASC")
})
@Entity
public class Board extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BoardStatus status;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String writer;

    @Column(name = "hit", nullable = false)
    private Integer hit;

    @Column(name = "group_no", nullable = false)
    private Long groupNo;

    @Column(name = "group_order", nullable = false)
    private Integer groupOrder;

    @Column(nullable = false)
    private Integer indent;

//    @OneToMany(mappedBy = "board")
//    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title,
                 String content,
                 String email,
                 String writer,
                 BoardStatus status,
                 Integer hit,
                 Long groupNo,
                 Integer groupOrder,
                 Integer indent) {
        this.title = title;
        this.content = content;
        this.email = email;
        this.writer = writer;
        this.status = status;
        this.hit = hit;
        this.groupNo = groupNo;
        this.groupOrder = groupOrder;
        this.indent = indent;
    }

    public void changeGroupNo() {
        this.groupNo = this.id * -1;
    }

    public void updateBoardInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateBoardStatus(BoardStatus status) {
        this.status = status;
    }

    public void plusHit() {
        this.hit++;
    }

    public boolean isDeleted() {
        return BoardStatus.DELETE == this.status;
    }
}
