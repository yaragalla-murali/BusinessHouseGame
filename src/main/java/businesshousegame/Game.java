package businesshousegame;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import businesshousegame.board.Board;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Game {
    public void start(List<Player> players, String boardCoordinates, String diceOutputs) {

        Board board = new Board();
        board.setup(boardCoordinates);
        Dice dice = new Dice(players, diceOutputs);

        IntStream.range(0, 10)
                .forEach(set -> {
                    System.out.println("Current set : " + (set + 1));
                    players.forEach(player -> {
                        Integer diceOutput = dice.giveDiceOutputForCurrentPlayer(player);
                        Cell currentCellOfPlayer = board.move(player, diceOutput);
                        applyBusinessRules(currentCellOfPlayer, player);
                        System.out.println(player.getName() + " is at position "
                                + player.getCurrPositionOnBoard()
                                + " and current balance is " + player.getCurrentMoney());
                    });
                });

    }

    private void applyBusinessRules(Cell cell, Player player) {
        String cellName = cell.getName();
        BigDecimal balance = player.getCurrentMoney();
        switch (cellName) {
            case "Jail":
                player.setCurrentMoney(balance.subtract(Jail.penalty));
                break;
            case "Treasure":
                player.setCurrentMoney(balance.add(Treasure.treasureValue));
                break;
            case "Hotel":
                Optional.of((Hotel) cell)
                        .ifPresent(hotel -> Optional.ofNullable(hotel.getOwner())
                                .ifPresentOrElse(
                                        owner -> { // Hotel already owned, pay rent
                                            BigDecimal balanceAfterHotelRent = balance.subtract(Hotel.rent);
                                            player.setCurrentMoney(balanceAfterHotelRent);
                                        },
                                        () -> { // Hotel is not owned, purchase it
                                            hotel.setOwner(player.getName());
                                            BigDecimal balanceAfterHotelPurchase = balance.subtract(Hotel.worth);
                                            player.setCurrentMoney(balanceAfterHotelPurchase);
                                        }
                                ));
        }
    }


}
