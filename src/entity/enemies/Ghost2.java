package entity.enemies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Ghost2 extends Ghost1{


	public Ghost2(float x,float y) {
		super(x,y);
		hp=1;
		atk=3;
		compt=0;
		try {
			this.imgB=new Image("images/RogueLike/fantomeMocheBas.png");
			this.imgT=new Image("images/RogueLike/fantomeMocheHaut.png");
			this.imgR=new Image("images/RogueLike/fantomeMocheDroite.png");
			this.imgL=new Image("images/RogueLike/fantomeMocheGauche.png");
			
			this.sprite=imgB;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x,y,width,height);
	}
	
}
