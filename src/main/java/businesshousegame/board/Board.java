package businesshousegame.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Empty;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Board {

    private List<Cell> grid = new ArrayList<>();
    private Map<String, Integer> playersCurrentPositions = new HashMap<>();

    public Board(String cellPositionsStr) {
        String[] cellsAtpositions = cellPositionsStr.split(",");
        for (String cell : cellsAtpositions) {
            switch (cell) {
                case "E" -> grid.add(new Empty());
                case "J" -> grid.add(new Jail());
                case "H" -> grid.add(new Hotel());
                case "T" -> grid.add(new Treasure());
            }
        }
    }

    public void handlePlayerMove(Player player) {
        playersCurrentPositions.put(player.getName(), player.getCurrPositionOnBoard());
        Cell currentPlayersCell = grid.get(player.getCurrPositionOnBoard());
        switch (currentPlayersCell) {
            case Jail jail -> player.deductMoney(jail.getPenality());
            case Treasure treasure -> player.addMoney(treasure.getTreasureValue());
            case Hotel hotel -> handleHotel(hotel, player);
            default -> System.out.print("");
        }
    }

    private void handleHotel(Hotel hotel, Player player) {
        if (hotel.getOwner() == null) {
            player.deductMoney(hotel.getWorth());
            hotel.setOwner(player.getName());
        } else {
            player.deductMoney(hotel.getRent());
        }
    }

    public Integer getMaxCellsInGrid() {
        return grid.size();
    }

}
