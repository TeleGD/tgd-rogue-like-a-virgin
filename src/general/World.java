package general;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


import entity.Enemy;
import entity.Player;
import entity.Projectile;
import entity.Item;
import general.Main;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;
import map.Generation;
import map.Salle;
import menus.MainMenu;

public class World extends BasicGameState {
	
	public static int ID=2;

	public final static String GAME_NAME="RogueLikeAVirgin";
	
	public final static String GAME_FOLDER_NAME="RogueLike";
	public final static String DIRECTORY_SOUNDS="sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	private static StateBasedGame game;
	
	public static ArrayList<Enemy> enemies,enemiesTmp;
	public static ArrayList<Projectile> projectiles,projectilesTmp;
	public static Player player;
	public static Item item;
	public static Salle map;
	
	public Image coeur;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		game=arg1;
		
		//Il faudra voir s'il faut bouger ces inits dans enter(...) si ca prend trop de temps
		enemies = new ArrayList<Enemy>();
		enemiesTmp = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		projectilesTmp = new ArrayList<Projectile>();
		player = new Player();
		item = new Item();
		map =  Generation.genereSalle(-1, 20,20 ,0);
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		coeur = new Image(World.DIRECTORY_IMAGES+"vie.png");
	}
	
	public static void changeMap(Salle s){
		map = s;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect(620, 340, 40, 40);

		map.render(container, game, g);
		
		player.render(container, game, g);
		if (item != null)
			item.render(container, game, g);
		for(Enemy e : enemies){
			e.render(container, game, g);
		}
		for(Projectile p : projectiles){
			p.render(container, game, g);
		}
		for (int i = 0; i < player.getHp(); i++){
			g.drawImage(coeur, 756+i*50, 36);
		}
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		player.update(container, game, delta);
		if (item != null)
			item.update(container, game, delta);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update(container, game, delta);
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update(container, game, delta);
		}
		
		map.update(container, game, delta);
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key,c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key,c);
	}

	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}

	public static void newItem() throws SlickException {
		item = new Item();
	}

}
