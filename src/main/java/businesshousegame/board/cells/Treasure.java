package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Treasure implements Cell {
	
	private final BigDecimal treasureValue=new BigDecimal(200);

	
	public BigDecimal getTreasureValue() {
		return treasureValue;
	}

	

}
