package entity.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import map.Case;
import map.Mur;

public class Enemy1 extends Enemy{

	private Polygon zoneR,zoneL,zoneT,zoneB;
	private double speed;
	
	public Enemy1(float x, float y) {
		super(x, y);
		hp=1;
		try {
			this.sprite=new Image("images/RogueLike/blobBas.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.width=36;
		this.height=36;
		zoning();
		this.hitbox=new Rectangle (x,y,width,height);
		speed = 0.15;
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
	
	public void move(int delta) {
		
		if(zoneT.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=0;
			speedY=-speed;
		}else if(zoneB.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=0;
			speedY=speed;
		}else if(zoneL.contains(World.player.getX()+World.player.getWidth(), World.player.getY()+World.player.getHeight())) {
			speedX=-speed;
			speedY=0;
		}else {
			speedX=speed;
			speedY=0;
		}
		
		
		/*il faut voir pour les murs et bouger en conséquence
		 *
		 * */
		Case[][] c=World.map.getCases();
		
		if (speedX>0){
			//going to the right
			int a= (int) (x+speedX*delta+width)/36; //right border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)) {
				speedX=0;
				if(World.player.getY()>y) {
					speedY=speed;
				}else {
					speedY=-speed;
				}
			}
			
		}else if (speedX<0) {
			//going to the left
			int a= (int) (x+speedX*delta)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (y/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (y+height)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[a][b] instanceof Mur)||(c[a][b1] instanceof Mur)) {
				speedX=0;
				if(World.player.getY()>y) {
					speedY=speed;
				}else {
					speedY=-speed;
				}
			}
			
		}else if(speedY>0) {
			//going down
			int a= (int) (y+speedY*delta+height)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (x/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (x+width)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)) {
				speedY=0;
				if(World.player.getX()>x) {
					speedX=speed;
				}else {
					speedX=-speed;
				}
			}
			
		}else {
			//going top
			int a= (int) (y+speedY*delta)/36; //left border of the hitbox if we continue the movement (number in the grid)
			int b= (int) (x/36); //top border of the hitbox if we continue the movement (number in the grid)
			int b1= (int) (x+width)/36; //bottom border of the hitbox if we continue the movement (number in the grid)
			if((c[b][a] instanceof Mur)||(c[b1][a] instanceof Mur)) {
				speedY=0;
				if(World.player.getX()>x) {
					speedX=speed;
				}else {
					speedX=-speed;
				}
			}
		}
		
		x+=speedX*delta;
		y+=speedY*delta;
		hitbox.setX(x);
		hitbox.setY(y);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		zoning();
		//move(delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.draw(hitbox);
	}

}
