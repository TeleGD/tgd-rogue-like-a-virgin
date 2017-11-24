package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import general.World;

public class Player extends Entity {
	
	private boolean up;
	private boolean down;
	private boolean updown;
	private boolean right;
	private boolean left;
	private boolean rightLeft;

	public Player() throws SlickException{
		World.player = this;
		sprite = new Image(World.DIRECTORY_IMAGES+"player.png");
		hitbox = new Rectangle(x,y,width,height);
		width=36;
		height=36;
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
	
	public void keyPressed(int key, char c) {
		switch (key){

		case Input.KEY_UP:
			up = true;
			updown=false;
			break;

		case Input.KEY_DOWN:
			down=true;
			updown=true;
			break;

		case Input.KEY_LEFT:
			left=true;
			rightLeft=false;
			break;
		case Input.KEY_RIGHT:
			right=true;
			rightLeft=true;
			break;
		case Input.KEY_ESCAPE:
			System.exit(0);
			break;
		}

	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			up=false;
			break;
		case Input.KEY_RIGHT:
			right=false;
			break;
		case Input.KEY_LEFT:
			left=false;
			break;
		case Input.KEY_DOWN:
			down=false;
			break;
		}
	}
	
	public void move(int dt) {
		speedX = 0;
		speedY = 0;
		if((up && !down) || (up && down && !updown)) {
			speedY=-0.2;
		}
		if((down && !up) || (up && down && updown)) {
				speedY=0.2;
		}
		if((left && !right)|| (left && right && !rightLeft)) {
			speedX = -0.2;
		}
		if((!left && right)|| (left && right && rightLeft)) {
				speedX = 0.2;
		}
		
		x+=dt*speedX;
		y+=dt*speedY;
	}
	
}
