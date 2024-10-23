package businesshousegame.board.cells;

import java.math.BigDecimal;

public class Hotel implements Cell {
	
	private final BigDecimal worth=new BigDecimal(200);
	private final BigDecimal rent=new BigDecimal(50);
	private String owner;

	

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getWorth() {
		return worth;
	}

	public BigDecimal getRent() {
		return rent;
	}

	

}
