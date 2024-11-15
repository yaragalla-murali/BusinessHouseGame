package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.Cell;

public interface CellRepository extends JpaRepository<Cell, Integer> {

}
