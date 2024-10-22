package businesshousegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dice {
	
	private Map<String, List<DiceOutput>> diceoutputForPlayers=new HashMap<>();

	
	public Dice(List<Player> players,String diceoutputStr) {
		String[] diceOutputs=diceoutputStr.split(",");
		int[]count= {0};
		for(int set=0; set<10;set++) {
			
			players.forEach(player -> {
				String name=player.getName();
				Optional<List<DiceOutput>> namekey=Optional.ofNullable(diceoutputForPlayers.get(name));
				if(namekey.isPresent()) {
					Integer diceoutput=Integer.parseInt(diceOutputs[count[0]]);
					List<DiceOutput>outputList=diceoutputForPlayers.get(name);
					outputList.add(new DiceOutput(diceoutput));
					
				}else {
					Integer diceoutput=Integer.parseInt(diceOutputs[count[0]]);
					DiceOutput doutput=new DiceOutput(diceoutput);
					List<DiceOutput>outputList=new ArrayList<>();
					outputList.add(doutput);
					diceoutputForPlayers.put(name, outputList);
				}
				count[0]=count[0]+1;
				
				
			});
		}
		
	}
	
	public Integer giveDiceOutputForCurrentPlayer(Player player) {
		String name=player.getName();
		List<DiceOutput>currentPlayerOutputs=diceoutputForPlayers.get(name);
		for(DiceOutput output:currentPlayerOutputs) {
			if(!output.isCompleted()) {
				output.setCompleted(true);
				return output.getOutput();
			}
				
		}
		return null;
	}
}
