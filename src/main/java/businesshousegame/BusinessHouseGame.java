package businesshousegame;

import businesshousegame.board.Board;
import businesshousegame.board.cells.Cell;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

public class BusinessHouseGame {


    public static void main(String... args) {

        List<Player> players = Arrays.asList(new Player("Ram"),
                new Player("Krishna"), new Player("Hari"));
        BusinessHouseGame businessHouseGame = new BusinessHouseGame();
        businessHouseGame.play(players,
                "E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E,"
                        + "H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E",
                "4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12,2,"
                        + "3,5,6,7,8,5,11,10,12");
    }


    public void play(List<Player> players, String boardCoordinates, String diceOutputs) {

        Board.setup(boardCoordinates);
        Dice.setup(diceOutputs);
        for (int set = 0; set < 10; set++) {
            out.println("Current set : " + (set + 1));
            for (Player player : players) {
                int diceOutput = Dice.giveDiceOutput();
                int currentPositionOnBoard = player.movePlayer(diceOutput, Board.getGrid().size());
                Cell currentCell = Board.getGrid().get(currentPositionOnBoard);
                currentCell.handleLandMoney(player);
                out.println(player.getName() + " position on board is " + player.getCurrPositionOnBoard() + " and has worth of " + player.getBalanceAmount());
            }
        }
        out.println("-----------------------------The Final Results Are-----------------------");
        Comparator<Player> sortPlayersByBalanceAmt = (player1, player2) -> Integer.valueOf(player1.getBalanceAmount()).compareTo(Integer.valueOf(player2.getBalanceAmount()));
        players.stream().sorted(sortPlayersByBalanceAmt.reversed()).forEach(player ->
                out.println(player.getName() + " has worth of " + player.getBalanceAmount())
        );

    }
}
