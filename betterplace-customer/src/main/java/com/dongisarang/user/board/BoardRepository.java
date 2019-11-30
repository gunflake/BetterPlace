package com.dongisarang.user.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        QBoard board = QBoard.board;

        builder.and(board.boardNo.gt(0));

        return builder;
    }

    /*List<Board> findByBoardNoGreaterThanAndBoardTypeEqualsAndTitleContainingOrContentContainingOrderByBoardNoDesc(
            Integer boardNo,
            Integer boardType,
            String title,
            String content,
            Pageable paging
    );

    List<Board> findByBoardNoGreaterThanAndBoardTypeNotAndTitleContainingOrContentContainingOrderByBoardNoDesc(
            Integer boardNo,
            Integer boardType,
            String title,
            String content,
            Pageable paging
    );*/
}
