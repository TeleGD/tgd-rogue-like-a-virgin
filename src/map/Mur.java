package map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import general.World;

public class Mur extends Case {
	
	public Mur(int x, int y,int difficulte) {
		super(x,y,difficulte);
		deplacementPossible=false;
		try {
			sprite = new Image(World.DIRECTORY_IMAGES+"wallInterieur"+getCouleur()+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
