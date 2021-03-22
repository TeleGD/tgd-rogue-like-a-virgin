package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;

import games.rogueLikeAVirgin.World;

public class Ghost2 extends Ghost1{


	public Ghost2(World world, float x,float y) {
		super(world, x,y);
		hp=2;
		atk=3;
		compt=0;
		this.imgB=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeMocheBas.png");
		this.imgT=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeMocheHaut.png");
		this.imgR=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeMocheDroite.png");
		this.imgL=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeMocheGauche.png");

		this.sprite=imgB;
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x+4,y+5,width-7,height-10);
	}

}
