package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.DiceOutput;

public interface DiceOutputDao extends JpaRepository<DiceOutput, Integer> {

}
