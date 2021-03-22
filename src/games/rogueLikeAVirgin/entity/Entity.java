package games.rogueLikeAVirgin.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import games.rogueLikeAVirgin.World;

public abstract class Entity {

	protected World world;
	protected float x,y;
	protected int width,height,dirX,dirY,hp,atk,def;
	protected double speedX,speedY;
	protected boolean alreadyDead;
	protected Shape hitbox;
	protected Image sprite;

	public Entity(World world) {
		this.world = world;
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public float getDirX(){
		return dirX;
	}

	public float getDirY(){
		return dirY;
	}

	public double getSpeedX(){
		return speedX;
	}

	public double  getSpeedY(){
		return speedY;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public int getHP(){
		return hp;
	}

	public int getAtk(){
		return atk;
	}

	public int getDef(){
		return def;
	}

	public boolean isAlreadyDead(){
		return alreadyDead;
	}

	public Shape getShape(){
		return hitbox;
	}

	public Shape getHitbox(){
		return hitbox;
	}

	public Image getSprite(){
		return sprite;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public void setAlreadyDead(boolean alreadyDead) {
		this.alreadyDead = alreadyDead;
	}

	public void setAtk(int atk){
		this.atk = atk;
	}

	public void setDef(int def){
		this.def = def;
	}

	public void setShape(Shape hitbox){
		this.hitbox = hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;
		hitbox.setLocation(x, y);
		if(x > World.longueur || y > World.hauteur || x < 0 || y < 0){
			alreadyDead = true;
		}
	}

	public abstract void die();

	public abstract void checkForCollision();

	public void update(GameContainer container, StateBasedGame game, int delta) {
		checkForCollision();
		move(delta);
		if(alreadyDead) die();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(sprite,x,y);
	}

}
