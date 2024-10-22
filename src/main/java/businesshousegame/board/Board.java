package businesshousegame.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Empty;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Board {

    private final List<Cell> grid = new ArrayList<>();
    private final Map<String, Integer> PlayersCurrentPositions = new HashMap<>();

    public void setup(String cellPositionsStr) {
        String[] cellsAtPositions = cellPositionsStr.split(",");
        for (String cell : cellsAtPositions) {
            grid.add(switch (cell) {
                case "E" -> new Empty();
                case "J" -> new Jail();
                case "H" -> new Hotel();
                case "T" -> new Treasure();
                default -> throw new IllegalArgumentException("Unexpected value: " + cell);
            });
        }
    }

    public Cell move(Player player, Integer diceOutput) {
        Integer playerPosition = player.getCurrPositionOnBoard();

        int playerCurrentPosition = playerPosition + diceOutput;
        if (playerCurrentPosition >= grid.size()) {
            playerCurrentPosition = playerCurrentPosition - grid.size();
        }
        player.setCurrPositionOnBoard(playerCurrentPosition);
        PlayersCurrentPositions.put(player.getName(), playerCurrentPosition);
        return grid.get(playerCurrentPosition);
    }
}
