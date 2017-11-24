package map;

import java.util.Random;

public class Generation {
	
	public static Salle genereSalle(int niveau, int posx, int posy, int hauteur, int largeur) {
		Case[][] map = new Case[hauteur][largeur];
		Random r = new Random();
		for (int i=0;i<hauteur;i++) {
			for (int j=0;j<largeur;j++) {
				int a = r.nextInt(100);
				Case c;
				if (a<90) {
					c=new CaseVide(j,i);
				} else {
					c = new Mur(j,i);
				}
				map[i][j]=c;
			}
		}
		return new Salle(map, hauteur, largeur);
	}

}
