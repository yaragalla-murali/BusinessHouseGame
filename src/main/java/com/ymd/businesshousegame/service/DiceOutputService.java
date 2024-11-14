package com.ymd.businesshousegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.DiceOutput;
import com.ymd.businesshousegame.repo.DiceOutputDao;

@Service
public class DiceOutputService {
	@Autowired
	private DiceOutputDao diceOutputDao;

	public DiceOutput giveDice(Dice dice, int sequenceNumber) {
		List<DiceOutput> outputs = diceOutputDao.findBySequenceAndDice(sequenceNumber, dice);
		return outputs.getFirst();
	}

}
