package games.rogueLikeAVirgin.map;

import app.AppLoader;

public class Mur extends Case {

	public Mur(int x, int y,int difficulte) {
		super(x,y,difficulte);
		deplacementPossible=false;
		sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/wallInterieur"+getCouleur()+".png");
	}

}
