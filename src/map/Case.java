package map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entity.Enemy;
import entity.Entity;
import entity.Projectile;

public class Case {
	
	protected boolean deplacementPossible;
	protected Entity entite;
	
	public Entity getEntite() {
		return entite;
	}

	public void setEntite(Entity entite) {
		this.entite = entite;
	}

	public Case() {
		deplacementPossible=true;
	}
	
	public boolean getDeplacementPossible(){
		return deplacementPossible;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}

}
