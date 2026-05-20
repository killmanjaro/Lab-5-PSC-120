package evolutionaryGames;


public enum Strategy {
	COOPERATOR (1.0), //not we also assign a double to each strategy
	DEFECTOR (2.0),
	WALKAWAY_COOPERATOR (3.0),
	WALKAWAY_DEFECTOR (4.0);
	//TODO: Add other strategies constants
	/**
	 * This defines the strategy id
	 */
	private final double id;
	Strategy(double id){
		this.id =id;
	}
	
	/**
	 * This method returns the id.
	 * @return
	 */
	public double id() {
		return id;
	}
}

