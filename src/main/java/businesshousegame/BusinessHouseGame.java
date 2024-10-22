package businesshousegame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BusinessHouseGame {

	public static void main(String[] args) {
		List<Player> players=Arrays.asList(new Player("Ram"),
				new Player("Krishna"), new Player("Hari"));
		Game game=new Game();
		game.start(players, 
				"E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E,"
				+ "H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E0", 
				"4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12,2,"
				+ "3,5,6,7,8,5,11,10,12");
		System.out.println();
		System.out.println("-----------------------------The Final Results Are-----------------------");
		Comparator<Player> comptr=(player1,player2) ->player1.getCurrentMoney().compareTo(player2.getCurrentMoney());
		players.stream().sorted(comptr.reversed()).forEach(player ->{
			System.out.println(player.getName()+" has total worth of "+player.getCurrentMoney());
		});;

	}

}
