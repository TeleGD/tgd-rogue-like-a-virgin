package map;

import java.util.Random;

public class Generation {
	
	public static Salle genereSalle(int niveau, int posx, int posy, int hauteur, int largeur) {
		Case[][] map = new Case[hauteur][largeur];
		Random r = new Random();
		int difficulte = r.nextInt(5);
		for (int i=0;i<hauteur;i++) {
			for (int j=0;j<largeur;j++) {
				Case c;
				
				if(i==10 || i == 9|| j== 10 || j ==9){
					if((i== 0 || i == largeur-1) || (j== 0 || j == hauteur-1))
						c=new Porte(i,j,difficulte);
					else
						c= new CaseVide(i, j,difficulte);
				}
				else if(i==0 || j == 0 || i == largeur-1 || j == hauteur-1){
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
		return new Salle(map, hauteur, largeur);
	}
	
}
