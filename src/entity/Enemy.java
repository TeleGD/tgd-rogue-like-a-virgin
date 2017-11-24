package entity;

import general.World;

public class Enemy extends Entity {

	public Enemy(){
		World.enemies.add(this);
	}
	
	@Override
	public void die() {
		World.enemies.remove(this);
	}

	@Override
	public void checkForCollision() {
		if(hitbox.intersects(World.player.getShape())){
			this.setHP(hp-Math.max(World.player.getAtk()-def, 0));
			if(hp <= 0) alreadyDead = true;
			return;
		}
		for(Projectile p : World.projectiles){
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

}
