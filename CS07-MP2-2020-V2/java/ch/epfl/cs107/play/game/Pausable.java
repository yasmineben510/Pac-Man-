package ch.epfl.cs107.play.game;

public interface Pausable {

	void requestResume();
	
	
	void requestPause();
	
	
	boolean isPaused();


}
