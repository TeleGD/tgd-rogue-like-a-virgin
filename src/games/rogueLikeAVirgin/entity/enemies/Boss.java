package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.entity.Projectile;
import games.rogueLikeAVirgin.entity.ProjectileSplit;
import games.rogueLikeAVirgin.map.Case;
import games.rogueLikeAVirgin.map.Mur;

public class Boss extends Enemy{

	private Polygon zoneR,zoneL,zoneT,zoneB;
	private double speed;
	private int compt;

	public Boss(World world, float x, float y) {
		super(world, x, y);
		hp=10;
		compt=0;
		this.imgB=AppLoader.loadPicture("/images/rogueLikeAVirgin/bossBas.png");
		this.imgT=AppLoader.loadPicture("/images/rogueLikeAVirgin/bossHaut.png");
		this.imgR=AppLoader.loadPicture("/images/rogueLikeAVirgin/bossDroite.png");
		this.imgL=AppLoader.loadPicture("/images/rogueLikeAVirgin/bossGauche.png");

		this.sprite=imgB;
		this.width=72;
		this.height=72;
		zoning();
		this.hitbox=new Rectangle (x,y,width,height);
		speed = 0.15;

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


		/*il faut voir pour les murs et bouger en cons�quence
		 *
		 * */
		Case[][] c=this.world.map.getCases();

		if (speedX>0){
			//going to the right
			int a= (int) (x+speedX*delta+width)/36; //right border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height/2)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			int b2= (int) (y+height)/36;
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)||(c[a][b2] instanceof Mur)) {
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
			int b1= (int) (y+height/2)/36;
			int b2= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)|| (c[a][b2] instanceof Mur)) {
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
			int b1= (int) (x+width/2)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			int b2= (int) (x+width)/36;
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)||(c[b2][a]instanceof Mur)) {
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
			int b1= (int) (x+width/2)/36;
			int b2= (int) (x+width)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)||(c[b2][a]instanceof Mur)) {
				speedY=0;
				if(this.world.player.getX()>x) {
					speedX=speed;
				}else {
					speedX=-speed;
				}
			}
		}

		if(speedX>0) {
			sprite=imgR;
		}else if (speedX<0) {
			sprite=imgL;
		}else if(speedY>0) {
			sprite=imgB;
		}else {
			sprite=imgT;
		}

		x+=speedX*delta;
		y+=speedY*delta;
		hitbox.setX(x);
		hitbox.setY(y);
	}

	@Override
	public void checkForCollision() {
		if(hitbox.intersects(this.world.player.getShape())){
			if(hp <= 0) alreadyDead = true;
			return;
		}
		for(Projectile p : this.world.projectiles){
			if(p.getFriendly()){
				if(hitbox.intersects(p.getShape())){
					this.setHP(hp-Math.max(p.getAtk()-def, 0));
					if(hp <= 0) alreadyDead = true;
					p.die();
					return;
				}
			}

		}
	}

	private void shoot() {
		new ProjectileSplit(this.world, this.x+width/2,this.y+height/2,0.2f,0,false,10,3);
		new ProjectileSplit(this.world, this.x+width/2,this.y+height/2,-0.2f,0,false,10,3);
		new ProjectileSplit(this.world, this.x+width/2,this.y+height/2,0,0.2f,false,10,3);
		new ProjectileSplit(this.world, this.x+width/2,this.y+height/2,0,-0.2f,false,10,3);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		zoning();
		if(compt>40) {
			compt=0;
			shoot();
		}
		compt++;
		//move(delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		super.render(container, game, g);
		g.draw(hitbox);
	}

	@Override
	public void die() {
		super.die();
		this.world.score += 230;
		this.world.player.setCoin(this.world.player.getCoin()+100);
	}
}
