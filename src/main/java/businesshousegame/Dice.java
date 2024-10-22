package businesshousegame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dice {

    private final Map<String, List<DiceOutput>> diceOutputForPlayers = new HashMap<>();


    public Dice(List<Player> players, String diceOutputStr) {
        String[] diceOutputs = diceOutputStr.split(",");
        int[] count = {0};
        for (int set = 0; set < 10; set++) {
            players.forEach(player -> {
                String name = player.getName();
                Optional<List<DiceOutput>> nameKey = Optional.ofNullable(diceOutputForPlayers.get(name));
                if (nameKey.isPresent()) {
                    diceOutputForPlayers.get(name).add(new DiceOutput(Integer.parseInt(diceOutputs[count[0]])));
                } else {
                    diceOutputForPlayers.put(name, List.of(new DiceOutput(Integer.parseInt(diceOutputs[count[0]]))));
                }
                count[0] = count[0] + 1;
            });
        }

    }

    public Integer giveDiceOutputForCurrentPlayer(Player player) {
        String name = player.getName();
        List<DiceOutput> currentPlayerOutputs = diceOutputForPlayers.get(name);
        for (DiceOutput output : currentPlayerOutputs) {
            if (!output.isCompleted()) {
                output.setCompleted(true);
                return output.getOutput();
            }

        }
        return null;
    }
}
