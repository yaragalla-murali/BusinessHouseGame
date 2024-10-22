package businesshousegame;

import java.math.BigDecimal;
import java.util.List;

import businesshousegame.board.Board;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Game {
	
	

	
	public void start(List<Player> players,String boardCoordinates, String diceOutputs)  {
		
		Board board=new Board();
		board.setup(boardCoordinates);
		Dice dice=new Dice(players,diceOutputs);
		
		for(int set=0;set<10;set++) {
			System.out.println("Current set : "+(set+1));
			players.stream().forEach(player -> {
				Integer diceOutput=dice.giveDiceOutputForCurrentPlayer(player);
				Cell currentCellOfPlayer=board.move(player,diceOutput);
				applyBusinessRules(currentCellOfPlayer,player);
				System.out.println(player.getName()+" is at position "
						+player.getCurrPositionOnBoard()
						+" and current balance is "+player.getCurrentMoney() );
			});
		}
		//for
	}
	
	private void applyBusinessRules(Cell cell, Player player) {
		String cellName=cell.getName();
		BigDecimal balance=player.getCurrentMoney();
		switch(cellName) {
			case "Jail":BigDecimal balanceAfterJailPenality=balance.subtract(Jail.penality);
						player.setCurrentMoney(balanceAfterJailPenality);
						break;
						
			case "Treasure":BigDecimal balanceAfterTreasureWorth=balance.add(Treasure.treasureValue);
							player.setCurrentMoney(balanceAfterTreasureWorth);
							break;
							
			case "Hotel": Hotel hotel=(Hotel)cell;
						  if(hotel.getOwner()==null) {
							  hotel.setOwner(player.getName());
							  BigDecimal balanceAfterHotelPurchase=balance.subtract(Hotel.worth);
							  player.setCurrentMoney(balanceAfterHotelPurchase);
						  }else {
							  BigDecimal balanceAfterHotelRent=balance.subtract(Hotel.rent);
							  player.setCurrentMoney(balanceAfterHotelRent);
						  }
		}
	}
	
	
}
