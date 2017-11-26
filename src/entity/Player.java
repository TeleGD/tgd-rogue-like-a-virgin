package entity;

import map.Bords;
import map.Case;
import map.Mur;
import map.Piques;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import entity.enemies.Enemy;

public class Player extends Entity {

	private boolean up;
	private boolean down;
	private boolean updown;
	private boolean right;
	private boolean left;
	private boolean rightLeft;
	private double speed;
	private int direction;
	private Image spriteU;
	private Image spriteR;
	private Image spriteD;
	private Image spriteL;
	private boolean nord;
	private boolean est;
	private boolean sud;
	private boolean ouest;
	private boolean nordsud;
	private boolean estouest;
	private int periodeTir;
	private int attenteTir;
	private boolean tir;
	private double projSpeed;
	private double projSpeedX;
	private double projSpeedY;
	private boolean deplacementPossibleHaut = true;
	private boolean deplacementPossibleBas = true;
	private boolean deplacementPossibleDroite = true;
	private boolean deplacementPossibleGauche = true;
	private boolean collision = false;
	private Case c[][];
	private int aTouchePique;
	private int coin;
	private float tryX;
	private float tryY;
	private double tmpSpeedX;
	private double tmpSpeedY;
	private boolean invincible;
	private int invincibleTimer;
	private int invincibleTimerMax;


	public int getInvincibleTimerMax() {
		return invincibleTimerMax;
	}

	public void setInvincibleTimerMax(int invincibleTimerMax) {
		this.invincibleTimerMax = invincibleTimerMax;
	}

	public Player() throws SlickException{
		World.player = this;
		x=342;
		y=342;
		tryX = x;
		tryY = y;
		spriteU = new Image(World.DIRECTORY_IMAGES+"playerHaut.png");
		spriteR = new Image(World.DIRECTORY_IMAGES+"playerDroite.png");
		spriteD = new Image(World.DIRECTORY_IMAGES+"playerBas.png");
		spriteL = new Image(World.DIRECTORY_IMAGES+"playerGauche.png");
		width=36;
		height=36;
		hitbox = new Rectangle(x+4,y+4,width-8,height-8);
		speed=0.2;
		direction=2;
		sprite=spriteD;
		periodeTir=50;
		attenteTir=0;
		tir=false;
		projSpeed=0.4;
		hp = 5;
		c = World.map.getCases();
		aTouchePique=0;
		coin = 0;
		atk = 1;
		invincible = false;
		invincibleTimerMax = 50;
		invincibleTimer = invincibleTimerMax;
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}
	
	public void setMap(Case[][] c){
		this.c = c;
	}

	@Override
	public void checkForCollision() {
//Bonjour, ce code est hideux. Desole.
		int tmpI,tmpJ;
		boolean accumulateur = false;
		tmpI = (int) (x+width/2)/width;
		tmpJ = (int) (y+height/2)/height;

		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				collision = false;
				if(tmpI+i < c.length && tmpI+i >= 0 && tmpJ+j < c[0].length && tmpJ+j >= 0) collision = c[tmpI+i][tmpJ+j].getHitbox().intersects(hitbox) && (c[tmpI+i][tmpJ+j] instanceof Mur || c[tmpI+i][tmpJ+j] instanceof Bords);
				accumulateur = accumulateur || collision;
			}
		}
		if(tmpI-1 >= 0) deplacementPossibleGauche = !(c[tmpI-1][tmpJ]instanceof Mur||c[tmpI-1][tmpJ]instanceof Bords);
		if(tmpI+1 < c.length) deplacementPossibleDroite = !(c[tmpI+1][tmpJ]instanceof Mur||c[tmpI+1][tmpJ]instanceof Bords);
		if(tmpJ-1 >= 0) deplacementPossibleHaut = !(c[tmpI][tmpJ-1]instanceof Mur||c[tmpI][tmpJ-1]instanceof Bords);
		if(tmpJ+1 < c[tmpI].length) deplacementPossibleBas = !(c[tmpI][tmpJ+1]instanceof Mur||c[tmpI][tmpJ+1]instanceof Bords);
		
		if(!accumulateur){
			deplacementPossibleBas = true;
			deplacementPossibleDroite = true;
			deplacementPossibleGauche = true;
			deplacementPossibleHaut = true;
		}else{
			boolean u,d,l,r,ul,ur,dl,dr;
			r = (!up && !down && right && !rightLeft && (tmpI+1 < c.length)) && (c[tmpI+1][tmpJ] instanceof Mur || c[tmpI+1][tmpJ] instanceof Bords); 
			l = (!up && !down && (left || rightLeft) && tmpI-1 >= 0) && (c[tmpI-1][tmpJ] instanceof Mur || c[tmpI-1][tmpJ] instanceof Bords);
			u = (!left && !right && up && !updown && (tmpJ-1 >= 0 && (c[tmpI][tmpJ-1] instanceof Mur || c[tmpI][tmpJ-1] instanceof Bords)) );
			d = (!left && !right && (down || updown) && (tmpJ+1 < c[tmpI].length && (c[tmpI][tmpJ+1] instanceof Mur ||c[tmpI][tmpJ+1] instanceof Bords)));
			ul = (up && (left || rightLeft) && !updown && tmpI-1 >= 0) && (tmpJ-1 >= 0 && (c[tmpI-1][tmpJ-1] instanceof Mur || c[tmpI-1][tmpJ-1] instanceof Bords));
			ur = (up && right && !rightLeft && !updown && (tmpI+1 < c.length)) && (tmpJ-1 >= 0 && (c[tmpI+1][tmpJ-1] instanceof Mur || c[tmpI+1][tmpJ-1] instanceof Bords));
			dl = ((down || updown) && (left || rightLeft)&& tmpI-1 >= 0 ) && (tmpJ+1 < c[tmpI].length && (c[tmpI-1][tmpJ+1] instanceof Mur || c[tmpI-1][tmpJ+1] instanceof Bords));
			dr = ((down || updown) && right && !rightLeft && (tmpI+1 < c.length) ) && (tmpJ+1 < c[tmpI].length && (c[tmpI+1][tmpJ+1] instanceof Mur || c[tmpI+1][tmpJ+1] instanceof Bords));
			
			if(r){
				x = tryX;
				speedX = tmpSpeedX;
			}
			
			if(l){
				x = tryX;
				speedY = tmpSpeedY;
			}

			if(u){
				y = tryY;
				speedY = tmpSpeedY;
			}

			if(d){
				y = tryY;
				speedY = tmpSpeedY;
			}
			
			if(ul){
				y = tryY;
				x = tryX;
				speedX = tmpSpeedX;
				speedY = tmpSpeedY;
			}
			
			if(ur){
				y = tryY;
				x = tryX;
				speedX = tmpSpeedX;
				speedY = tmpSpeedY;
			}
			
			if(dl){
				y = tryY;
				x = tryX;
				speedX = tmpSpeedX;
				speedY = tmpSpeedY;
			}
			
			if(dr){
				y = tryY;
				x = tryX;
				speedX = tmpSpeedX;
				speedY = tmpSpeedY;
			}
			
		}//*/
		
		for(Enemy e : World.enemies){
			if(hitbox.intersects(e.getShape())){
				if(!invincible) {
					this.setHP(hp-Math.max(e.getAtk()-def, 0));
					invincibleTimer  = invincibleTimerMax;
					invincible = true;
				}
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
		if(World.item != null){
			for (int i = 0; i < World.item.size(); i++ ){
				if(hitbox.intersects(World.item.get(i).getShape())){
					switch (World.item.get(i).type) {
					case "SpeedUp":  
						speed *= 1.1;
						break;
					case "SpeedDown":  
						speed /= 1.1;
						break;
					case "HpUp":
						if(hp <= 19)
							hp++;
						break;
					case "FireRateUp":
						if(periodeTir >= 10)
							periodeTir -= 5;
						break;
					case "FireRateDown":
						periodeTir += 5;
						break;
					case "Coin":
						setCoin(getCoin()+10);
						break;
					}
					World.item.remove(i);
					return;
				}
			}
		}
	}

	public boolean isInvincible(){
		return invincible;
	}
	
	public void setInvincible(boolean invincible){
		this.invincible = invincible;
	}
	
	public int getInvincibleTimer(){
		return invincibleTimer;
	}
	
	public void setInvincibleTimer(int timer){
		this.invincibleTimer = timer;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPeriode(){
		return periodeTir;
	}

	public double getProj(){
		return projSpeed;
	}

	public void keyPressed(int key, char c) {
		switch (key){

		case Input.KEY_UP:
			up = true;
			updown=false;
			break;

		case Input.KEY_DOWN:
			down=true;
			updown=true;
			break;

		case Input.KEY_LEFT:
			left=true;
			rightLeft=true;
			break;
		case Input.KEY_RIGHT:
			right=true;
			rightLeft=false;
			break;


		case Input.KEY_Z:
			nord = true;
			nordsud=false;
			break;

		case Input.KEY_S:
			sud=true;
			nordsud=true;
			break;

		case Input.KEY_Q:
			ouest=true;
			estouest=false;
			break;
		case Input.KEY_D:
			est=true;
			estouest=true;
			break;
		case Input.KEY_ESCAPE:
			System.exit(0);
			break;

		}

	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			up=false;
			break;
		case Input.KEY_RIGHT:
			right=false;
			break;
		case Input.KEY_LEFT:
			left=false;
			break;
		case Input.KEY_DOWN:
			down=false;
			break;

		case Input.KEY_Z:
			nord=false;
			break;
		case Input.KEY_D:
			est=false;
			break;
		case Input.KEY_Q:
			ouest=false;
			break;
		case Input.KEY_S:
			sud=false;
			break;
		}
	}

	public void move(int dt) {
		speedX = 0;
		speedY = 0;
		if(((up && !down) || (up && down && !updown)) && deplacementPossibleHaut){
			speedY=-speed;
		}
		if(((down && !up) || (up && down && updown)) && deplacementPossibleBas) {
			speedY=speed;
		}
		if(((left && !right)|| (left && right && rightLeft)) && deplacementPossibleGauche) {
			speedX = -speed;
		}
		if(((!left && right)|| (left && right && !rightLeft)) && deplacementPossibleDroite) {
			speedX = speed;
		}
		if (speedX!=0 && speedY!=0) {
			speedX/=Math.sqrt(2);
			speedY/=Math.sqrt(2);
		}

		x+=dt*speedX;
		y+=dt*speedY;
		hitbox.setX(x+4);
		hitbox.setY(y+4);
	}

	public void setDir() {
		direction=2;
		tir=false;
		sprite=spriteD;
		projSpeedX=0;
		projSpeedY=0;

		if((nord && !sud) || (nord && sud && !nordsud)) {
			direction=0;
			sprite=spriteU;
			projSpeedY=-projSpeed;
			tir=true;
		}
		if((sud && !nord) || (nord && sud && nordsud)) {
			direction=2;
			sprite=spriteD;
			projSpeedY=projSpeed;
			tir=true;
		}
		if((ouest && !est)|| (ouest && est && !estouest)) {
			direction=3;
			sprite=spriteL;
			projSpeedX=-projSpeed;
			tir=true;
		}
		if((!ouest && est)|| (ouest && est && estouest)) {
			direction=1;
			sprite=spriteR;
			projSpeedX=projSpeed;
			tir=true;
		}

		if (tir && attenteTir==0) {
			new Projectile(x+width/2-8,y+height/2-8,true,projSpeedX,projSpeedY,this.atk);
			attenteTir=periodeTir;
		}
		if (attenteTir>0) {
			attenteTir--;
		}

	}

	public void touchePiques() {
		if (aTouchePique==0) {
			for (int i=0;i<20;i++) {
				for (int j=0;j<20;j++) {
					if (hitbox.intersects(c[i][j].getHitbox()) && (c[i][j] instanceof Piques)) {
						aTouchePique=80;
						hp--;
					}
				}
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		tryX = x;
		tryY = y;
		tmpSpeedX = speedX;
		tmpSpeedY = speedY;
		move(delta);
		if(invincible) invincibleTimer -= delta;
		if(invincibleTimer <= 0) invincible = false;
		checkForCollision();
		hitbox.setX(x+4);
		hitbox.setY(y+4);
		touchePiques();
		if (aTouchePique>0) aTouchePique--;
		if(alreadyDead) die();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		setDir();
		g.drawImage(sprite,(float) x,(float) y);
		g.draw(hitbox);
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void setPeriod(int i) {
		this.periodeTir = i;
		
	}
}
