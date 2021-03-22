package games.rogueLikeAVirgin.map;

import app.AppLoader;

public class CaseVide extends Case{
	public CaseVide(int x,int y,int difficulte){
		super(x,y,difficulte);
		sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/floor"+getCouleur()+".png");
	}
}
