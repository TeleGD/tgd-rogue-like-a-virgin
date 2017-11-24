package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ProjectileSplit extends Projectile {
	int t,tMax, frag;
	boolean recursive;
	Projectile child;
	double speed,angle;
	
	public ProjectileSplit(int i, int j,double speedX,double speedY, boolean friendly, int dt){
		super(i,j,friendly);
		t = dt;
		tMax = dt;
		frag = 2;
		recursive = false;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public ProjectileSplit(int i, int j,double speedX,double speedY, boolean friendly, int dt, int fragments){
		super(i,j,friendly);
		t = dt;
		tMax = dt;
		frag = fragments;
		recursive = false;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public ProjectileSplit(int i, int j,double speedX,double speedY, boolean friendly, int dt, int fragments, boolean recursive){
		super(i,j,friendly);
		if(dt > 0){
			t = dt;
			tMax = dt;
		}else{
			t = 100;
			tMax = 100;
		}
		this.speedX = speedX;
		this.speedY = speedY;
		if(fragments >0){
			if(recursive){//On veut eviter la superposition des tirs en cas de recursivite.
				frag = fragments - fragments % 2;
				speed = Math.sqrt(speedX*speedX+speedY*speedY);
			}else{
				frag = fragments;
			}
		}else{
			frag = 2;
		}
		this.recursive = recursive;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
		
		t -= delta;
		if(t <= 0){
			if(recursive){
				for(int a = 0; a < frag /2; a++){
					angle = -90 + a*90/frag;
					child = new ProjectileSplit((int)x/36,(int)y/36,speedX*Math.cos(angle)-speedY*Math.sin(angle),speedY*Math.cos(angle)+speedX*Math.sin(angle),getFriendly(),tMax,frag,recursive);
					angle = 90 - a*90/frag;
					child = new ProjectileSplit((int)x/36,(int)y/36,speedX*Math.cos(angle)-speedY*Math.sin(angle),speedY*Math.cos(angle)+speedX*Math.sin(angle),getFriendly(),tMax,frag,recursive);
				}				
			}
			alreadyDead = true;
		}
		if(alreadyDead) die();
	}
}
