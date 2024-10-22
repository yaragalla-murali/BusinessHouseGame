package businesshousegame;

public class DiceOutput {
	
	private Integer output;
	private boolean isCompleted;
	
	public DiceOutput(Integer output) {
		this.output=output;
	}
	
	public Integer getOutput() {
		return output;
	}
	public void setOutput(Integer output) {
		this.output = output;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	

}
