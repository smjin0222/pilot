package com.estsoft.pilot.domain.board.repository;

import com.estsoft.pilot.domain.board.constant.BoardStatus;
import com.estsoft.pilot.domain.board.entity.Board;
import com.estsoft.pilot.domain.comment.constant.CommentStatus;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.estsoft.pilot.domain.board.entity.QBoard.board;
import static com.estsoft.pilot.domain.comment.entity.QComment.comment;
import static org.springframework.util.StringUtils.hasText;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Board> findBoardPage(String searchQuery, Pageable pageable) {
        List<Board> contents = queryFactory
                .selectFrom(board)
                .where(ExpressionUtils.or(
                                titleLike(searchQuery),
                                contentLike(searchQuery))
                )
                .orderBy(board.groupNo.desc(), board.groupSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(board.count())
                .from(board)
                .where(ExpressionUtils.or(
                                titleLike(searchQuery),
                                contentLike(searchQuery))
                )
                .fetchOne();

        return new PageImpl<>(contents, pageable, totalCount);
    }

    @Override
    public Board findBoardDetailById(Long boardId) {
        return queryFactory
                .selectFrom(board)
                .leftJoin(board.comments, comment)
                .fetchJoin()
                .where(
                        boardIdEq(boardId)
                )
                .orderBy(comment.createTime.asc())
                .fetchOne();
    }

    @Override
    public Integer findMaxGroupSeqByGroupNo(Long groupNo) {
        return queryFactory
                .select(board.groupSeq.max())
                .from(board)
                .where(groupNoEq(groupNo))
                .fetchOne();
    }

    @Override
    public void updateAllGroupSeq(Long groupNo, Integer groupSeq) {
        queryFactory
                .update(board)
                .set(board.groupSeq, board.groupSeq.add(1))
                .where(
                        groupNoEq(groupNo),
                        groupSeqGoe(groupSeq)
                )
                .execute();
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return boardId != null ? board.id.eq(boardId) : null;
    }

    private BooleanExpression titleLike(String searchQuery) {
        return hasText(searchQuery) ? board.title.like("%" + searchQuery + "%") : null;
    }

    private BooleanExpression contentLike(String searchQuery) {
        return hasText(searchQuery) ? board.content.like("%" + searchQuery + "%") : null;
    }

    private BooleanExpression boardStatusEq(BoardStatus boardStatus) {
        return boardStatus != null ? board.status.eq(boardStatus) : null;
    }

    private BooleanExpression groupNoEq(Long groupNo) {
        return groupNo != null ? board.groupNo.eq(groupNo) : null;
    }

    private BooleanExpression groupSeqGoe(Integer groupSeq) {
        return groupSeq != null ? board.groupSeq.goe(groupSeq) : null;
    }

    private BooleanExpression commentStatusEq(CommentStatus commentStatus) {
        return commentStatus != null ? comment.status.eq(commentStatus) : null;
    }
}
