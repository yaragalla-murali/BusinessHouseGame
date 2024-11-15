package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.Dice;

public interface DiceRepository extends JpaRepository<Dice, Integer> {

}
