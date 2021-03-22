package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;

import games.rogueLikeAVirgin.World;

public class Enemy2 extends Enemy1{

	public Enemy2(World world, float x, float y) {
		super(world, x, y);
		this.hp=3;
		this.imgB=AppLoader.loadPicture("/images/rogueLikeAVirgin/blobRoseBas.png");
		this.imgT=AppLoader.loadPicture("/images/rogueLikeAVirgin/blobRoseHaut.png");
		this.imgR=AppLoader.loadPicture("/images/rogueLikeAVirgin/blobRoseDroite.png");
		this.imgL=AppLoader.loadPicture("/images/rogueLikeAVirgin/blobRoseGauche.png");

		this.sprite=imgB;
		this.width=36;
		this.height=36;
		zoning();
		this.hitbox=new Rectangle (x+4,y+4,width-8,height-8);
		speed=0.1;
	}

}
