package map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entity.Enemy;
import entity.Projectile;

public class Salle {
	private Case cases[][];
	private int ligne;
	private int colonne;
	
	public Salle(Case[][] cases,int i,int j) {
		ligne=i;
		colonne=j;
		this.cases=cases;
		
	}
	
	public void deplacement(Case depart, Case arrivee) {
		if (arrivee.getDeplacementPossible()) {
			arrivee.setEntite(depart.getEntite());
			depart.setEntite(null);
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for(int i =0;i<colonne;i++){
			for(int j=0;j<ligne;j++){
				cases[i][j].update(container,game,delta);
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for(int i =0;i<colonne;i++){
			for(int j=0;j<ligne;j++){
				cases[i][j].render(container,game,g);
			}
		}
		
	}

}
