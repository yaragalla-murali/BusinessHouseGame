package businesshousegame;

import java.util.ArrayList;
import java.util.List;

import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Board {

	private final List<Cell> grid = new ArrayList<>();

	public Board(String totalCellCordsAsStr) {
		String[] cellCords = totalCellCordsAsStr.split(",");
		for (String cell : cellCords) {
			switch (cell) {
			case "E" -> grid.add(null);
			case "J" -> grid.add(new Jail());
			case "T" -> grid.add(new Treasure());
			case "H" -> grid.add(new Hotel());
			}
		}
	}

	public List<Cell> getGrid() {
		return grid;
	}

}
