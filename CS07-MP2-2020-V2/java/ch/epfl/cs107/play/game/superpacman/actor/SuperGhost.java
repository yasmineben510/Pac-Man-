package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

public abstract class SuperGhost extends Ghost {

	private DiscreteCoordinates targetPos;
	
	private Queue<Orientation> path;
	
	
	public SuperGhost(Area area, DiscreteCoordinates position, DiscreteCoordinates shelter, String spriteName) {
		super(area, position, shelter, spriteName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * generate the shortest path to follow the player if known
	 * @return the orientation for the shortest path
	 */
	protected Orientation followPlayer() {
			do {	
			 targetPos= getSuperPacman().getCurrentCells().get(0);
			 path = ((SuperPacmanArea)getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(),targetPos);
			} while (path == null );
		
		return path.poll();
	}
	
	/**
	 * generates a random path
	 * @return the orientation for a random path
	 */
	private Orientation generateRandomPath() {
		do {
			targetPos = new DiscreteCoordinates(RandomGenerator.getInstance().nextInt(getOwnerArea().getWidth()), RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight()));
			path = ((SuperPacmanArea)getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(),targetPos);
			
			} while (path == null );
		return path.poll();
	}
	
	
	@Override
	protected Orientation getNextOrientation() {		
		return generateRandomPath();
	}

	protected DiscreteCoordinates getTargetPos() {
		return targetPos;
	}

	protected void setTargetPos(DiscreteCoordinates targetPos) {
		this.targetPos = targetPos;
	}
	@Override
	public void update (float deltaTime) {
		
		
		super.update(deltaTime);
	}
	
}
