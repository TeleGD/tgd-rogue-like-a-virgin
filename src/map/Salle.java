package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Salle {
	private Case cases[][];
	private int hauteur;
	private int largeur;
	
	public Salle(Case[][] cases,int i,int j) {
		hauteur=i;
		largeur=j;
		this.cases=cases;
		
	}
	
	public void deplacement(Case depart, Case arrivee) {
		if (arrivee.getDeplacementPossible()) {
			arrivee.setEntite(depart.getEntite());
			depart.setEntite(null);
			
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for(int i =0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				cases[i][j].update(container,game,delta);
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for(int i =0;i<hauteur;i++){
			for(int j=0;j<largeur;j++){
				cases[i][j].render(container,game,g);
			}
		}
		
	}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	public int getLigne() {
		return hauteur;
	}

	public void setLigne(int ligne) {
		this.hauteur = ligne;
	}

	public int getColonne() {
		return largeur;
	}

	public void setColonne(int colonne) {
		this.largeur = colonne;
	}
	
	

}
