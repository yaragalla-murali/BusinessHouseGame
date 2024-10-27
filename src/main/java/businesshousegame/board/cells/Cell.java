package businesshousegame.board.cells;

import businesshousegame.Player;

public interface Cell {

	default int handleCellLanding(Player player) {
		return 0;
	}
}
