package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.game.tutos.area.tuto2.Ferme;

public class Tuto2 extends AreaGame {

	private SimpleGhost player;
	
	
	
	private void createAreas() {
		addArea(new Ferme());
		addArea(new Village());
		
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Tuto2";
	}
	
	public void switchArea(){
		player.strengthen();
		getCurrentArea().unregisterActor(player);
		if(getCurrentArea().getTitle().equals("zelda/Village")) {
		    setCurrentArea("zelda/Ferme", true);
		}
		
		else if(getCurrentArea().getTitle().equals( "zelda/Ferme")) {
			setCurrentArea("zelda/Village", false);
		}
        super.getCurrentArea().registerActor(player);
		super.getCurrentArea().setViewCandidate(player);
	}
	
	@Override 
	public boolean begin(Window window, FileSystem fileSystem) {
		
		if (super.begin(window, fileSystem)) {
			createAreas();
			setCurrentArea("zelda/Ferme", true);
			
			// creation simpleghost player
			player = new SimpleGhost(new Vector(18,7),"ghost.1");
		    // enregistrement acteur 
			getCurrentArea().registerActor(player);
			getCurrentArea().setViewCandidate(player);
			return true;
		} else 
			return false;
	}
	
	
	
	@Override
	public void update(float deltaTime) {
		/*update(deltaTime);
		Keyboard keyboard = getWindow().getKeyboard();
		Button key =keyboard.get(Keyboard.UP);
		if(key.isDown()) {
			player.moveUp();
		}
		key =keyboard.get(Keyboard.DOWN);
		if(key.isDown()) {
			player.moveDown();
		}
		key =keyboard.get(Keyboard.LEFT);
		if(key.isDown()) {
			player.moveLeft();
		}
		key =keyboard.get(Keyboard.RIGHT);
		if(key.isDown()) {
			player.moveRight();
		}
		if(player.isWeak()) {
			switchArea();
		} */
	}
	
	
	@Override
	public void end() {
		// by default does nothing
		// can save the game states if wanted
	} 
	

}
