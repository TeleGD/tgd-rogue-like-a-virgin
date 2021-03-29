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

public class CreditsMenu extends BasicGameState{

	private int ID;
	private TrueTypeFont font,font2;
	private StateBasedGame game;
	private ArrayList<String> names;

	public CreditsMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame game) {
		font=AppLoader.loadFont("/fonts/rogueLikeAVirgin/iCrack.ttf",AppFont.PLAIN,55);
		font2=AppLoader.loadFont("/fonts/rogueLikeAVirgin/INFECTED.ttf",AppFont.PLAIN,35);
		names=new ArrayList<String>();
		names.add("S.Rimlinger");
		names.add("A.Sochala");
		names.add("P.Wang");
		names.add("A.Canal");
		names.add("N.Balroy");
		names.add("N.Bernardes");
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame game) {
		this.game=game;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("CREDITS",(World.longueur-font.getWidth("CREDITS"))/2 , 100);
		g.drawString("Art Director",(World.longueur-font.getWidth("Art Director"))/2 , 200);
		g.drawLine((World.longueur-font.getWidth("Art Director"))/2, 202+font.getHeight("Art Director"), (World.longueur+font.getWidth("Art Director"))/2, 202+font.getHeight("Art Director"));
		g.drawString("The Team",(World.longueur-font.getWidth("The Team"))/2 , 400);
		g.drawLine((World.longueur-font.getWidth("The Team"))/2, 400+font.getHeight("The Team"), (World.longueur+font.getWidth("The Team"))/2, 400+font.getHeight("Art Director"));
		g.setFont(font2);
		g.drawString("G.Teset",(World.longueur-font.getWidth("G.Teset"))/2 , 200+font.getHeight("Art Director")+30);
		for (int i=0;i<3;i++) {
			g.drawString(names.get(i),300 , 400+font.getHeight("The Team")+30+40*i);
			g.drawString(names.get(5-i),800 , 400+font.getHeight("The Team")+30+40*i);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) {
	}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_ENTER) {
			game.enterState(1 /* Choice */, new FadeOutTransition(), new FadeInTransition());
		}
	}


}
