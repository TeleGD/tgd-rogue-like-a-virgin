package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import map.Bords;
import map.Case;
import map.Mur;

public class Enemy extends Entity {

	private Polygon zoneR,zoneL,zoneT,zoneB;
	
	public Enemy(){
		World.enemies.add(this);
		try {
			this.sprite=new Image("images/RogueLike/blobBas.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		zoneL=new Polygon();
		this.x=100;
		this.y=100;
		this.width=36;
		this.height=36;
		zoning();
		this.hitbox=new Rectangle (x,y,width,height);
	}
	
	@Override
	public void die() {
		World.enemies.remove(this);
	}

	@Override
	public void checkForCollision() {
		if(hitbox.intersects(World.player.getShape())){
			this.setHP(0);
			System.out.println("col player");
			//this.setHP(hp-Math.max(World.player.getAtk()-def, 0));
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
	
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		zoning();
		//move(delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		/*g.draw(zoneL);
		g.draw(zoneB);
		g.draw(zoneR);
		g.draw(zoneT);*/
		g.draw(hitbox);
	}
	
	public void move(int delta) {
		if(zoneT.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=0;
			speedY=-0.2;
		}else if(zoneB.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=0;
			speedY=0.2;
		}else if(zoneL.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=-0.2;
			speedY=0;
		}else {
			speedX=0.2;
			speedY=0;
		}
		x+=speedX*delta;
		y+=speedY*delta;
		hitbox.setX(x);
		hitbox.setY(y);
		
		/*il faut voir pour les murs et bouger en conséquence
		 * 
		Case[][] c=World.map.getCases();
		
		if (speedX>0){
			//going to the right
			int a= (int) (x+speedX*delta+width)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b] instanceof Bords)) {
				speedX=0;
				if(Math.random()>0.5) {
					speedY=0.2;
				}
			}
			
		}else if (speedX<0) {
			//going to the left
			
		}else if(speedY>0) {
			//going down
			
		}else {
			//going top
			
		}
		
		*/
	}
	
	
	public void zoning() {
		// creating the zones used to know the direction to go
		zoneL=new Polygon();
		zoneL.addPoint(x+width/2, y+height/2);
		zoneL.addPoint(x+width/2-1000, y+height/2-1000);
		zoneL.addPoint(x+width/2-1000, y+height/2+1000);
		zoneR=new Polygon();
		zoneR.addPoint(x+width/2, y+height/2);
		zoneR.addPoint(x+width/2+1000, y+height/2-1000);
		zoneR.addPoint(x+width/2+1000, y+height/2+1000);
		zoneT=new Polygon();
		zoneT.addPoint(x+width/2, y+height/2);
		zoneT.addPoint(x+width/2-1000, y+height/2-1000);
		zoneT.addPoint(x+width/2+1000, y+height/2-1000);
		zoneB=new Polygon();
		zoneB.addPoint(x+width/2, y+height/2);
		zoneB.addPoint(x+width/2-1000, y+height/2+1000);
		zoneB.addPoint(x+width/2+1000, y+height/2+1000);
		
	}
	/*appartition-----
	 * déplacement	
	 * mort
	 * tuage de player
	 * img
	 * 
	 */
	

}
