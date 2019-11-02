package games.rogueLikeAVirgin.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import games.rogueLikeAVirgin.World;

public class Bords extends Case {

	public Bords(int x, int y) {
		super(x,y);
		deplacementPossible=false;
		try {
			sprite = new Image(World.DIRECTORY_IMAGES+"wall.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
