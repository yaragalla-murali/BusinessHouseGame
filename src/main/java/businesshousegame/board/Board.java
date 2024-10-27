package businesshousegame.board;

import businesshousegame.board.cells.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static List<Cell> grid = new ArrayList<>();


    public static Board setup(String cellPositionsStr) {
        String[] cellsAtpositions = cellPositionsStr.split(",");
        for (String cell : cellsAtpositions) {
            switch (cell) {
                case "E" -> grid.add(new Empty());
                case "J" -> grid.add(new Jail());
                case "H" -> grid.add(new Hotel());
                case "T" -> grid.add(new Treasure());
            }
        }
        return new Board();
    }

    public static List<Cell> getGrid() {
        return grid;
    }

}
