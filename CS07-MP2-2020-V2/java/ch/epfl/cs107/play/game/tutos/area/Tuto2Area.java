package ch.epfl.cs107.play.game.tutos.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.tutos.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

abstract public class Tuto2Area extends Area {

	
	private Window window;

	protected abstract void createArea();

	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
	   this.window = window;
	   if (super.begin(window, fileSystem)) {
		  setBehavior(new Tuto2Behavior(window, getTitle()));
		  createArea();
          return true;
       }
		  return false;
	}
	
	
	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 10.f;
	}

}
