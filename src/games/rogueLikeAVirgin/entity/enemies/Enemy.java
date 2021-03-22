package games.rogueLikeAVirgin.entity.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.entity.Entity;
import games.rogueLikeAVirgin.entity.Projectile;

public abstract class Enemy extends Entity {

	protected Image imgR,imgL,imgT,imgB;
	public Enemy(World world, float x,float y){
		super(world);
		hp=1;
		this.atk=1;
		this.world.enemies.add(this);
		this.sprite=AppLoader.loadPicture("/images/rogueLikeAVirgin/blobBas.png");
		this.x=x;
		this.y=y;
		this.width=36;
		this.height=36;
		this.hitbox=new Rectangle (x+4,y+4,width-8,height-8);
	}

	@Override
	public void die() {
		this.world.enemies.remove(this);
		this.world.player.setCoin(this.world.player.getCoin()+1);
		this.world.score += 20;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		super.render(container, game, g);
	}

	@Override
	public abstract void move(int delta);


	/*appartition-----
	 * d�placement
	 * mort
	 * tuage de player non
	 * img non
	 *
	 */


	@Override
	public void checkForCollision() {
		if(hitbox.intersects(this.world.player.getShape())){
			if(!this.world.player.isInvincible()) {
				this.world.player.setInvincible(true);
				this.world.player.setInvincibleTimer(this.world.player.getInvincibleTimerMax());
				this.setHP(hp-Math.max(this.world.player.getAtk()-def, 0));
			}
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

}
