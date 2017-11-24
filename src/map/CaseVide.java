package map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import general.World;

public class CaseVide extends Case{
	public CaseVide(int x,int y){
		super(x,y);
		try {
			sprite = new Image(World.DIRECTORY_IMAGES+"floor.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
