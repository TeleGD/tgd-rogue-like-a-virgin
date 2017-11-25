package entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import map.CaseVide;


public class Item extends Entity {

	String type;
	
	public Item() throws SlickException{
		World.item = this;
		Random r = new Random();
		switch (r.nextInt(5)) {
        case 0:  
        	type = "SpeedUp";
        	sprite = new Image(World.DIRECTORY_IMAGES+"itemSpeedUp.png");
            break;
        case 1:
        	type = "SpeedDown";
        	sprite = new Image(World.DIRECTORY_IMAGES+"itemSpeedDown.png");
            break;
        case 2:  
        	type = "HpUp";
        	sprite = new Image(World.DIRECTORY_IMAGES+"itemHpUp.png");
            break;
		case 3:  
			type = "FireRateUp";
        	sprite = new Image(World.DIRECTORY_IMAGES+"itemFireRateUp.png");
            break;
		case 4:  
			type = "FireRateDown";
        	sprite = new Image(World.DIRECTORY_IMAGES+"itemFireRateDown.png");
            break;
		}
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
