package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Treasure implements Cell {
	
	private final BigDecimal treasureValue=new BigDecimal(200);

	@Override
	public String getName() {		
		return "Treasure";
	}

	public BigDecimal getTreasureValue() {
		return treasureValue;
	}

	

}
