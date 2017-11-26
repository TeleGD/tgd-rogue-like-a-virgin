package entity.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;
import entity.Projectile;
import general.World;
import map.Bords;
import map.Case;
import map.Mur;

public abstract class Enemy extends Entity {

	protected Image imgR,imgL,imgT,imgB;
	public Enemy(float x,float y){
		hp=1;
		this.atk=1;
		World.enemies.add(this);
		try {
			this.sprite=new Image("images/RogueLike/blobBas.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x=x;
		this.y=y;
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x,y,width,height);
	}
	
	@Override
	public void die() {
		World.enemies.remove(this);
		World.player.setCoin(World.player.getCoin()+1);
		World.score += 20;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
	}
	
	public abstract void move(int delta); 
	
	
	/*appartition-----
	 * déplacement	
	 * mort 
	 * tuage de player non
	 * img non
	 * 
	 */
	
	
	@Override
	public void checkForCollision() {
		if(hitbox.intersects(World.player.getShape())){
			if(!World.player.isInvincible()) {
				World.player.setInvincible(true);
				World.player.setInvincibleTimer(World.player.getInvincibleTimerMax());
				this.setHP(hp-Math.max(World.player.getAtk()-def, 0));
			}
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
