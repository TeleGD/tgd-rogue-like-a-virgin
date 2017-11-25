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
			//porte haut
			fermee =3;
		}
		if(posx==largeur-1){
			//bas
			fermee = 4;
		}
		if(posy==0){
			//gauche
			fermee = 2;
		}
		if(posy==hauteur-1){
			//droite
			fermee = 1;
		}
		
		return genereSalle( niveau,  largeur,  hauteur,fermee);
	}
	
	
	//porte fermee : 0 rien | 1 : gauche | 2 : droite | 3 : bas | 4 : haut
	public static Salle genereSalle(int niveau, int plargeur, int phauteur,int porteFermee) {
		hauteur = phauteur;
		largeur = plargeur;
		Case[][] map = new Case[hauteur][largeur];
		Random r = new Random();
		difficulte = r.nextInt(difficulteMax);
		if(niveau != -1)
			difficulte =niveau;
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
		
		//porte gauche
		if(fermee != 1){
			map[0][l] = new Porte(0, l, diffPorte);
			map[0][l+1] = new Porte(0, l+1, diffPorte);
		}

		
		//porte droite
		if(fermee != 2){
			diffPorte = r.nextInt(difficulteMax);
			map[hauteur-1][l] = new Porte(hauteur-1, l, diffPorte);
			map[hauteur-1][l+1] = new Porte(hauteur-1, l+1, diffPorte);
		}

		
		//porte haut
		if(fermee != 4){
			diffPorte = r.nextInt(difficulteMax);
			map[h][0] = new Porte(h, 0, diffPorte);
			map[h+1][0] = new Porte(h+1, 0, diffPorte);
		}

		
		//porte bas
		if(fermee != 3){
			diffPorte = r.nextInt(difficulteMax);
			map[h][largeur-1] = new Porte(h, largeur-1, diffPorte);
			map[h+1][largeur-1] = new Porte(h+1, largeur-1, diffPorte);
		}
		
		
		for(int i =1;i<hauteur-1;i++){
			map[i][l]=new CaseVide(i, l, difficulte);
			map[i][l+1]=new CaseVide(i, l+1, difficulte);
		}
		
		for(int j =1;j<largeur-1;j++){
			map[h][j]=new CaseVide(h, j, difficulte);
			map[h+1][j]=new CaseVide(h+1, j, difficulte);
		}
		
		return map;
	}
	
	
}
