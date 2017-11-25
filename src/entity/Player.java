package entity;

import java.util.ArrayList;

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
	
	

	public Player() throws SlickException{
		World.player = this;
		spriteU = new Image(World.DIRECTORY_IMAGES+"playerHaut.png");
		spriteR = new Image(World.DIRECTORY_IMAGES+"playerDroite.png");
		spriteD = new Image(World.DIRECTORY_IMAGES+"playerBas.png");
		spriteL = new Image(World.DIRECTORY_IMAGES+"playerGauche.png");
		hitbox = new Rectangle(x,y,width,height);
		width=36;
		height=36;
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
		if((up && !down) || (up && down && !updown)) {
			speedY=-speed;
		}
		if((down && !up) || (up && down && updown)) {
				speedY=speed;
		}
		if((left && !right)|| (left && right && !rightLeft)) {
			speedX = -speed;
		}
		if((!left && right)|| (left && right && rightLeft)) {
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
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		setDir();
		g.drawImage(sprite,(float) x,(float) y);
		
	}
}
