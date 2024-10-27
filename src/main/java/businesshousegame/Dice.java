package businesshousegame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dice {

    private static Queue<Integer> diceoutputs = new LinkedList<>();


    public static Dice setup(String diceoutputStr) {
    	String[] diceOutputs = diceoutputStr.split(",");
        Arrays.stream(diceOutputs).forEach(diceoutput -> {
            Integer diceOutputIntValue = Integer.parseInt(diceoutput);
            diceoutputs.add(diceOutputIntValue);
        });
        return new Dice();
    }

    public static Integer giveDiceOutput() {
        return diceoutputs.poll();
    }
}
