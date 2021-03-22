package games.rogueLikeAVirgin.map;

import app.AppLoader;

import games.rogueLikeAVirgin.entity.Entity;

public class Piques extends Case {

	public Piques(int x, int y,int difficulte){
		super(x,y,difficulte);
		deplacementPossible=false;
		sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/pick"+getCouleur()+".png");
	}

	@Override
	public void ArriveSur(Entity e) {
		//e prend des degats
	}

}
