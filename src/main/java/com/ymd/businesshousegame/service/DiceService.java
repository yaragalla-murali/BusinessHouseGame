package com.ymd.businesshousegame.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.entity.DiceOutput;
import com.ymd.businesshousegame.repo.DiceDao;
import com.ymd.businesshousegame.repo.DiceOutputDao;

@Service
public class DiceService {

	@Autowired
	private DiceDao diceRepo;

	@Autowired
	private DiceOutputDao diceOutputDao;

	public Dice setup(String diceOutputsStr) {
		String[] diceOutputStrArray = diceOutputsStr.split(",");
		List<DiceOutput> diceOutputs = new ArrayList<>();
		Dice dice = new Dice();
		dice = diceRepo.save(dice);
		diceRepo.flush();
		int counter = 0;
		for (String diceOutputStr : diceOutputStrArray) {
			DiceOutput diceOutput = new DiceOutput();
			Integer output = Integer.parseInt(diceOutputStr);
			diceOutput.setOutput(output);
			diceOutput.setSequence(counter);
			diceOutput.setDice(dice);
			diceOutputs.add(diceOutput);
			counter = counter + 1;
		}

		diceOutputDao.saveAll(diceOutputs);
		return dice;

	}
}
