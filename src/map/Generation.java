package map;

import java.util.Random;

public class Generation {
	
	private static int difficulte;
	
	static int hauteur;
	static int largeur;
	
	public static int difficulteMax = 5;
	
	public static Salle genereSalle(int niveau, int posx, int posy, int phauteur, int plargeur) {
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
						c=new Mur(i,j);
					} else {
						c = new Piques(i,j,difficulte);
					}
				}
				
				map[i][j]=c;
			}
		}
		return new Salle(posePortes(map), hauteur, largeur);
	}
	
	
	private static Case[][] posePortes(Case[][] map){
		Random r = new Random();
		int diffPorte = r.nextInt(difficulteMax);
		
		int h=(hauteur/2)-1;
		int l=(largeur/2)-1;
		
		//porte haut
		map[0][l] = new Porte(0, l, diffPorte);
		map[0][l+1] = new Porte(0, l+1, diffPorte);
		
		//porte bas
		diffPorte = r.nextInt(difficulteMax);
		map[hauteur-1][l] = new Porte(hauteur-1, l, diffPorte);
		map[hauteur-1][l+1] = new Porte(hauteur-1, l+1, diffPorte);
		
		//porte gauche
		diffPorte = r.nextInt(difficulteMax);
		map[h][0] = new Porte(h, 0, diffPorte);
		map[h+1][0] = new Porte(h+1, 0, diffPorte);
		
		//porte bas
		diffPorte = r.nextInt(difficulteMax);
		map[h][largeur-1] = new Porte(h, largeur-1, diffPorte);
		map[h+1][largeur-1] = new Porte(h+1, largeur-1, diffPorte);
		
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
