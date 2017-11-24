package entity;

import general.World;

public class Projectile extends Entity{

	private boolean friendly;
	
	public Projectile(){
		World.projectiles.add(this);
	}
	
	public boolean getFriendly(){
		return friendly;
	}
	
	public void setFriendly(boolean friendly){
		this.friendly = friendly;
	}
	
	@Override
	public void die() {
		World.projectiles.remove(this);
	}

	@Override
	public void checkForCollision() {
		// TODO Auto-generated method stub
		
	}

}
