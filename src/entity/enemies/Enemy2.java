package entity.enemies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy2 extends Enemy1{

	public Enemy2(float x, float y) {
		super(x, y);
		this.hp=3;
		try {
			this.imgB=new Image("images/RogueLike/blobRoseBas.png");
			this.imgT=new Image("images/RogueLike/blobRoseHaut.png");
			this.imgR=new Image("images/RogueLike/blobRoseDroite.png");
			this.imgL=new Image("images/RogueLike/blobRoseGauche.png");
			
			this.sprite=imgB;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.width=36;
		this.height=36;
		zoning();
		this.hitbox=new Rectangle (x,y,width,height);
		speed=0.1;
	}

}
