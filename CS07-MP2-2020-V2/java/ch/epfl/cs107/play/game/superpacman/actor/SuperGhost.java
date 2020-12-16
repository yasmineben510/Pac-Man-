package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public abstract class SuperGhost extends Ghost {

	/// Maximum attempts allowed to find a random path.
	private final int MAX_RANDOM_ATTEMPT=200;
	private DiscreteCoordinates targetPos;
	
	private Queue<Orientation> path;
	//private Path graphicPath;
	private Orientation nextOrientation;
	
	/**
	 * Constructor for a SuperGhost
	 */
	public SuperGhost(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, position, shelter, spriteName);
		generatePath();
	}

	/**
	 * generate the shortest path to follow the player if known
	 * @return the orientation for the shortest path
	 */
	protected void followPlayer() {
		
		int attempts = 0;

		do {
			 targetPos= getSuperPacman().getCurrentCells().get(0);
			 path = ((SuperPacmanArea)getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(),targetPos);
			 ++attempts;
		} while (path == null && attempts<MAX_RANDOM_ATTEMPT) ;
							
	/*	if (path!=null) {
			graphicPath= new Path(this.getPosition(), new LinkedList <Orientation >(path));
		}*/
	}
	
	/**
	 * generates a random path or follows the player if mesmerized 
	 * @return the orientation for a random path
	 */
	protected void generatePath() {
		
		if(!isAfraid() && getSuperPacman()!=null ) {
			followPlayer();
		}
		else 
			do {
			   targetPos = new DiscreteCoordinates(RandomGenerator.getInstance().nextInt(getOwnerArea().getWidth()), RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight()));
			   path = ((SuperPacmanArea)getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(),targetPos);
			} while (path == null );
		
		//graphicPath= new Path(this.getPosition(), new LinkedList <Orientation >(path));
	}
	
	@Override
	protected Orientation getNextOrientation() {		
		if (path==null) {
            return super.getNextOrientation();
		}
		return path.poll();
	}
	
	/**
	 * @return the SuperGhost's target position
	 */
	protected DiscreteCoordinates getTargetPos() {
		return targetPos;
	}
	
	@Override
	public void update (float deltaTime) {
		
		if (!isDisplacementOccurs()) {
		
			nextOrientation = getNextOrientation();  
			
			if (this.getCurrentMainCellCoordinates().equals(targetPos) || isStateChanged()||nextOrientation==null) {
				this.generatePath();
				nextOrientation = this.getNextOrientation();
				setStateChanged(false);
			}  
			
			/// (extension) : the SuperGhost creates a new path each time the SuperPacmanPlayer changes his orientation
			if (getSuperPacman()!=null && !isAfraid()) {
				this.followPlayer();
				nextOrientation = this.getNextOrientation();
			}
			
			List <DiscreteCoordinates> nextCells = Collections.singletonList(getCurrentMainCellCoordinates().jump(nextOrientation.toVector()));	

			if(getOwnerArea().canEnterAreaCells(this, nextCells)) {				 
				orientate(nextOrientation);
			    } 
			
			if (isAfraid()) {
			move(getAnimationDurationGhost()/2);
			} else {
				move(getAnimationDurationGhost());
			}
		}	
		super.update(deltaTime);
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		//graphicPath.draw(canvas);
	}
}
