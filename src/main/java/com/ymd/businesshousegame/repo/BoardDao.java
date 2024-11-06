package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymd.businesshousegame.entity.Board;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer> {

}
