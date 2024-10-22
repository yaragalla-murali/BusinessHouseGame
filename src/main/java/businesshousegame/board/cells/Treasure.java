package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Treasure implements Cell {

    public static final BigDecimal treasureValue = new BigDecimal(200);

    @Override
    public String getName() {
        return "Treasure";
    }


}
