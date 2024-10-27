package businesshousegame;

public class Player {

    private String name;
    private int balanceAmount = 1000;
    private int currPositionOnBoard = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getCurrPositionOnBoard() {
        return currPositionOnBoard;
    }

    public int movePlayer(int diceOutput, int maxCellsOnBoard) {
        currPositionOnBoard = currPositionOnBoard + diceOutput;
        if (currPositionOnBoard >= maxCellsOnBoard)
            currPositionOnBoard = currPositionOnBoard - maxCellsOnBoard;
        return currPositionOnBoard;
    }
}
