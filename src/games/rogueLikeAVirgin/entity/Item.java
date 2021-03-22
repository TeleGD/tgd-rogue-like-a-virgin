package games.rogueLikeAVirgin.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.map.Case;
import games.rogueLikeAVirgin.map.CaseVide;


public class Item extends Entity {

	String type;

	public Item(World world) {
		super(world);
		this.world.item.add(this);
		Random r = new Random();
		switch (r.nextInt(8)) {
        case 0:
        	type = "SpeedUp";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemSpeedUp.png");
            break;
        case 1:
        	type = "SpeedDown";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemSpeedDown.png");
            break;
        case 2:
        	type = "HpUp";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemHpUp.png");
            break;
		case 3:
			type = "FireRateUp";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemFireRateUp.png");
            break;
		case 4:
			type = "FireRateDown";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemFireRateDown.png");
            break;
        default:
        	type = "Coin";
        	sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemCoin.png");
        	break;
		}
		Case[][] c = this.world.map.getCases();
    	boolean caseNotFound = true;
    	int posx;
    	int posy;
    	while(caseNotFound){
    		posx = (r.nextInt(17)+2);
    		posy = (r.nextInt(17)+2);
        	if(c[posx][posy] instanceof CaseVide){
        		x = posx*36;
        		y = posy*36;
        		caseNotFound = false;
        	}
    	}
		width=36;
		height=36;
		hitbox = new Rectangle(x,y,width,height);
	}

	@Override
	public void checkForCollision() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(sprite,x,y);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

}
