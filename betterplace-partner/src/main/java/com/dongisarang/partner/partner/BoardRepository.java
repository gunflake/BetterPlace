package com.dongisarang.partner.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {
}