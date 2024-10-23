package businesshousegame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dice {

    private Queue<Integer> diceoutputs = new LinkedList<>();


    public Dice(String diceoutputStr) {
        String[] diceOutputs = diceoutputStr.split(",");
        Arrays.stream(diceOutputs).forEach(diceoutput -> {
            Integer diceOutputIntValue = Integer.parseInt(diceoutput);
            diceoutputs.add(diceOutputIntValue);
        });
    }

    public Integer giveDiceOutput() {
        return diceoutputs.poll();
    }
}
