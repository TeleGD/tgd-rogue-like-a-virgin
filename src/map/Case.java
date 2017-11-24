package map;

import entity.Entity;

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

}
