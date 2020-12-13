package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;

import java.util.List;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Bonus;
import ch.epfl.cs107.play.game.superpacman.actor.Cherry;
import ch.epfl.cs107.play.game.superpacman.actor.Diamond;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	
	
	private List<Diamond> diamonds = new ArrayList<Diamond>();
	private List<Ghost> ghosts = new ArrayList<Ghost>();
	private SuperPacmanCell cell;
	private AreaGraph graph;

	
	
	/**
	 * Default SuperPacmanBehavior Constructor  
	 * @param window (Window): graphic context, not null
	 * @param name (String): name of the behavior image, not null
	 */
	public SuperPacmanBehavior(Window window, String name) {
		super(window, name);
		graph = new AreaGraph();		
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
				cell = new SuperPacmanCell(x,y,SuperPacmanCellType.toType(getRGB(getHeight()-1-y, x)));
				setCell(x, y, cell);
			}
	    }
		createNodes();
	}
	
	private void createNodes() {
		boolean[] edges;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
			  if (!(getCellType(x,y).equals(SuperPacmanCellType.WALL))) {
				  DiscreteCoordinates position = new DiscreteCoordinates(x, y);
			      edges = getEdges(x, y);
			      graph.addNode(position, edges[0], edges[1], edges[2], edges[3]);
			  }
		   }
		}
	}
	
	 private boolean[] getEdges(int x, int y) {
			boolean [] edges = new boolean[4];
			if(!getCellType(x-1,y).equals(SuperPacmanCellType.WALL)) {
				edges[0]=true;
			}
			if(!getCellType(x,y+1).equals(SuperPacmanCellType.WALL)) {
				edges[1]=true;
			}
			if(!getCellType(x+1,y).equals(SuperPacmanCellType.WALL)) {
				edges[2]=true;
			}
			if(!getCellType(x,y-1).equals(SuperPacmanCellType.WALL)) {
				edges[3]=true;
			}
			return edges;
		}
	
	
	/**
	 * Enum type SuperPacmanCellType
	 * Associate to each cell of the area its type depending on its color
	 */
	public enum SuperPacmanCellType {
		
		NONE(0),  // never used as real content
		WALL (-16777216), //black
		FREE_WITH_DIAMOND(-1),  //white
		FREE_WITH_BLINKY (-65536), //red
		FREE_WITH_PINKY (-157237), //pink
		FREE_WITH_INKY (-16724737),  //cyan
		FREE_WITH_CHERRY (-36752),  //light red
		FREE_WITH_BONUS (-16478723), //light blue
		FREE_EMPTY (-6118750); // sort of gray
		
		final int type;
		
		/**
		 * Default constructor for the enum type SuperPacmanCellType
		 * @param type (int) the RGB color of the cell that will be associated to a SuperPacmanCellType
		 */
		SuperPacmanCellType(int type){
			this.type = type;
		}
		
		public static SuperPacmanCellType toType(int type){
			for(SuperPacmanCellType ict : SuperPacmanCellType.values()){
				if(ict.type == type)
				return ict;
			}
			return NONE;
	    }
	}
	
    /**
	 * Register an actor depending on the SuperPacmanCell cell's type in the area
     * @param area (Area) : the area in which the actor will be registered
     */
	protected void registerActors(Area area) {
	
		registerWall(area);
		
		registerCollectable(area);
		
		registerGhosts(area);
	}
	
	/**
	 * Register the ghosts
	 * @param area
	 */
	private void registerGhosts(Area area) {
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
		   	  DiscreteCoordinates position = new DiscreteCoordinates(x, y);
			   
			  if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_BLINKY)) {
				  Blinky ghost = new Blinky(area,position,position);
				  area.registerActor(ghost);
				  ghosts.add(ghost);
			  }
			  
			  if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_INKY)) {
				  Inky ghost = new Inky(area,position,position);
				  area.registerActor(ghost);
				  ghosts.add(ghost);
			  }
			  
			 if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_PINKY)) {
				  Pinky ghost = new Pinky(area,position,position);
				  area.registerActor(ghost);
				  ghosts.add(ghost);
			  }
			}
		}	
	}
	
	

	
	
	/**
	 * Register the walls
	 * @param area
	 */
	private void registerWall (Area area) {
		boolean[][] neighborhood;
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
			  if (getCellType(x,y).equals(SuperPacmanCellType.WALL)) {
				  DiscreteCoordinates position = new DiscreteCoordinates(x, y);
				  neighborhood = getNeighborhood(x, y);
				  area.registerActor(new Wall(area,position,neighborhood));
			  }
			}
		}
	}
	
	
	/**
	 * Register the collectable entities
	 * @param area
	 */
	private void registerCollectable(Area area) {
		for (int x = 0; x<getWidth(); ++x) {
			for (int y =0; y<getHeight(); ++y) {
			  DiscreteCoordinates position = new DiscreteCoordinates(x, y);
		      if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_BONUS)) {
				  Bonus bonus = new Bonus(area, position);
			      area.registerActor(bonus);
		      }
	          if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_CHERRY)) {
	    	      Cherry cherry = new Cherry(area, position);
		          area.registerActor(cherry);
		      }
	          if (getCellType(x,y).equals(SuperPacmanCellType.FREE_WITH_DIAMOND)) {
	    	      Diamond diamond = new Diamond(area, position);
		          area.registerActor(diamond);
		          diamonds.add(diamond);
		      }
			}
		}
	}
	
   
	
	/**
	 * Construct the neighborhood of an actor WALL
	 * Indicate if it contains an actor of the type WALL in his neighborhood
	 * @param x (int): x-coordinate of this cell
     * @param y (int): y-coordinate of this cell
	 * @return (boolean[][]): matrix 3x3, false by default
	 */
	private boolean[][] getNeighborhood(int x, int y){
	    
		boolean[][] neighborhood = new boolean[3] [3];
		neighborhood [1][1]=true;
		
		if (getCellType(x-1,y).equals(SuperPacmanCellType.WALL) ) {
		    neighborhood[0][1]=true;
		}
		if (getCellType(x,y+1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[1][0]=true;
		}
		if (getCellType(x+1,y).equals(SuperPacmanCellType.WALL) ){
		    neighborhood[2][1]=true;
		}
		if (getCellType(x,y-1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[1][2]=true;
		}
		if (getCellType(x-1,y+1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[0][0]=true;
		}
		if (getCellType(x+1,y+1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[2][0]=true;
		}
		if (getCellType(x-1,y-1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[0][2]=true;
		}
		if (getCellType(x+1,y-1).equals(SuperPacmanCellType.WALL)) {
		    neighborhood[2][2]=true;
		}
		
		return neighborhood;
	}
		
	/**
	 * Getter for the type of a SuperPacmanCell cell
	 * @param x (int): x-coordinate of this cell
     * @param y (int): y-coordinate of this cell
	 * @return (SuperPacmanCellType ): The type of the SuperPacmanCell cell
	 */
	private SuperPacmanCellType getCellType(int x, int y) {
		if (x < 0 || y < 0 || x>= getWidth() || y >= getHeight()) {
			return SuperPacmanCellType.NONE;
		}else 
		return ((SuperPacmanCell)getCell(x,y)).type;
	}
	
	
	
	protected List<Diamond> getDiamonds() {
		return diamonds;
	}
	
	
	
	public void resetAllGhostsPosition() {
		for(Ghost ghost : ghosts) {
			ghost.resetGhostPosition();
		}
	}
	
	public void frightenGhosts() {
		
		for(Ghost ghost : ghosts) {
		    System.out.println("afraid = true");
			ghost.setIsAfraid(true);
		}
		
		if (Ghost.getTimer()<=0) {
			for(Ghost ghost : ghosts) {
			    System.out.println("afraid = false");
				ghost.setIsAfraid(false);
			}
	    }
		
		
	}

	

	protected AreaGraph getGraph() {
		return graph;
	}



	/**
	 * Class SuperPacmanCell extends AreaBehavior.Cell
	 * Instance for the SuperPacmanCellType of the SuperPacmanBehavior
	 */
	public class SuperPacmanCell extends AreaBehavior.Cell { 
		
		private SuperPacmanCellType type;
		
		/**
		 *  Constructor for SuperPacmanCell
		 *  @param x (int) : x coordinate of the cell
		 *  @param y (int) : y coordinate of the cell
		 *  @param type (SuperPacmanCellType): the enum type of the cell
		*/
		protected SuperPacmanCell(int x, int y, SuperPacmanCellType type) {
			super(x, y);
			this.type=type;
		}
        

		@Override
		public boolean canEnter(Interactable entity) { 
			return !this.hasNonTraversableContent();
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}
		
	}









	
}