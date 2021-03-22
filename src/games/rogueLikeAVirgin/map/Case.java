package games.rogueLikeAVirgin.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import games.rogueLikeAVirgin.entity.Entity;

public abstract class Case {

	protected int niveau;

	protected Shape hitbox;
	protected int x;
	protected int y;
	protected Image sprite;

	protected boolean deplacementPossible;
	protected Entity entite;

	public Entity getEntite() {
		return entite;
	}

	public void setEntite(Entity entite) {
		this.entite = entite;
	}

	public Case(int x,int y) {
		hitbox = new Rectangle(x*36,y*36,36,36);
		deplacementPossible=true;
		this.x=x;
		this.y=y;
	}

	public Case(int x, int y, int difficulte){
		this(x,y);
		niveau = difficulte;
	}

	public boolean getDeplacementPossible(){
		return deplacementPossible;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(sprite,(float) x*36,(float) y*36);
	}

	public void ArriveSur(Entity e){

	}

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	};

	protected String getCouleur(){
		switch(niveau){
		case 0:
			return "Green";
		case 1:
			return "";
		case 2:
			return "Blue";
		case 3:
			return "Yellow";
		case 6 :
			return"Telecom";
		default:
			return "Red";
		}
	}

}
