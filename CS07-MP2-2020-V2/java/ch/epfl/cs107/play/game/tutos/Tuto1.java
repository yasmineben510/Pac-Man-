package ch.epfl.cs107.play.game.tutos;

import ch.epfl.cs107.play.game.areagame.Area;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.tutos.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Ferme;
import ch.epfl.cs107.play.game.tutos.area.tuto1.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {

	private SimpleGhost player;
	
	private void createAreas() {
		super.addArea(new Ferme());
		super.addArea(new Village());
		
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Tuto1";
	}
	
	public void switchArea(){
		player.strengthen();
		super.getCurrentArea().unregisterActor(player);
		if(super.getCurrentArea().getTitle().equals("zelda/Village")) {
			super.setCurrentArea("zelda/Ferme", true);
		}
		
		else if(super.getCurrentArea().getTitle().equals( "zelda/Ferme")) {
			super.setCurrentArea("zelda/Village", false);
		}
        super.getCurrentArea().registerActor(player);
		super.getCurrentArea().setViewCandidate(player);
	}
	
	@Override 
	public boolean begin(Window window, FileSystem fileSystem) {
		
		if (super.begin(window, fileSystem)) {
			createAreas();
			super.setCurrentArea("zelda/Village", true);
			player = new SimpleGhost(new Vector(18,7),"ghost.1");
			super.getCurrentArea().registerActor(player);
			super.getCurrentArea().setViewCandidate(player);
			return true;
		} else 
			return false;
	}
	
	
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
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
		}
	}
	
	
	@Override
	public void end() {
		// by default does nothing
		// can save the game states if wanted
	} 
	

}
