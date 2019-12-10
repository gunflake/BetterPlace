package com.dongisarang.user.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.EntityManager;
import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {
//TODO 중복 로직 제거, keyword(제목, 내용) 검색 수정
    default Predicate makePredicateNotice(Integer type, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        QBoard board = QBoard.board;

        builder.and(board.boardType.eq(0)).and(board.boardNo.gt(0));

        if((keyword != null && !keyword.isEmpty())) {
            builder.and(
                    board.title.like("%" + keyword + "%")
                            .or(
                                    board.content.like("%" + keyword + "%")
                            )
            );
        }

        return builder;
    }

    default Predicate makePredicateInfo(Integer type, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        QBoard board = QBoard.board;

        if((type == null || type == 0)) {
            builder.and(board.boardType.between(1, 5))
                    .and(board.boardNo.gt(0));
        } else {
            System.out.println("what is type" + type);
                builder.and(board.boardType.eq(type))
                        .and(board.boardNo.gt(0));
        }

        if((keyword != null && !keyword.isEmpty())) {
            builder.and(
                    board.title.like("%" + keyword + "%")
                            .or(
                                    board.content.like("%" + keyword + "%")
                            )
            );
        }

        return builder;
    }
}
