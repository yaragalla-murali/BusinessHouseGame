package com.ymd.businesshousegame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ymd.businesshousegame.entity.DiceOutput;
import java.util.List;
import com.ymd.businesshousegame.entity.Dice;

public interface DiceOutputRepository extends JpaRepository<DiceOutput, Integer> {

	List<DiceOutput> findBySequenceAndDice(int sequence, Dice dice);
}
