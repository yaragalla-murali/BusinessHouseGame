package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.Cell;

public interface CellDao extends JpaRepository<Cell, Integer> {

}
