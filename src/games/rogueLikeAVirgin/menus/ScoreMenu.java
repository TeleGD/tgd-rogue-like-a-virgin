package games.rogueLikeAVirgin.menus;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.rogueLikeAVirgin.World;

public class ScoreMenu extends BasicGameState{

	private int ID;
	private TrueTypeFont fontTitrePrincipal,fontConfirmText;
	private TrueTypeFont fontTitreSecondaire;
	protected TrueTypeFont fontItem;
	private ArrayList<Doublet> scores;
	private StateBasedGame game;

	public ScoreMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame game) {
		this.game = game;
		fontConfirmText=AppLoader.loadFont("/fonts/press-start-2p.ttf",AppFont.PLAIN,20);
		//titre="SCORES";
		//margeMoins=150;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		fontTitrePrincipal=AppLoader.loadFont("/fonts/press-start-2p.ttf",AppFont.BOLD,40);
		fontTitreSecondaire=AppLoader.loadFont("/fonts/vt323.ttf",AppFont.BOLD,24);
		fontItem=AppLoader.loadFont("/fonts/vt323.ttf",AppFont.BOLD,14);
		scores=Dao.search();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);
		g.drawString("Rogue Like a Virgin",(World.longueur-fontTitrePrincipal.getWidth("Rogue Like a Virgin"))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString("Rogue Like a Virgin",(World.longueur-fontTitrePrincipal.getWidth("Rogue Like a Virgin"))/2+4 , 122);

		g.setFont(fontTitreSecondaire);
		g.drawString("Scores", World.longueur/2-fontTitreSecondaire.getWidth("Scores")/2, 232);

		g.drawRect(World.longueur/2-300, World.hauteur/2-130, 600,37);

		g.drawRect(World.longueur/2-300, World.hauteur-200, 600,37);

		g.setFont(fontConfirmText);
		g.drawString("PRESS ENTER", World.longueur/2-fontConfirmText.getWidth("PRESS ENTER")/2, 530);


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
			game.enterState(1 /* MainMenu */,  new FadeOutTransition(),new FadeInTransition());
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
	}

}
