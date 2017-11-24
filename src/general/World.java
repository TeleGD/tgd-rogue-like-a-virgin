package general;

import java.io.File;

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

import general.Main;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;
import menus.MainMenu;

public class World extends BasicGameState {
	
	public static int ID=2;

	public final static String GAME_NAME="RogueLikeAVirgin";
	
	public final static String GAME_FOLDER_NAME="RogueLike";
	public final static String DIRECTORY_SOUNDS="sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	private static StateBasedGame game;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		game=arg1;
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect(620, 340, 40, 40);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void keyReleased(int key, char c) {
		
	}


	public void keyPressed(int key, char c) {
		
	}

	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}

}
