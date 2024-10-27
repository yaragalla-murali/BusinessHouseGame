package businesshousegame.board.cells;

import businesshousegame.Player;

/**
 * This is a tagging interface
 */
public interface Cell {

    default Player handleLandMoney(Player player) {
        return player;
    }

}
 