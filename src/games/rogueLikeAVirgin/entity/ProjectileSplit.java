package games.rogueLikeAVirgin.entity;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import games.rogueLikeAVirgin.World;

public class ProjectileSplit extends Projectile {
	int t,tMax, frag;
	boolean recursive;
	Projectile child;
	double speed,angle;

	public ProjectileSplit(World world, float x, float y, float speedX,float speedY, boolean friendly,int dt,int fragments) {
		super(world, x,y,friendly,speedX,speedY);
		tMax=dt;
		t=dt;
		frag=fragments;
	}

	public ProjectileSplit(World world, int i, int j,double speedX,double speedY, boolean friendly, int dt){
		super(world, i,j,friendly);
		t = dt;
		tMax = dt;
		frag = 2;
		recursive = false;
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public ProjectileSplit(World world, int i, int j,double speedX,double speedY, boolean friendly, int dt, int fragments){
		super(world, i,j,friendly);
		t = dt;
		tMax = dt;
		frag = fragments;
		recursive = false;
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public ProjectileSplit(World world, int i, int j,double speedX,double speedY, boolean friendly, int dt, int fragments, boolean recursive){
		super(world, i,j,friendly);
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

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		checkForCollision();
		move(delta);
		t -= delta;
		/*if(t <= 0){
			if(recursive){
				for(int a = 0; a < frag /2; a++){
					angle = -90 + a*90/frag;
					child = new ProjectileSplit((int)x/36,(int)y/36,speedX*Math.cos(angle)-speedY*Math.sin(angle),speedY*Math.cos(angle)+speedX*Math.sin(angle),getFriendly(),tMax,frag,recursive);
					angle = 90 - a*90/frag;
					child = new ProjectileSplit((int)x/36,(int)y/36,speedX*Math.cos(angle)-speedY*Math.sin(angle),speedY*Math.cos(angle)+speedX*Math.sin(angle),getFriendly(),tMax,frag,recursive);
				}
			}
			alreadyDead = true;
		}*/
		if(t<=0) {
			for(int a=1;a<=frag;a++) {
				angle = -90+a*180/(frag-1);
				new Projectile(this.world, x,y,friendly,speedX*Math.cos(angle)-speedY*Math.sin(angle),speedY*Math.cos(angle)+speedX*Math.sin(angle));
			}
			alreadyDead=true;
		}
		if(alreadyDead) die();
	}
}
