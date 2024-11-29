package com.ymd.businesshousegame.repository;

import com.ymd.businesshousegame.entity.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiceRepository extends JpaRepository<Dice, Integer> {

}
