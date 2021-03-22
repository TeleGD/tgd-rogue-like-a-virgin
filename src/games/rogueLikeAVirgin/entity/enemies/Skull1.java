package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.entity.Projectile;
import games.rogueLikeAVirgin.map.Case;
import games.rogueLikeAVirgin.map.Mur;

public class Skull1 extends Enemy{

	protected Polygon zoneR,zoneL,zoneT,zoneB;
	protected double speed;
	private int compt;

	public Skull1(World world, float x, float y) {
		super(world, x, y);
		hp=1;
		this.imgB=AppLoader.loadPicture("/images/rogueLikeAVirgin/squeletteBas.png");
		this.imgT=AppLoader.loadPicture("/images/rogueLikeAVirgin/squeletteHaut.png");
		this.imgR=AppLoader.loadPicture("/images/rogueLikeAVirgin/squeletteDroite.png");
		this.imgL=AppLoader.loadPicture("/images/rogueLikeAVirgin/squeletteGauche.png");

		this.sprite=imgB;
		this.width=36;
		this.height=36;
		zoning();
		this.hitbox=new Rectangle (x,y,width,height);
		speed = 0.07;
	}


	public void zoning() {
		// creating the zones used to know the direction to go
		zoneL=new Polygon();
		zoneL.addPoint(x+width/2, y+height/2);
		zoneL.addPoint(x+width/2-1000, y+height/2-1000);
		zoneL.addPoint(x+width/2-1000, y+height/2+1000);
		zoneR=new Polygon();
		zoneR.addPoint(x+width/2, y+height/2);
		zoneR.addPoint(x+width/2+1000, y+height/2-1000);
		zoneR.addPoint(x+width/2+1000, y+height/2+1000);
		zoneT=new Polygon();
		zoneT.addPoint(x+width/2, y+height/2);
		zoneT.addPoint(x+width/2-1000, y+height/2-1000);
		zoneT.addPoint(x+width/2+1000, y+height/2-1000);
		zoneB=new Polygon();
		zoneB.addPoint(x+width/2, y+height/2);
		zoneB.addPoint(x+width/2-1000, y+height/2+1000);
		zoneB.addPoint(x+width/2+1000, y+height/2+1000);

	}

	@Override
	public void move(int delta) {

		if (Math.pow(Math.pow((this.world.player.getX()+this.world.player.getWidth()/2)-x, 2)+Math.pow((this.world.player.getY()+this.world.player.getHeight()/2)-y, 2),0.5)>=200){
			if(zoneT.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight())) {
				speedX=0;
				speedY=-speed;
			}else if(zoneB.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight())) {
				speedX=0;
				speedY=speed;
			}else if(zoneL.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight())) {
				speedX=-speed;
				speedY=0;
			}else {
				speedX=speed;
				speedY=0;
			}
		} else {
			speedX=0;
			speedY=0;
		}




		/*il faut voir pour les murs et bouger en cons�quence
		 *
		 * */
		Case[][] c=this.world.map.getCases();

		if (speedX>0){
			//going to the right
			int a= (int) (x+speedX*delta+width)/36; //right border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)) {
				speedX=0;
				if(this.world.player.getY()>y) {
					speedY=speed;
				}else {
					speedY=-speed;
				}
			}

		}else if (speedX<0) {
			//going to the left
			int a= (int) (x+speedX*delta)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)) {
				speedX=0;
				if(this.world.player.getY()>y) {
					speedY=speed;
				}else {
					speedY=-speed;
				}
			}

		}else if(speedY>0) {
			//going down
			int a= (int) (y+speedY*delta+height)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (x/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (x+width)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)) {
				speedY=0;
				if(this.world.player.getX()>x) {
					speedX=speed;
				}else {
					speedX=-speed;
				}
			}

		}else {
			//going top
			int a= (int) (y+speedY*delta)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (x/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (x+width)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)) {
				speedY=0;
				if(this.world.player.getX()>x) {
					speedX=speed;
				}else {
					speedX=-speed;
				}
			}
		}

		if((zoneT.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight()))) {
			sprite=imgT;
		}else if (zoneB.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight())) {
			sprite=imgB;
		}else if(zoneL.contains(this.world.player.getX()+this.world.player.getWidth(), this.world.player.getY()+this.world.player.getHeight())) {
			sprite=imgL;
		}else {
			sprite=imgR;
		}


		x+=speedX*delta;
		y+=speedY*delta;
		hitbox.setX(x);
		hitbox.setY(y);
	}

	private void shoot() {
		new Projectile(this.world, this.x+width/2,this.y+height/2,false,(this.world.player.getX()-x)/1500,(this.world.player.getY()-y)/1500);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		zoning();
		//move(delta);
		if(compt>60) {
			compt=0;
			shoot();
		}
		compt++;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		super.render(container, game, g);
	}

}
