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
	public void addMoney(BigDecimal money) {
		currentMoney = currentMoney.add(money);
	}
	
	public void deductMoney(BigDecimal money) {
		currentMoney = currentMoney.subtract(money);
	}
	
	public Integer getCurrPositionOnBoard() {
		return currPositionOnBoard;
	}
	public void move(Integer diceOutput,Integer maxCellsOnBoard) {
		currPositionOnBoard = currPositionOnBoard+diceOutput;
		if(currPositionOnBoard >= maxCellsOnBoard)
			currPositionOnBoard=currPositionOnBoard-maxCellsOnBoard;
	}
}
