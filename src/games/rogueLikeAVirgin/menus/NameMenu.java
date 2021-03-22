package games.rogueLikeAVirgin.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.rogueLikeAVirgin.World;

public class NameMenu extends BasicGameState{

	private int ID;
	private int compt;
	private TrueTypeFont fontTitrePrincipal;

	public NameMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame game) {
		fontTitrePrincipal=AppLoader.loadFont("/fonts/rogueLikeAVirgin/iCrack.ttf",AppFont.PLAIN,55);
		compt =0;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString("Rogue Like a Virgin",(World.longueur-fontTitrePrincipal.getWidth("Rogue Like a Virgin"))/2 , (World.hauteur-fontTitrePrincipal.getHeight("Rogue Like a Virgin"))/2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) {
		compt++;
		if(compt>15) {
			game.enterState(1 /* MainMenu */,new FadeOutTransition(),new FadeInTransition());
		}
	}

}
