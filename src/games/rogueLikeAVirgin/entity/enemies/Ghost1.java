package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;

import games.rogueLikeAVirgin.World;

public class Ghost1 extends Enemy{

	protected int compt;
	public Ghost1(World world, float x,float y) {
		super(world, x,y);
		hp=1;
		atk=2;
		compt=0;
		this.imgB=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeBas.png");
		this.imgT=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeHaut.png");
		this.imgR=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeDroite.png");
		this.imgL=AppLoader.loadPicture("/images/rogueLikeAVirgin/fantomeGauche.png");

		this.sprite=imgB;
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x+4,y+5,width-7,height-10);
	}

	@Override
	public void move(int delta) {
		//se déplace aleatoirement et ne tient pas compte des murs
		compt++;
		if(compt>50) {
			//a la fin du compteur on choisis une nouvelle direction
			chooseDir();
			compt=0;
		}

		//refaire chooseDir tant que l'on est trop pres des bords avec une vitesse inadequate
		boolean ok=false;
		while (!ok) {
			ok=true;
			if((x<100)&&(speedX<0)) {
				chooseDir();
				ok=false;
			}else if((x>630)&&(speedX>0)) {
				chooseDir();
				ok=false;
			}else if((y>630)&&(speedY>0)) {
				chooseDir();
				ok=false;
			} else if((y<100)&&(speedY<0)) {
				chooseDir();
				ok=false;
			}
		}

		x+=speedX*delta;
		y+=speedY*delta;
		hitbox.setX(x+4);
		hitbox.setY(y+5);
	}


	private void chooseDir() {
		double a=Math.random();
		if(a<0.125) {
			speedX=0.1;
			speedY=0.1;
			sprite=imgR;
		}else if(a<0.25) {
			speedX=0.1;
			speedY=0;
			sprite=imgR;
		}else if(a<0.375){
			speedX=0.1;
			speedY=-0.1;
			sprite=imgR;
		}else if(a<0.5){
			speedX=0;
			speedY=0.1;
			sprite=imgB;
		}else if (a<0.625) {
			speedX=0;
			speedY=-0.1;
			sprite=imgT;
		}else if(a<0.75) {
			speedX=-0.1;
			speedY=0;
			sprite=imgL;
		}else if(a<0.875){
			speedX=-0.1;
			speedY=0.1;
			sprite=imgL;
		}else {
			speedX=-0.1;
			speedY=-0.1;
			sprite=imgL;
		}
	}
}
