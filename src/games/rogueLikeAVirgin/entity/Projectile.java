package games.rogueLikeAVirgin.entity;

import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.map.Bords;
import games.rogueLikeAVirgin.map.Case;
import games.rogueLikeAVirgin.map.Mur;
import games.rogueLikeAVirgin.map.Porte;

public class Projectile extends Entity{

	protected boolean friendly;

	public Projectile(World world){
		super(world);
		this.world.projectiles.add(this);
		atk = 1;
	}

	public Projectile(World world, float x, float y, boolean friendly,double vitX,double vitY){
		super(world);
		this.friendly = friendly;
		this.x = x;
		this.y = y;
		height = 16;
		width = 16;
		//On reduit la hitbox pour pouvoir passer sans problemes dans des espaces d'une case de large
		hitbox = new Rectangle(x+4,y+4,width-8,height-8);
		this.world.projectiles.add(this);
		atk = 1;
		speedX=vitX;
		speedY=vitY;
		if(friendly){
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/coeur.png");
		}else{
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/carreau.png");
		}
	}

	public Projectile(World world, float x, float y, boolean friendly,double vitX,double vitY,int atk){
		super(world);
		this.friendly = friendly;
		this.x = x;
		this.y = y;
		this.atk = atk;
		height = 16;
		width = 16;
		//On reduit la hitbox pour pouvoir passer sans problemes dans des espaces d'une case de large
		hitbox = new Rectangle(x+4,y+4,width-8,height-8);
		this.world.projectiles.add(this);
		speedX=vitX;
		speedY=vitY;
		if(friendly){
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/coeur.png");
		}else{
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/carreau.png");
		}
	}

	public Projectile(World world, int i, int j, boolean friendly){
		super(world);
		this.friendly = friendly;
		this.x = 36*i;
		this.y = 36*j;
		height = 16;
		width = 16;
		hitbox = new Rectangle(x,y,width,height);
		this.world.projectiles.add(this);
		atk = 1;
		if(friendly){
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/coeur.png");
		}else{
			sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/carreau.png");
		}
	}

	public boolean getFriendly(){
		return friendly;
	}

	public void setFriendly(boolean friendly){
		this.friendly = friendly;
	}

	@Override
	public void die() {
		this.world.projectiles.remove(this);
	}

	@Override
	public void checkForCollision() {
		int tmpI,tmpJ;
		Case[][] c = this.world.map.getCases();
		tmpI = (int) x/36;
		tmpJ = (int) y/36;
		for(int deltaI = -1; deltaI < 2; deltaI++){
			for(int deltaJ = -1; deltaJ < 2; deltaJ++){
				if(!(deltaI == 0 && deltaJ == 0) && tmpI+deltaI >= 0 && tmpJ+deltaJ >= 0 && tmpI+deltaI < c.length && tmpJ+deltaJ < c[tmpI].length){
					if(c[tmpI+deltaI][tmpJ+deltaJ].getHitbox().intersects(hitbox) && (c[tmpI+deltaI][tmpJ+deltaJ] instanceof Mur || c[tmpI+deltaI][tmpJ+deltaJ] instanceof Bords || c[tmpI+deltaI][tmpJ+deltaJ] instanceof Porte)){
						alreadyDead = true;
					}
				}
			}
		}

		// pour que les projectiles ne traversent pas les murs
		for(int deltaI = 1; deltaI > -2; deltaI--){
			for(int deltaJ = 1; deltaJ > -2; deltaJ--){
				if (tmpI+1<c.length){
					if(!(deltaI == 0 && deltaJ == 0) && tmpI+1+deltaI >= 0 && tmpJ+1+deltaJ >= 0 && tmpI+1+deltaI < c.length && tmpJ+1+deltaJ < c[tmpI+1].length){
						if(c[tmpI+1+deltaI][tmpJ+1+deltaJ].getHitbox().intersects(hitbox) && (c[tmpI+1+deltaI][tmpJ+1+deltaJ] instanceof Mur || c[tmpI+1+deltaI][tmpJ+1+deltaJ] instanceof Bords || c[tmpI+1+deltaI][tmpJ+1+deltaJ] instanceof Porte)){
							alreadyDead = true;
						}
					}
				}
			}
		}
	}

}
