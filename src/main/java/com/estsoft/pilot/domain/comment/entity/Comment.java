package com.estsoft.pilot.domain.comment.entity;

import com.estsoft.pilot.domain.base.BaseTimeEntity;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.comment.constant.CommentStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CommentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Comment(String content,
                   CommentStatus status) {
        this.content = content;
        this.status = status;
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void updateCommentStatus(CommentStatus status) {
        this.status = status;
    }
}
