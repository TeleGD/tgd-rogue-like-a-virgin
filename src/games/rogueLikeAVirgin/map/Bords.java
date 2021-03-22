package games.rogueLikeAVirgin.map;

import app.AppLoader;

public class Bords extends Case {

	public Bords(int x, int y) {
		super(x,y);
		deplacementPossible=false;
		sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/wall.png");
	}
}
