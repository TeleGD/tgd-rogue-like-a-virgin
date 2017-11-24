package map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entity.Entity;
import general.World;

public class Porte extends Case {

	public Porte(int x, int y,int difficulte) {
		super(x, y,difficulte);
		try {
			sprite = new Image(World.DIRECTORY_IMAGES+"door"+getCouleur()+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
