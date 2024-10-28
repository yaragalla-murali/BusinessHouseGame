package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import businesshousegame.Dice;

public class DiceTest {

	@Test
	public void diceSetup() {
		Dice dice = new Dice("6,7,4");
		assertEquals(6, dice.getDice());
		assertEquals(7, dice.getDice());
		assertEquals(4, dice.getDice());
	}
}
