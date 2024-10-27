package businesshousegame;

import java.util.LinkedList;
import java.util.Queue;

public class Dice {

	private Queue<Integer> diceOutputs = new LinkedList<>();

	public Dice(String diceOutputsAsStr) {
		String[] diceOutputAsStrArray = diceOutputsAsStr.split(",");
		for (String output : diceOutputAsStrArray) {
			diceOutputs.add(Integer.parseInt(output));
		}
	}

	public Integer getDice() {
		return diceOutputs.poll();
	}

}
