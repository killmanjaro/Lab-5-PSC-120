package evolutionaryGames;

import java.util.ArrayList;

import sim.util.Int2D;
import sweep.SimStateSweep;

public class Environment extends SimStateSweep {
	public static long id = 0;//start id.  for each agent created, increment by 1
	public int _cooperators = 500;//variables _cooperators to _pavlovw are the initial numbers of each strategy
	public int _defectors = 500; //initial number of defectors
	public int _walkawayCooperators = 500;//initial number of walkaways
	public int _walkawayDefectors = 500; //initial number of walkawayD
	public int _tftMobile = 0; //initial number of tit for tat mobile
	public int _tftStationary = 0;// initial number of tit for tat stationary
	public int _pavlovMobile = 0;// initial number of pavlov mobile
	public int _pavlovStationary = 0;// initial number of pavlov stationary
	public int memorySize = 3; //size of an agent's memory of TFT and PAVLOV strategies
	public double active = 1.0; //probability of random movement
	public boolean groups_or_patches = false; //Multiple agents can be in the same location or what we will call group
	public double averageAge = 50; //average lifespan of an agent
	public double sdAge = 10; //standard deviation in lifespan
	public double mutationRate = 0.0; //muations rate
	public double resoucesToReproduce = 30; //required resources to reproduce one offspring
	public double minResourcesStart = 0; //minimum starting resources
	public double maxResourcesStart = 30;//maximum starting resources.  Starting resources are randomly determined between minResourcesStart and maxResourcesStart
	public double carryingCapacity = 3000;//the maximum number of agents that the space can support
	public int searchRadiusPlayer = 1;//radius for searching for another player
	public double cooperate_cooperator = 3; //payoff for each of two cooperators
	public double defect_defector = 0; //payoff for each of two defectors
	public double cooperate_defector = -1;//payoff for a cooperator playing a defector
	public double defect_cooperator = 5;//payoff for a defector playing a cooperator
	public int reproductionRadius = 1; //The radius from the parent in which an offspring can be placed
	public double lowerBoundsSurvival = 0;//The minimum resources required to survive if useLowerBoundsSurvival = true;
	public boolean useLowerBoundsSurvival = false;// see comments for lowerBoundsSurvival reproductionRadius of the parent, else they are placed randomly in space
	public boolean localReproduction = false;//If true, agent reproduce within 
	public ArrayList<Strategy> mutationList = new ArrayList();//strategies that can be mutants
	public boolean mutationRange = true;//if true, use the mutationList
	
	/*
	 * 
	 * Getters and Setter begin
	 */

	public int get_cooperators() {
		return _cooperators;
	}

	public void set_cooperators(int _cooperators) {
		this._cooperators = _cooperators;
	}

	public int get_defectors() {
		return _defectors;
	}

	public void set_defectors(int _defectors) {
		this._defectors = _defectors;
	}


	public int get_walkawayCooperators() {
		return _walkawayCooperators;
	}

	public void set_walkawayCooperators(int _walkawayCooperators) {
		this._walkawayCooperators = _walkawayCooperators;
	}

	public int get_walkawayDefectors() {
		return _walkawayDefectors;
	}

	public void set_walkawayDefectors(int _walkawayDefectors) {
		this._walkawayDefectors = _walkawayDefectors;
	}

	public void set_tftMobile(int _tftMobile) {
		this._tftMobile = _tftMobile;
	}

	public int get_tftStationary() {
		return _tftStationary;
	}

	public void set_tftStationary(int _tftStationary) {
		this._tftStationary = _tftStationary;
	}

	public int get_pavlovMobile() {
		return _pavlovMobile;
	}

	public void set_pavlovMobile(int _pavlovMobile) {
		this._pavlovMobile = _pavlovMobile;
	}

	public int get_pavlovStationary() {
		return _pavlovStationary;
	}

	public void set_pavlovStationary(int _pavlovStationary) {
		this._pavlovStationary = _pavlovStationary;
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public double getActive() {
		return active;
	}

	public void setActive(double active) {
		this.active = active;
	}

	public boolean isGroups_or_patches() {
		return groups_or_patches;
	}

	public void setGroups_or_patches(boolean groups) {
		this.groups_or_patches = groups;
	}

	public double getAverageAge() {
		return averageAge;
	}

	public void setAverageAge(double averageAge) {
		this.averageAge = averageAge;
	}

	public double getMinResourcesStart() {
		return minResourcesStart;
	}

	public void setMinResourcesStart(double minResourcesStart) {
		this.minResourcesStart = minResourcesStart;
	}

	public double getMaxResourcesStart() {
		return maxResourcesStart;
	}

	public void setMaxResourcesStart(double maxResourcesStart) {
		this.maxResourcesStart = maxResourcesStart;
	}

	public double getCarryingCapacity() {
		return carryingCapacity;
	}

	public void setCarryingCapacity(double carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}

	public double getCooperate_cooperator() {
		return cooperate_cooperator;
	}

	public void setCooperate_cooperator(double cooperate_cooperator) {
		this.cooperate_cooperator = cooperate_cooperator;
	}

	public double getDefect_defector() {
		return defect_defector;
	}

	public void setDefect_defector(double defect_defector) {
		this.defect_defector = defect_defector;
	}

	public double getCooperate_defector() {
		return cooperate_defector;
	}

	public void setCooperate_defector(double cooperate_defector) {
		this.cooperate_defector = cooperate_defector;
	}

	public double getDefect_cooperator() {
		return defect_cooperator;
	}

	public void setDefect_cooperator(double defect_cooperator) {
		this.defect_cooperator = defect_cooperator;
	}

	public double getLowerBoundsSurvival() {
		return lowerBoundsSurvival;
	}

	public void setLowerBoundsSurvival(double lowerBoundsSurvival) {
		this.lowerBoundsSurvival = lowerBoundsSurvival;
	}

	public boolean isLocalReproduction() {
		return localReproduction;
	}

	public void setLocalReproduction(boolean localReproduction) {
		this.localReproduction = localReproduction;
	}

	public boolean isMutationRange() {
		return mutationRange;
	}

	public void setMutationRange(boolean mutationRange) {
		this.mutationRange = mutationRange;
	}



	
	/*
	 * 
	 * Getters and Setter end
	 */


	
	public double getSdAge() {
		return sdAge;
	}

	public void setSdAge(double sdAge) {
		this.sdAge = sdAge;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public double getResoucesToReproduce() {
		return resoucesToReproduce;
	}

	public void setResoucesToReproduce(double resoucesToReproduce) {
		this.resoucesToReproduce = resoucesToReproduce;
	}

	public int getSearchRadiusPlayer() {
		return searchRadiusPlayer;
	}

	public void setSearchRadiusPlayer(int searchRadiusPlayer) {
		this.searchRadiusPlayer = searchRadiusPlayer;
	}

	public int getReproductionRadius() {
		return reproductionRadius;
	}

	public void setReproductionRadius(int reproductionRadius) {
		this.reproductionRadius = reproductionRadius;
	}

	public boolean isUseLowerBoundsSurvival() {
		return useLowerBoundsSurvival;
	}

	public void setUseLowerBoundsSurvival(boolean useLowerBoundsSurvival) {
		this.useLowerBoundsSurvival = useLowerBoundsSurvival;
	}

	/**
	 * Constructor method
	 * @param seed
	 * @param observer
	 */
	public Environment(long seed, Class observer) {
		super(seed, observer);
	}	
	
	/**
	 * Find a unique x,y location
	 * @return
	 */
	public Int2D emptyXY() {
		int x = random.nextInt(gridWidth);
		int y = random.nextInt(gridHeight);
		while(sparseSpace.getObjectsAtLocation(x, y)!= null) {
			x = random.nextInt(gridWidth);
			y = random.nextInt(gridHeight);
		}
		return new Int2D(x,y); //this returns a pair of integers. if loc is an Int2D, then
								//loc.x is the x value and loc.y is the y value in the 2D space
	}
	
	/**
	 * Find a random x,y location, it is not necessarily unique
	 * @return
	 */
	
	public Int2D locationXY() {
		int x = random.nextInt(gridWidth);
		int y = random.nextInt(gridHeight);
		return new Int2D(x,y); //this returns a pair of integers. if loc is an Int2D, then
		//loc.x is the x value and loc.y is the y value in the 2D space
	}
	
	/**
	 * This is the list of mutations that can occur in a simulation.  We only allow mutations back and forth for
	 * populations in which at least one agent initially exists.  So this list is created at the start of the simulation
	 * and the strategy types are determined by whether there are more than 0 agents in the initial population.
	 */
	public void makeMutationList() {//we fill the list will allowable mutations.
		mutationList.clear();//clear the mutation list from previous simulations
		if(_cooperators > 0) {
			mutationList.add(Strategy.COOPERATOR);
		}
		if(_defectors > 0) {
			mutationList.add(Strategy.DEFECTOR);
		}
		if(_walkawayCooperators > 0) {
			mutationList.add(Strategy.WALKAWAY_COOPERATOR);
		}
		if(_tftMobile > 0) {
			mutationList.add(Strategy.TFT_MOBILE);
		}
		if(_tftStationary > 0) {
			mutationList.add(Strategy.TFT_STATIONARY);
		}
		if(_pavlovMobile > 0) {
			mutationList.add(Strategy.PAVLOV_MOBILE);
		}
		if(_pavlovStationary  > 0) {
			mutationList.add(Strategy.PAVLOV_STATIONARY);
		}
		
		  //TODO:fill in the if then statements for TFTM, TFTS, PAVLOVM, and PAVLOVS
		
	}
	
	public void makeAgents() {
		if(!groups_or_patches) {
			int total =  _cooperators + _defectors + _walkawayCooperators + _tftMobile + _tftStationary + _pavlovMobile + _pavlovStationary + _walkawayDefectors;
			//total = all the agents in a simulation
			int total2 = gridWidth * gridHeight;// find the total number of locations or cells
			if(total > total2) {
				System.out.println("Too many agents"); //print out an error if there are more agents than locations
				return;
			}
		}
		//Make the agents for each strategy and place them randomly into space
		for(int i=0;i<_cooperators;i++) {  //make the cooperators
			Int2D location;
			if(!groups_or_patches)
				location = emptyXY();
			else
				location = locationXY();
			//where location.x is the x location and location.y is the y location
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent =  new Agent(this,id,Strategy.COOPERATOR,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);//this colors the agent by strategy, e.g., cooperator is blue
		}
		for(int i=0;i<_defectors;i++) {//make the defectors
			Int2D location;
			if(!groups_or_patches)
				location = emptyXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.DEFECTOR,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent); //defectors are red
		}
		for(int i=0;i<_walkawayCooperators;i++) {//make the walk away cooperators
			Int2D location;
			if(!groups_or_patches)
				location = emptyXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.WALKAWAY_COOPERATOR,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		
		for(int i=0;i<_walkawayDefectors;i++) {// make the walk away defectors
			Int2D location;
			if(!groups_or_patches)
				location = emptyXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.WALKAWAY_DEFECTOR,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		for(int i=0;i<this._tftMobile;i++) {
			Int2D location;
			if(!this.groups_or_patches)
				location = uniqueXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.TFTM,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		for(int i=0;i<this._tftStationary;i++) {
			Int2D location;
			if(!groups_or_patches)
				location = uniqueXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.TFTS,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		for(int i=0;i<this._pavlovMobile;i++) {
			Int2D location;
			if(!groups_or_patches)
				location = uniqueXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.PAVLOVM,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		for(int i=0;i<this._pavlovStationary;i++) {
			Int2D location;
			if(!groups_or_patches)
				location = uniqueXY();
			else
				location = locationXY();
			int xdir = random.nextInt(3)-1;
			int ydir = random.nextInt(3)-1;
			double resources = (maxResourcesStart-minResourcesStart)*random.nextDouble()+minResourcesStart;
			long id = this.id++;
			Agent agent = new Agent(this,id,Strategy.PAVLOVS,resources,location.x,location.y,xdir,ydir,true);
			agent.event = schedule.scheduleRepeating(agent);
			sparseSpace.setObjectLocation(agent,random.nextInt(gridWidth), random.nextInt(gridHeight));
			agent.colorByStrategy(agent.strategy, this, agent);
		}
		makeMutationList();//makes the list of possible mutations based on whether there are initial agents > 0 for a strategy
	}

	public void start() {
		super.start();
		makeSpace(gridWidth,gridHeight);
		makeAgents();
		if(observer != null)
			observer.initialize(space, spaces);
	}
}
