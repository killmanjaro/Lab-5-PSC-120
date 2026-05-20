package evolutionaryGames;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Bag;
import sim.util.Int2D;

public class Agent implements Steppable {
	public Stoppable event;// event.stop() is used to remove the agent from the schedule
	long id;// this is the agent's id
	Strategy strategy; // this is the agent's strategy
	double resources; //resources accumulated to reproduce
	boolean played = false;//false to start a round and true once played
	Memory memory = null;//for memory agents, a memory structure must be created.
	int x; // location on the x-axis
	int y; // location on the y-axis
	int xdir; // x direction of change
	int ydir; // y direction of change
	int age; // current age of agent
	int maxAge;// age at which agent dies

	/**
	 * Agent constructor method.
	 * 
	 * @param state
	 * @param id
	 * @param strategy
	 * @param resources
	 * @param x
	 * @param y
	 * @param xdir
	 * @param ydir
	 * @param startup
	 */
	public Agent(Environment state, long id, Strategy strategy, double resources, int x, int y, int xdir, int ydir,
			boolean startup) {
		super();
		this.id = id; //agent unique id
		this.strategy = strategy; //the agents strategy
		this.resources = resources;//agents startup resources
		this.x = x;//initial starting x location
		this.y = y;//initial starting y location
		this.xdir = xdir;//initial starting direction of movement on the x-axis
		this.ydir = ydir;//initial starting direction of movement on the y-axis
		maxAge = (int) (state.averageAge + state.random.nextGaussian() * state.sdAge);
		if (startup) {//create an age in the range 0 to averageAge, so some agents die quickly and other reproduce
			age = state.random.nextInt((int) state.averageAge);
		} else {
			age = 0;//when born from another agent
		}
		//TODO: create memory for memory agents with a switch statement.

	}

	/**
	 * Returns the strategy of an opponent.
	 * 
	 * @param opponent
	 * @return
	 */
	public Strategy getStrategy(Agent opponent) {
		switch (strategy) {
		case COOPERATOR:
			return Strategy.COOPERATOR;
		case DEFECTOR:
			return Strategy.DEFECTOR;
		case WALKAWAY_COOPERATOR:
			return Strategy.COOPERATOR;
		case WALKAWAY_DEFECTOR:
			return Strategy.DEFECTOR;

			//TODO:See lab for instructions to complete

		default: //just in case there are no matches, return cooperate, which should not happen
			return Strategy.COOPERATOR;
		}
	}

	//TODO:See lab for instructions to completeUncomment the block below and see lab instructions to 
	//complete getStrategyTFT and getStrategyPAVLOV

	/*
	 * public Strategy getStrategyTFT(Agent opponent) {
	 * 
	 * See lab for instructions to complete
	 * 
	 * }
	 * 
	 * public Strategy getStrategyPAVLOV(Agent opponent) {
	 * 
	 * See lab for instructions to complete
	 * 
	 * }
	 */

	/**
	 * Calculates an agent's payoff given the strategy it played and the strategy of
	 * its opponent. There are two "play" methods. This plays the prisoner's dilemma and adds
	 * up the resources.
	 * 
	 * @param state
	 * @param opponent
	 * @return
	 */
	public Strategy play(Environment state, Agent opponent) {
		Strategy myStrategy = getStrategy(opponent); //given this agent's opponent, this returns this agent's strategy
		Strategy myOpponentStrategy = opponent.getStrategy(this);// given this agent, return opponents strategy
		switch (myOpponentStrategy) {//opponent strategy
		case COOPERATOR://opponent cooperates
			switch (myStrategy) {//this agent's strategy
			case COOPERATOR://this agent cooperates
				resources += state.cooperate_cooperator; //cooperative payoff
				break;
			case DEFECTOR://this agent defects
				resources += state.defect_cooperator; //sucker's payoff
				break;
			}
			break;
		case DEFECTOR://opponent defects
			switch (myStrategy) {//this agent's strategy
			case COOPERATOR://this agent cooperates
				resources += state.cooperate_defector; //sucker's payoff
				break;
			case DEFECTOR://this agent defects
				resources += state.defect_defector;// defectors' payoff
				break;
			}
			break;
		}
		this.played = true;
		//TODO: Good place to add code for remembering an opponent. An agent that
		// is TFT or PAVLOV must remember the opponent it played.
		//You could use a switch of if-then statement to allow only TFT and PAPLOV strategies
		//to update their memory. 
		// memory.storeMemory(opponent, myOpponentStrategy, myStrategy);

		return myOpponentStrategy;
	}

	/**
	 * An agent using a mobile strategy first searches for agents in its search
	 * radius. If there are none, then it randomly moves then searches again for an
	 * opponent to play.  
	 * 
	 * @param state
	 */
	public void mobileStrategy(Environment state) {
		Bag agents = search(state);
		if (agents == null || agents.numObjs == 0) {//if there are no opponents, move and try to find one
			if (state.random.nextBoolean(state.active)) {
				xdir = state.random.nextInt(3) - 1;
				ydir = state.random.nextInt(3) - 1;
			}
			placeAgent(state);
			agents = search(state);
			if (agents == null) {//check to see if there are agents without having to move
				return;//no agents after moving and looking, so give up on this step
			}
			Agent opponent = findOpponent(state, agents);
			if (opponent == null) {//check to see if there is an eligible opponent
				return;//on eligible opponents on this step, give up
			}
			play(state, opponent); //agents play the prisoner's dilemma
			opponent.play(state, this);
		} else {//there were agents without having to move
			Agent opponent = findOpponent(state, agents);
			if (opponent == null) {//check to see if there is an eligible opponent
				return;//on eligible opponents on this step, give up
			}
			play(state, opponent); //agents play the prisoner's dilemma
			opponent.play(state, this);
		}
	}

	/**
	 * A walkaway strategy is just like a mobile strategy except that if a defection
	 * is encountered, it moves randomly.
	 * 
	 * @param state
	 */
	public void walkawayStrategy(Environment state) {
		Bag agents = search(state);
		Agent opponent;
		Strategy opponentStrategy;
		if (agents == null || agents.numObjs == 0) {//if there are no opponents, move and try to find one
			if (state.random.nextBoolean(state.active)) {
				xdir = state.random.nextInt(3) - 1;
				ydir = state.random.nextInt(3) - 1;
			}
			placeAgent(state);
			agents = search(state);
			if (agents == null) {//check to see if there are agents without having to move
				return;//no agents after moving and looking, so give up on this step
			}
			opponent = findOpponent(state, agents);
			if (opponent == null) {//check to see if there is an eligible opponent
				return;//on eligible opponents on this step, give up
			}
			opponentStrategy = play(state, opponent); //agents play the prisoner's dilemma
			opponent.play(state, this);
		} else {//there were agents without having to move
			opponent = findOpponent(state, agents);
			if (opponent == null) {//check to see if there is an eligible opponent
				return;//on eligible opponents on this step, give up
			}
			opponentStrategy = play(state, opponent);//agents play the prisoner's dilemma
			opponent.play(state, this);
		}
		if (opponentStrategy == Strategy.DEFECTOR) { // walk away
			if (state.random.nextBoolean(state.active)) { //the opponent was a defector, so move away
				xdir = state.random.nextInt(3) - 1;
				ydir = state.random.nextInt(3) - 1;
			}
			placeAgent(state);
		}
	}

	/**
	 * This is the stationary strategy. Agents don't move. Because they don't move, they likely don't do well.
	 * 
	 * @param state
	 */

	public void stationaryStrategy(Environment state) {
		Bag agents = search(state);
		Agent opponent;
		if (agents == null || agents.numObjs == 0) {//if there are no opponents, move and try to find one
			return;//no agents after moving and looking, so give up on this step
		} else {
			opponent = findOpponent(state, agents);
			if (opponent == null) {
				return;//on eligible opponents on this step, give up
			}
			play(state, opponent);//agents play the prisoner's dilemma
			opponent.play(state, this);
		}
	}

	/**
	 * This is the main play method. Notice that strategy in the switch statement is the
	 * agent's strategy.  This "play" method ( determines the movement strategy, finds an opponent, 
	 * and calls the "play" method above via strategies such as mobileStrategy.
	 * 
	 * @param state
	 */

	public void play(Environment state) {
		switch (strategy) {
		case COOPERATOR:
			; //skip to the next
		case DEFECTOR:
			mobileStrategy(state); //both COOPERATORs and DEFECTORs use the mobileStrategy
			break;
		case WALKAWAY_COOPERATOR:
			; //skip to the next
		case WALKAWAY_DEFECTOR: //both WALKAWAY_COOPERATORs and WALKAWAY_DEFECTORs use the walkawayStrategy
			walkawayStrategy(state);
			break;

			//TODO: See lab on instructions how to complete this method.
		}
	}

	/**
	 * This method finds an opponent to play from a bag of agents supplied to it.
	 * 
	 * @param state
	 * @param agents
	 * @return
	 */


	public Agent findOpponent(Environment state, Bag agents) {
		if(agents == null || agents.numObjs == 0) return null;//bag is empty or null, just return null
		agents.shuffle(state.random);//randomize the order of the agents, this is important to not bias the selection of agents
		for(int i = 0; i< agents.numObjs;i++) {
			Agent a = (Agent)agents.objs[i];
			if(!(a.played && a.equals(this))) {// a.played == true AND a == this agent,keep going through the bag to find an agent
				return a; //return an agent
			}
		}
		return null;//No agent was found!
	}

	/**
	 * When an agent reproduces, this method can return a mutant strategy from the
	 * mutationList if the mutations rate is > 0.0
	 * 
	 * @param state
	 * @return
	 */

	public Strategy mutation(Environment state) {
		Strategy newStrategy;
		if (state.mutationRange) {//if mutations are restricted to the initial strategies at the start of a simulation. That is, all strategies
			                      //with the number of agents > 0
			if (state.random.nextBoolean(state.mutationRate)) {//with probability of mutations rate, e.g., 0.01 would be 1 in 100 time true on average
				newStrategy = state.mutationList.get(state.random.nextInt(state.mutationList.size())); //find another strategy randomly
				while (state.mutationList.size()> 1 && newStrategy == this.strategy) { // make sure it is different than it parent strategy different from the parent
					newStrategy = state.mutationList.get(state.random.nextInt(state.mutationList.size()));//find a new one until newStrategy != this.strategy
				}
				return newStrategy; //return the new strategy
			} else {
				return this.strategy; // the parent agent strategy if no mutation
			}
		} else {// if state.mutationRange == false, get a new strategy from the list of all strategeis
			if (state.random.nextBoolean(state.mutationRate)) {
				newStrategy = Strategy.values()[state.random.nextInt(Strategy.values().length)]; //we go into the Enum Strategy
				while (state.mutationList.size()> 1 &&newStrategy == this.strategy) { // find a strategy different from the parent
					newStrategy = Strategy.values()[state.random.nextInt(Strategy.values().length)];//find a new one until newStrategy != this.strategy
				}
				return newStrategy;//return the new strategy
			} else {
				return this.strategy; // the parent agent strategy if no mutation
			}
		}
	}

	/**
	 * This method finds a random and empty location in space, if an empty location
	 * exists within the reproductionRadius
	 * 
	 * @param state
	 * @return
	 */
	public Int2D findLocation(Environment state) {
		if (!state.groups_or_patches) {//One agent per location
			Int2D location = state.sparseSpace.getRandomEmptyMooreLocation(state, x, y, state.reproductionRadius,
					state.sparseSpace.TOROIDAL, false); //this finds an empty random location in an agent's Moore neighborhood (i.e., it reproductionRadius
			return location;//location has the coordinates location.x, location.y
		} else {//if more than one agent is at a location
			if (state.reproductionRadius == 0) {//if the reproduction radius is 0, the offspring goes into the parent location
				return new Int2D(x, y);//return the new location
			} else {// if the reproductionRadius > 0, then find a random location within the
				// parent's reproductionRadius
				int xch = state.random.nextInt(state.reproductionRadius + 1);// For example, if reproductionRadius = 2, then 
				                                                             //xch could = 0, 1, or 2 randomly
				if(state.random.nextBoolean(0.5)) xch = - xch; //50% chance its negative, zeros are not changed, e.g., 0, -1, -2
				int ych = state.random.nextInt(state.reproductionRadius + 1);
				if(state.random.nextBoolean(0.5)) ych = - ych; //50% chance its negative, zeros are not changed
				int newx = state.sparseSpace.stx(x + xch);// adjust of a toroidal space
				int newy = state.sparseSpace.sty(y + ych);
				return new Int2D(newx, newy); //return the new location
			}
		}
	}

	/**
	 * The reproduction method for an agent. When an agent has sufficient resources
	 * to reproduce, it may be able to reproduce an agent so long as the carrying
	 * capacity is not exceeded.
	 * 
	 * @param state
	 * @return
	 */

	public Agent replicate(Environment state) {
		Int2D location;
		if (state.localReproduction) {// if local reproduction is true
			location = this.findLocation(state); // find a location
		} else {
			if (!state.groups_or_patches)// if patches == false
				location = state.emptyXY();// get any empty location for the offspring
			else
				location = state.locationXY();// otherwise find a random patch
		}
		if (location == null) {// if there is no location, too bad
			resources = 0.0; // reproduction failed
			return null; // reproduction cannot proceed because there is not place for the agent
		}
		if (carryingCapacityExceeded(state)) {// if the carrying capacity is exceeded, too bad
			resources = 0.0; // reproduction failed
			return null; // exceeds
		}
		resources = 0.0;
		// If we get this far, reproduction will be successful!
		int xdir = state.random.nextInt(3) - 1;// get random direcrections of movement
		int ydir = state.random.nextInt(3) - 1;
		Strategy newStrategy = mutation(state);// get a strategy for the offspring. It will be the parents, unless there
												// is a mutation
		long newId = state.id++;// get a new id for the offspring
		Agent a = new Agent(state, newId, newStrategy, 0, location.x, location.y, xdir, ydir, false);// make the
																										// offspring
		a.event = state.schedule.scheduleRepeating(a);// schedule it as repeating
		state.sparseSpace.setObjectLocation(a, location.x, location.y);// put it in the chosen location
		colorByStrategy(a.strategy, state, a);// color it by strategy
		return a;// return the agent
	}
	/**
	 * Color by strategy method: state.gui.setOvalPortrayal2DColor(agent, red,
	 * green, blue, opacity);
	 * 
	 * @param strategy
	 * @param state
	 * @param a
	 */

	public void colorByStrategy(Strategy strategy, Environment state, Agent a) {
		switch (strategy) {
		case COOPERATOR:
			state.gui.setOvalPortrayal2DColor(a, (float) 0, (float) 0, (float) 1, (float) 1);
			                                     //no red,  , no green, blue,     solid color
			break;
		case DEFECTOR:
			state.gui.setOvalPortrayal2DColor(a, (float) 1, (float) 0, (float) 0, (float) 1);
			                                     //red,     no green,  no blue,   solid color
			break;
		case WALKAWAY_COOPERATOR:
			state.gui.setOvalPortrayal2DColor(a, (float) 0, (float) 1, (float) 0, (float) 1);
			                                     //no red,   green,     no blue,   solid
			break;
		case WALKAWAY_DEFECTOR:
			state.gui.setOvalPortrayal2DColor(a, (float) 1, (float) 0.6, (float) 0, (float) 1);
			break;
                                                 //red,      60% green,  no blue,  solid --> result is orang
			//TODO: you will need to add case statements to color the different strategies. 
			//You can, for example, go to https://rgbcolorpicker.com/ to try out colors. RGB is listed
			//as 0 to 255, so for walk away defector you would enter 1 * 255 = 255, 0.6 * 255 = 153, 0 * 255 = 0
			//For example, purple is (float)0.5, (float) 0.5, (float) 0, (float) 1 or 128, 128, 0, 1

		default:
			state.gui.setOvalPortrayal2DColor(a, (float) 1, (float) 1, (float) 1, (float) 1);
			break;
		}
	}

	/**
	 * Places an agent in space.
	 * 
	 * @param state
	 */
	public void placeAgent(Environment state) {
		if (!state.groups_or_patches) {
			int tempx = state.sparseSpace.stx(x + xdir);
			int tempy = state.sparseSpace.sty(y + ydir);
			Bag b = state.sparseSpace.getObjectsAtLocation(tempx, tempy);
			if (b == null || b.numObjs == 0) {
				x = tempx;
				y = tempy;
				state.sparseSpace.setObjectLocation(this, x, y);
			}
		} else {
			x = state.sparseSpace.stx(x + xdir);
			y = state.sparseSpace.sty(y + ydir);
			state.sparseSpace.setObjectLocation(this, x, y);
		}
	}

	/**
	 * Searches locally in it search radius for agents and returns a bag of them. If
	 * none, it returns null
	 * 
	 * @param state
	 * @return
	 */
	public Bag search(Environment state) {
		Bag agents = null;
		if (state.groups_or_patches) {// For Aktipis model
			Bag temp = state.sparseSpace.getObjectsAtLocation(x, y);
			if (temp == null) {
				return null;//if there are no agents, return null
			}
			agents = new Bag(temp.numObjs);// size of temp
			agents.addAll(temp); // have to make a copy because temp points to a bag in the sparseSpace
			agents.remove(this);// remove this agent
			return agents;// return the rest if any
		} else {//agent are in unique locations
			agents = state.sparseSpace.getMooreNeighbors(x, y, state.searchRadiusPlayer, state.sparseSpace.TOROIDAL,
					false);//get neighbors but not self (this agent)
			return agents;// return all the neighboring agents except this agent
		}
	}

	/**
	 * When an agent is removed from the simulation, it's die method is called
	 * 
	 * @param state
	 */
	public void die(Environment state) {
		state.sparseSpace.remove(this);// remove from space
		event.stop();// remove it from the schedule
	}

	/**
	 * tests for whether the carrying capacity is exceeded.
	 * 
	 * @param state
	 * @return
	 */

	public boolean carryingCapacityExceeded(Environment state) {
		Bag agents = state.sparseSpace.getAllObjects();
		if (agents.numObjs < state.carryingCapacity) {
			return false;// there is room for at least one more
		} else {
			return true;// no room for even one more
		}
	}


	/**
	 * Step method required by MASON
	 */

	public void step(SimState state) {
		Environment eState = (Environment) state;
		if (age > maxAge) {// check to see if the agent is to old and must die
			die(eState); //agent dies
			return; //end step
		}
		if (eState.useLowerBoundsSurvival && resources < eState.lowerBoundsSurvival) {// check to see whether agent
																						// starves to death
			die(eState); //agent dies
			return;//end step
		}
		if (resources >= eState.resoucesToReproduce) {// check to see whether agent can reproduce
			replicate(eState);
		}
		age++; // ages 1 step
		if (played) {
			return;// end step
		}
		play(eState);// if it hasn't played, play!
	}

}
