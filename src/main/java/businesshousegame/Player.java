package businesshousegame;

import java.math.BigDecimal;

public class Player {

	private String name;
	private BigDecimal currentMoney=new BigDecimal(1000);
	private Integer currPositionOnBoard=0;
	
	public Player(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}
	public Integer getCurrPositionOnBoard() {
		return currPositionOnBoard;
	}
	public void setCurrPositionOnBoard(Integer currPositionOnBoard) {
		this.currPositionOnBoard = currPositionOnBoard;
	}
}
