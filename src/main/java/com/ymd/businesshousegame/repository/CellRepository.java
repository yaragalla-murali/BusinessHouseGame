package com.ymd.businesshousegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.Cell;

public interface CellRepository extends JpaRepository<Cell, Integer> {

}
