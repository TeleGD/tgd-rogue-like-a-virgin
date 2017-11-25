package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import map.CaseVide;


public class Item extends Entity {

	public Item() throws SlickException{
		World.item = this;
		sprite = new Image(World.DIRECTORY_IMAGES+"itemBoost.png");
		width=36;
		height=36;
		x=342;
		y=342;
		hitbox = new Rectangle(x,y,width,height);
	}
	
	public void die() {
		World.item = null;
	}

	public void checkForCollision() {
		// TODO Auto-generated method stub	
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(alreadyDead) die();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,342,342);
	}

}
