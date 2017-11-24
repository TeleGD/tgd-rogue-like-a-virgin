package map;

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

}
