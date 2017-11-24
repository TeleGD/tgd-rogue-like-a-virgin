package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import general.World;

public class Player extends Entity {

	public Player() throws SlickException{
		World.player = this;
		sprite = new Image(World.DIRECTORY_IMAGES+"player.png");
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

}
