package menus;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.Doublet;
import general.Main;
import general.utils.FontUtils;

public class ScoreMenu extends BasicGameState{

	private TrueTypeFont fontTitrePrincipal,fontConfirmText;
	private TrueTypeFont fontTitreSecondaire;
	protected TrueTypeFont fontItem;
	public static int ID=7;
	private ArrayList<Doublet> scores;
	private StateBasedGame game;

	public ScoreMenu(StateBasedGame g){
		super();
		game=g;
		fontConfirmText=FontUtils.loadCustomFont("press-start-2p.ttf",Font.PLAIN,20);
		/*try {
			background=new Image("sprites/main_menu.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}*/
		//titre="SCORES";
		//margeMoins=150;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		fontTitrePrincipal=FontUtils.loadFont("press-start-2p.ttf",Font.BOLD,40,false);
		fontTitreSecondaire=FontUtils.loadFont("Kalinga",Font.BOLD,24,true);
		fontItem=FontUtils.loadFont("Kalinga",Font.BOLD,14,true);
		scores=general.Dao.search();


	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);
		g.drawString("Rogue Like A Virgin",(Main.longueur-fontTitrePrincipal.getWidth("Rogue Like A Virgin"))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString("Rogue Like A Virgin",(Main.longueur-fontTitrePrincipal.getWidth("Rogue Like A Virgin"))/2+4 , 122);

		g.setFont(fontTitreSecondaire);
		g.drawString("Scores", Main.longueur/2-fontTitreSecondaire.getWidth("Scores")/2, 232);

		g.drawRect(Main.longueur/2-300, Main.hauteur/2-130, 600,37);

		g.drawRect(Main.longueur/2-300, Main.hauteur-200, 600,37);

		g.setFont(fontConfirmText);
		g.drawString("PRESS ENTER", Main.longueur/2-fontConfirmText.getWidth("PRESS ENTER")/2, 530);


		g.setFont(fontItem);
		//Affichage des 5 permiers nom avec le meilleur score
		for (int i=0;i<5;i++) {
			g.drawString(scores.get(i).getName(), 500, 300+35*i);
			g.drawString(""+scores.get(i).getScore(), 650, 300+35*i);
		}

	}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_ENTER)
			game.enterState(-3,  new FadeOutTransition(),new FadeInTransition());
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

}
