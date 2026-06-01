package evolutionaryGames;


public enum Strategy {
	COOPERATOR (1.0), //not we also assign a double to each strategy
	DEFECTOR (2.0),
	WALKAWAY_COOPERATOR (3.0),
	WALKAWAY_DEFECTOR (4.0),
	TFT_MOBILE (5.0),
	TFT_STATIONARY (6.0),
	PAVLOV_MOBILE (7.0),
	PAVLOV_STATIONARY (8.0),
	GRIM_MOBILE (9.0),
	GRIM_STATIONARY (10.0);
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
