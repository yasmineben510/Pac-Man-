package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Ghost extends MovableAreaEntity implements Interactor{
	
	private final int GHOST_SCORE = 500; // static or not ? 
	private final static int RADIUS = 5;
	private SuperPacmanPlayer player = null;

	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates shelter) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
	}

	/// implements Interactable
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
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
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	/// implements Interactor
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> neighbor = new ArrayList<DiscreteCoordinates>();
		int x = getCurrentMainCellCoordinates().x;
		int y = getCurrentMainCellCoordinates().y;
		for(int i = x - RADIUS; i <= x + RADIUS ; ++i) { 
			for (int j = y - RADIUS; j <= y + RADIUS; ++i) {
				neighbor.add(new DiscreteCoordinates(i,j));
			}
		}
		return neighbor;
	}

	@Override
	public boolean wantsCellInteraction() {
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		return true;
	}

	@Override
	public void interactWith(Interactable other) {
		// TODO Auto-generated method stub
		
	}

}
