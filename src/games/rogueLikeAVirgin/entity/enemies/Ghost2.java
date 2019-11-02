package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Ghost2 extends Ghost1{


	public Ghost2(float x,float y) {
		super(x,y);
		hp=2;
		atk=3;
		compt=0;
		try {
			this.imgB=new Image("images/rogueLikeAVirgin/fantomeMocheBas.png");
			this.imgT=new Image("images/rogueLikeAVirgin/fantomeMocheHaut.png");
			this.imgR=new Image("images/rogueLikeAVirgin/fantomeMocheDroite.png");
			this.imgL=new Image("images/rogueLikeAVirgin/fantomeMocheGauche.png");

			this.sprite=imgB;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x+4,y+5,width-7,height-10);
	}

}
