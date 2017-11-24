package map;

import java.util.Random;

public class Generation {
	
	private static int difficulte;
	
	static int hauteur;
	static int largeur;
	
	 static int difficulteMax = 5;
	 
	
	public static Salle genereNewSalle(int niveau, int posx, int posy){
		int fermee = 0;
		
		if(posx==0){
			fermee = 4;
		}
		if(posx==largeur-1){
			fermee = 2;
		}
		if(posy==0){
			fermee = 1;
		}
		if(posy==largeur-1){
			fermee = 3;
		}
		
		return genereSalle( niveau,  hauteur,  largeur,fermee);
	}
	
	
	//porte fermee : 0 rien | 1 : haut | 2 : droite | 3 : bas | 4 : gauche
	public static Salle genereSalle(int niveau, int phauteur, int plargeur,int porteFermee) {
		hauteur = phauteur;
		largeur = plargeur;
		Case[][] map = new Case[hauteur][largeur];
		Random r = new Random();
		difficulte = r.nextInt(difficulteMax);
		for (int i=0;i<hauteur;i++) {
			for (int j=0;j<largeur;j++) {
				Case c;
				
				if(i==0 || j == 0 || i == hauteur-1 || j == largeur-1){
					c=new Bords(i,j);
				}
				else{
					int a = r.nextInt(100);
					a+=difficulte*difficulte/2;
					if (a<95) {
						c=new CaseVide(i,j,difficulte);
					}
					else if (a<100) {
						c=new Mur(i,j,difficulte);
					} else {
						c = new Piques(i,j,difficulte);
					}
				}
				
				map[i][j]=c;
			}
		}
		return new Salle(posePortes(map,porteFermee), hauteur, largeur);
	}
	
	
	private static Case[][] posePortes(Case[][] map, int fermee){
		Random r = new Random();
		int diffPorte = r.nextInt(difficulteMax);
		
		int h=(hauteur/2)-1;
		int l=(largeur/2)-1;
		
		//porte haut
		if(fermee != 1){
			map[0][l] = new Porte(0, l, diffPorte);
			map[0][l+1] = new Porte(0, l+1, diffPorte);
		}

		
		//porte bas
		if(fermee != 3){
			diffPorte = r.nextInt(difficulteMax);
			map[hauteur-1][l] = new Porte(hauteur-1, l, diffPorte);
			map[hauteur-1][l+1] = new Porte(hauteur-1, l+1, diffPorte);
		}

		
		//porte gauche
		if(fermee != 2){
			diffPorte = r.nextInt(difficulteMax);
			map[h][0] = new Porte(h, 0, diffPorte);
			map[h+1][0] = new Porte(h+1, 0, diffPorte);
		}

		
		//porte bas
		if(fermee != 4){
			diffPorte = r.nextInt(difficulteMax);
			map[h][largeur-1] = new Porte(h, largeur-1, diffPorte);
			map[h+1][largeur-1] = new Porte(h+1, largeur-1, diffPorte);
		}

		
		for(int i =1;i<hauteur-1;i++){
			map[i][h]=new CaseVide(i, h, difficulte);
			map[i][h+1]=new CaseVide(i, h+1, difficulte);
		}
		
		for(int j =1;j<largeur-1;j++){
			map[l][j]=new CaseVide(l, j, difficulte);
			map[l+1][j]=new CaseVide(l+1, j, difficulte);
		}
		
		return map;
	}
	
	
}
