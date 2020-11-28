package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {
	
	public final static float CAMERA_SCALE_FACTOR = 13.f;
	public final static float STEP = 0.05f;


	private SuperPacmanPlayer player;
	private final String[] areas = {"Superpacman/Level0", "Superpacman/Level","Superpacman/Level2"};
	private final DiscreteCoordinates[] startingPositions = {new DiscreteCoordinates(2,10), 
															 new DiscreteCoordinates(5,15)};

	private int areaIndex;
	/**
	 * Add all the areas
	 */
	private void createAreas(){

		addArea(new Ferme());
		addArea(new Village());

	}

	/// SuperPacman implements playable
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {


		if (super.begin(window, fileSystem)) {

			createAreas();
			areaIndex = 0;
			Area area = setCurrentArea(areas[areaIndex], true);
			player = new SuperPacmanPlayer(area, Orientation.RIGHT, startingPositions[areaIndex],"superpacman/bonus");
			area.registerActor(player);
			area.setViewCandidate(player);
			return true;
		}
		return false;
	}


	@Override
	public void end() {
	}


	protected void switchArea() {

		player.leaveArea();

		areaIndex = (areaIndex==0) ? 1 : 0;

		Area currentArea = setCurrentArea(areas[areaIndex], false);
		player.enterArea(currentArea, startingPositions[areaIndex]);

		player.strengthen();
	}
	
	// Returns the title of the game
	
	@Override
	public String getTitle() {
		return "Super Pac-Man";
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
	

}
