package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Jail implements Cell {
	
	public static final BigDecimal penality=new BigDecimal(150);

	@Override
	public String getName() {		
		return "Jail";
	}

	

}
