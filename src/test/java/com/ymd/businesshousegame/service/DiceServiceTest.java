package com.ymd.businesshousegame.service;

import com.ymd.businesshousegame.entity.Dice;
import com.ymd.businesshousegame.repository.DiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiceServiceTest {

	@Mock
	private DiceRepository diceRepo;

	@InjectMocks
	private DiceService diceService;

	@Test
	void testSetup() {
		String diceOutputsStr = "4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5";

		String[] diceOutputStrArray = diceOutputsStr.split(",");
		List<Integer> diceOutputs = new ArrayList<>();
		Dice dice = new Dice();
		for (String diceOutputStr : diceOutputStrArray) {
			Integer output = Integer.parseInt(diceOutputStr);
			diceOutputs.add(output);
		}
		dice.setOutputs(diceOutputs);

		when(diceRepo.save(any(Dice.class))).thenReturn(dice);

		Dice resultDice = diceService.setup(diceOutputsStr);

		assertNotNull(resultDice);
		for (int i = 0; i < dice.getOutputs().size(); i++) {
			assertEquals(dice.getOutputs().get(i), resultDice.getOutputs().get(i));
		}
	}


	@Test
	void testGiveDice() {
		String diceOutputsStr = "4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5";

		String[] diceOutputStrArray = diceOutputsStr.split(",");
		List<Integer> diceOutputs = new ArrayList<>();
		Dice dice = new Dice();
		for (String diceOutputStr : diceOutputStrArray) {
			Integer output = Integer.parseInt(diceOutputStr);
			diceOutputs.add(output);
		}
		dice.setOutputs(diceOutputs);
		dice.setLastUsedOutputIndex(1);

		when(diceRepo.save(dice)).thenReturn(dice);

		int output = diceService.giveDice(dice);

		assertEquals(4, output);
	}
}
