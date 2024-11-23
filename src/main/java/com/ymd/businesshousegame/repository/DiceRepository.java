package com.ymd.businesshousegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.Dice;

public interface DiceRepository extends JpaRepository<Dice, Integer> {

}
