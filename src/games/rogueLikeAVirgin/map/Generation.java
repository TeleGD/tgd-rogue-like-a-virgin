package games.rogueLikeAVirgin.map;

import java.util.Random;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.entity.enemies.*;

public class Generation {

	private World world;
	public int difficulte;

	int hauteur;
	int largeur;

	int difficulteMax = 5;

	public Generation(World world) {
		this.world = world;
	}


	public Salle genereNewSalle(int niveau, int posx, int posy) {
		@SuppressWarnings("unused")
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

		this.world.enemies.clear();
		this.world.enemiesTmp.clear();
		this.world.projectiles.clear();
		this.world.projectilesTmp.clear();
		this.world.item.clear();

		return genereSalle( niveau,  largeur,  hauteur,0);
	}


	//porte fermee : 0 rien | 1 : gauche | 2 : droite | 3 : bas | 4 : haut
	public Salle genereSalle(int niveau, int plargeur, int phauteur,int porteFermee) {
		if(niveau==6) {
			if(!this.world.isSaxGuy()) {
				this.world.changeMusic();
			}
		}else {
			if(this.world.isSaxGuy()) {
				this.world.changeMusic();
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


	private Case[][] posePortes(Case[][] map, int fermee){
		Random r = new Random();
		int diffPorte = r.nextInt(difficulteMax);

		int h=(hauteur/2)-1;
		int l=(largeur/2)-1;

		//porte gauche
		if(fermee != 1){
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[0][l] = new Porte(this.world, 0, l, diffPorte);
			map[0][l+1] = new Porte(this.world, 0, l+1, diffPorte);
		}


		//porte droite
		if(fermee != 2){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[hauteur-1][l] = new Porte(this.world, hauteur-1, l, diffPorte);
			map[hauteur-1][l+1] = new Porte(this.world, hauteur-1, l+1, diffPorte);
		}


		//porte haut
		if(fermee != 4){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[h][0] = new Porte(this.world, h, 0, diffPorte);
			map[h+1][0] = new Porte(this.world, h+1, 0, diffPorte);
		}


		//porte bas
		if(fermee != 3){
			diffPorte = r.nextInt(difficulteMax);
			if(diffPorte == r.nextInt(15))
				diffPorte = 6;
			map[h][largeur-1] = new Porte(this.world, h, largeur-1, diffPorte);
			map[h+1][largeur-1] = new Porte(this.world, h+1, largeur-1, diffPorte);
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


	public void genereEnemies(int type,Case[][] c) {
		/*type est la difficulte de la salle
		 * 1 a 5: difficulte croissante
		 * 6: Boss
		 */
		if(type<6) {
			//generation des salles normales
			for (int i=0;i<type*2;i++) {

				float x=(float) (50+Math.random()*(720-72-50));
				float y=(float) (50+Math.random()*(720-72-50));
				float x2=(float) (50+Math.random()*(720-72-50));
				float y2=(float) (50+Math.random()*(720-72-50));
				float x3=(float) (50+Math.random()*(720-72-50));
				float y3=(float) (50+Math.random()*(720-72-50));
				while (c[(int)x/36][(int)y/36] instanceof Mur) {
					x=(float) (50+Math.random()*(720-36-50));
					y=(float) (50+Math.random()*(720-36-50));
				}
				while (c[(int)x2/36][(int)y2/36] instanceof Mur) {
					x2=(float) (50+Math.random()*(720-36-50));
					y2=(float) (50+Math.random()*(720-36-50));
				}
				/*
				 * proba de faire apparaitre un faible monstre: 1/type mais a voir apres
				 */
				if(Math.random()<(float)1/type) {

					new Enemy1(this.world, x,y);
					new Ghost1(this.world, x3,y3);
					new Skull1(this.world, x2,y2);
				}else {
					new Enemy2(this.world, x,y);
					new Ghost2(this.world, x3,y3);
					new Skull2(this.world, x2,y2);
				}
			}

		}else {
			float x,y;
			for (int i=0;i<6;i++) {

				x=(float) (50+Math.random()*(720-36-50));
				y=(float) (50+Math.random()*(720-36-50));
				while (c[(int)x/36][(int)y/36] instanceof Mur) {
					x=(float) (50+Math.random()*(720-36-50));
					y=(float) (50+Math.random()*(720-36-50));
				}
				new Ghost2(this.world, x,y);
			}

			new Boss(this.world, 400,400);
		}


	}

}
