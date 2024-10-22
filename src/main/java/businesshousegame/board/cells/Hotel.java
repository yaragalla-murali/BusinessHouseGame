package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Hotel implements Cell {

    public static final BigDecimal worth = new BigDecimal(200);
    public static final BigDecimal rent = new BigDecimal(50);
    private String owner;

    @Override
    public String getName() {
        return "Hotel";
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
