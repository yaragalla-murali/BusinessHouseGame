package businesshousegame;

public class Player {
	private String name;
	private int balanceAmt = 1000;
	private int currentPositionOnBoard;

	public Player(String name) {
		this.name = name;
	}

	public int getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(int balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getName() {
		return name;
	}

	public int move(int diceOutput, int boardMaxCells) {
		currentPositionOnBoard = currentPositionOnBoard + diceOutput;
		if (currentPositionOnBoard >= boardMaxCells) {
			currentPositionOnBoard = currentPositionOnBoard - boardMaxCells;
		}
		return currentPositionOnBoard;
	}

}
