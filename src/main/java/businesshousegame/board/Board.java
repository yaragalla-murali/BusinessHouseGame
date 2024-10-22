package businesshousegame.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Empty;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class Board {
	
	private List<Cell> grid=new ArrayList<>();
	private Map<String,Integer> PlayersCurrentPositions=new HashMap<>();

	public void setup(String cellPositionsStr) {
		String[] cellsAtpositions=cellPositionsStr.split(",");
		for(String cell: cellsAtpositions) {
			switch(cell) {
				case "E": grid.add(new Empty()); break;
				case "J": grid.add(new Jail()); break; 
				case "H": grid.add(new Hotel()); break; 
				case "T": grid.add(new Treasure()); break; 
			}
		}
	}
	
	public Cell move(Player player, Integer diceOutput) {
		Integer playerPosition=player.getCurrPositionOnBoard();
		
		Integer playerCurrentPosition=playerPosition+diceOutput;
		if(playerCurrentPosition>=grid.size()) {
			playerCurrentPosition=playerCurrentPosition-grid.size();
		}
		player.setCurrPositionOnBoard(playerCurrentPosition);		
		PlayersCurrentPositions.put(player.getName(),playerCurrentPosition);		
		return grid.get(playerCurrentPosition);
	}
}
