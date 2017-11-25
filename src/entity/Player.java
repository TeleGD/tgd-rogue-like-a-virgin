package entity;

import java.util.ArrayList;

import map.Case;
import map.Mur;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;

public class Player extends Entity {
	
	private boolean up;
	private boolean down;
	private boolean updown;
	private boolean right;
	private boolean left;
	private boolean rightLeft;
	private double speed;
	private int direction;
	private Image spriteU;
	private Image spriteR;
	private Image spriteD;
	private Image spriteL;
	private boolean nord;
	private boolean est;
	private boolean sud;
	private boolean ouest;
	private boolean nordsud;
	private boolean estouest;
	private int periodeTir;
	private int attenteTir;
	private ArrayList<Projectile> playerProjectiles;
	private boolean tir;
	private double projSpeed;
	private double projSpeedX;
	private double projSpeedY;
	private boolean deplacementPossibleHaut = true;
	private boolean deplacementPossibleBas = true;
	private boolean deplacementPossibleDroite = true;
	private boolean deplacementPossibleGauche = true;
	private boolean collision = false;
	
	

	public Player() throws SlickException{
		World.player = this;
		x=40;
		y=40;
		spriteU = new Image(World.DIRECTORY_IMAGES+"playerHaut.png");
		spriteR = new Image(World.DIRECTORY_IMAGES+"playerDroite.png");
		spriteD = new Image(World.DIRECTORY_IMAGES+"playerBas.png");
		spriteL = new Image(World.DIRECTORY_IMAGES+"playerGauche.png");
		width=36;
		height=36;
		hitbox = new Rectangle(x,y,width,height);
		speed=0.25;
		direction=2;
		sprite=spriteD;
		playerProjectiles=new ArrayList<Projectile>();
		periodeTir=50;
		attenteTir=0;
		tir=false;
		projSpeed=0.4;
		
	}
	
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkForCollision() {
		

		int tmpI,tmpJ;
		Case[][] c = World.map.getCases();
		tmpI = (int) x/36;
		tmpJ = (int) y/36;
		for(int deltaI = -1; deltaI < 2; deltaI++){
			for(int deltaJ = -1; deltaJ < 2; deltaJ++){
				collision = c[tmpI+deltaI][tmpJ+deltaJ].getHitbox().intersects(hitbox);
				if(!(deltaI == 0 && deltaJ == 0) && tmpI+deltaI >= 0 && tmpJ+deltaJ >= 0 && tmpI+deltaI < c.length && tmpJ+deltaJ < c[tmpI].length){
					if(collision && (c[tmpI+deltaI][tmpJ+deltaJ] instanceof Mur)){
						if (deltaI<0) deplacementPossibleGauche = false;
						else deplacementPossibleDroite = false;
						//System.out.println("mur");
						if (deltaJ<0) deplacementPossibleHaut = false;
						else deplacementPossibleBas = false;
					}
				} else if(!(c[tmpI+deltaI][tmpJ+deltaJ] instanceof Mur)) {
					deplacementPossibleGauche = true;
					deplacementPossibleDroite = true;
					deplacementPossibleHaut = true;
					deplacementPossibleBas = true;
					//System.out.println("deplacement");
				}

			}
		}
		//pour que la collision à droite et en bas fonctionne avec le mur
		for(int deltaI = 1; deltaI > -2; deltaI--){
			for(int deltaJ = 1; deltaJ > -2; deltaJ--){
				collision = c[tmpI+1+deltaI][tmpJ+1+deltaJ].getHitbox().intersects(hitbox);
				if(!(deltaI == 0 && deltaJ == 0) && tmpI+1+deltaI >= 0 && tmpJ+1+deltaJ >= 0 && tmpI+1+deltaI < c.length && tmpJ+1+deltaJ < c[tmpI+1].length){
					if(collision && (c[tmpI+1+deltaI][tmpJ+1+deltaJ] instanceof Mur)){
						if (deltaI<0) deplacementPossibleGauche = false;
						else deplacementPossibleDroite = false;
						//System.out.println("mur");
						if (deltaJ<0) deplacementPossibleHaut = false;
						else deplacementPossibleBas = false;
					}
				} else if(!(c[tmpI+1+deltaI][tmpJ+1+deltaJ] instanceof Mur)) {
					deplacementPossibleGauche = true;
					deplacementPossibleDroite = true;
					deplacementPossibleHaut = true;
					deplacementPossibleBas = true;
					//System.out.println("deplacement");
				}

			}
		}
		for(Enemy e : World.enemies){
			if(hitbox.intersects(e.getShape())){
				this.setHP(hp-Math.max(e.getAtk()-def, 0));
				if(hp <= 0) alreadyDead = true;
				return;
			}
		}
		for(Projectile p : World.projectiles){
			if(!p.getFriendly()){
				if(hitbox.intersects(p.getShape())){
					this.setHP(hp-Math.max(p.getAtk()-def, 0));
					if(hp <= 0) alreadyDead = true;
					p.die();
					return;
				}
			}
		}
		if(World.item != null){
			if(hitbox.intersects(World.item.getShape())){
				World.item.alreadyDead = true;
				speed *= 1.33;
				return;
			}
		}
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void keyPressed(int key, char c) {
		switch (key){

		case Input.KEY_Z:
			up = true;
			updown=false;
			break;

		case Input.KEY_S:
			down=true;
			updown=true;
			break;

		case Input.KEY_Q:
			left=true;
			rightLeft=false;
			break;
		case Input.KEY_D:
			right=true;
			rightLeft=true;
			break;
			
			
		case Input.KEY_UP:
			nord = true;
			nordsud=false;
			break;

		case Input.KEY_DOWN:
			sud=true;
			nordsud=true;
			break;

		case Input.KEY_LEFT:
			ouest=true;
			estouest=false;
			break;
		case Input.KEY_RIGHT:
			est=true;
			estouest=true;
			break;
		case Input.KEY_ESCAPE:
			System.exit(0);
			break;

		}

	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_Z:
			up=false;
			break;
		case Input.KEY_D:
			right=false;
			break;
		case Input.KEY_Q:
			left=false;
			break;
		case Input.KEY_S:
			down=false;
			break;
		
		case Input.KEY_UP:
			nord=false;
			break;
		case Input.KEY_RIGHT:
			est=false;
			break;
		case Input.KEY_LEFT:
			ouest=false;
			break;
		case Input.KEY_DOWN:
			sud=false;
			break;
		}
	}
	
	public void move(int dt) {
		speedX = 0;
		speedY = 0;
		if(((up && !down) || (up && down && !updown)) && deplacementPossibleHaut) {
			speedY=-speed;
		}
		if(((down && !up) || (up && down && updown)) && deplacementPossibleBas) {
				speedY=speed;
		}
		if(((left && !right)|| (left && right && !rightLeft)) && deplacementPossibleGauche) {
			speedX = -speed;
		}
		if(((!left && right)|| (left && right && rightLeft)) && deplacementPossibleDroite) {
				speedX = speed;
		}
		if (speedX!=0 && speedY!=0) {
			speedX/=Math.sqrt(2);
			speedY/=Math.sqrt(2);
		}
		
		x+=dt*speedX;
		y+=dt*speedY;
	}
	
	public void setDir() {
		direction=2;
		tir=false;
		sprite=spriteD;
		projSpeedX=0;
		projSpeedY=0;
		
		if((nord && !sud) || (nord && sud && !nordsud)) {
			direction=0;
			sprite=spriteU;
			projSpeedX=-projSpeed;
			tir=true;
		}
		if((sud && !nord) || (nord && sud && nordsud)) {
			direction=2;
			sprite=spriteD;
			projSpeedX=projSpeed;
			tir=true;
		}
		if((ouest && !est)|| (ouest && est && !estouest)) {
			direction=3;
			sprite=spriteL;
			projSpeedY=-projSpeed;
			tir=true;
		}
		if((!ouest && est)|| (ouest && est && estouest)) {
			direction=1;
			sprite=spriteR;
			projSpeedY=projSpeed;
			tir=true;
		}
		
		if (tir && attenteTir==0) {
			playerProjectiles.add(new Projectile(x,y,true,projSpeedY,projSpeedX));
			attenteTir=periodeTir;
		}
		if (attenteTir>0) {
			attenteTir--;
		}
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		hitbox.setX(x);
		hitbox.setY(y);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		setDir();
		g.drawImage(sprite,(float) x,(float) y);
		g.draw(hitbox);
	}
}
