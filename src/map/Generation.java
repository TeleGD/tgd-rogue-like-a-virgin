package map;

import java.util.Random;

import org.newdawn.slick.SlickException;

import entity.Item;
import entity.enemies.*;
import general.World;

public class Generation {

	public static int difficulte;

	static int hauteur;
	static int largeur;

	static int difficulteMax = 5;


	public static Salle genereNewSalle(int niveau, int posx, int posy) throws SlickException{
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

		World.enemies.clear();
		World.enemiesTmp.clear();
		World.projectiles.clear();
		World.projectilesTmp.clear();
		World.item.clear();

		return genereSalle( niveau,  largeur,  hauteur,0);
	}


	//porte fermee : 0 rien | 1 : gauche | 2 : droite | 3 : bas | 4 : haut
	public static Salle genereSalle(int niveau, int plargeur, int phauteur,int porteFermee) throws SlickException {
		if(niveau==6) {
			if(!World.isSaxGuy()) {
				World.changeMusic();
			}
		}else {
			if(World.isSaxGuy()) {
				World.changeMusic();
			}
		}
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
				else if(difficulte == 6){
					//telecom
					
					
					
					c=new CaseVide(i,j,difficulte);
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
		genereEnemies(difficulte,map);
		return new Salle(posePortes(map,porteFermee), hauteur, largeur);
	}


	private static Case[][] posePortes(Case[][] map, int fermee){
		Random r = new Random();
		int diffPorte = r.nextInt(difficulteMax);

		int h=(hauteur/2)-1;
		int l=(largeur/2)-1;

		//porte gauche
		if(fermee != 1){
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[0][l] = new Porte(0, l, diffPorte);
			map[0][l+1] = new Porte(0, l+1, diffPorte);
		}


		//porte droite
		if(fermee != 2){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[hauteur-1][l] = new Porte(hauteur-1, l, diffPorte);
			map[hauteur-1][l+1] = new Porte(hauteur-1, l+1, diffPorte);
		}


		//porte haut
		if(fermee != 4){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[h][0] = new Porte(h, 0, diffPorte);
			map[h+1][0] = new Porte(h+1, 0, diffPorte);
		}


		//porte bas
		if(fermee != 3){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
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


	public static void genereEnemies(int type,Case[][] c) {
		/*type est la difficulte de la salle
		 * 1 a 5: difficulte croissante
		 * 6: Boss
		 */
		if(type<6) {
			//generation des salles normales
			for (int i=0;i<type*2;i++) {

				float x=(float) ((float) 50+Math.random()*(720-36-50));
				float y=(float) ((float) 50+Math.random()*(720-36-50));
				while (c[(int)x/36][(int)y/36] instanceof Mur) {
					x=(float) ((float) 50+Math.random()*(720-36-50));
					y=(float) ((float) 50+Math.random()*(720-36-50));
				}
				/*
				 * proba de faire apparaitre un faible monstre: 1/type mais a voir apres
				 */
				if(Math.random()<(float)1/type) {

					new Enemy1(x,y);
					new Ghost1(720-x,720-y);
				}else {
					new Enemy2(x,y);
					new Ghost2(720-x,720-y);
				}
			}

		}else {
			float x,y;
			for (int i=0;i<6;i++) {

				x=(float) ((float) 50+Math.random()*(720-36-50));
				y=(float) ((float) 50+Math.random()*(720-36-50));
				while (c[(int)x/36][(int)y/36] instanceof Mur) {
					x=(float) ((float) 50+Math.random()*(720-36-50));
					y=(float) ((float) 50+Math.random()*(720-36-50));
				}
				new Ghost2(x,y);
			}
			
			new Boss(400,400);
		}


	}

}
