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

import general.Main;
import general.utils.FontUtils;

public class CreditsMenu extends BasicGameState{

	public static int ID=20;
	private TrueTypeFont font,font2;
	private StateBasedGame game;
	private ArrayList<String> names;

	@Override
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		font=FontUtils.loadFont("iCrack.ttf",Font.PLAIN,55,false);
		font2=FontUtils.loadFont("INFECTED.ttf",Font.PLAIN,35,false);
		names=new ArrayList<String>();
		names.add("S.Rimlinger");
		names.add("A.Sochala");
		names.add("P.Wang");
		names.add("A.Canal");
		names.add("N.Balroy");
		names.add("N.Bernardes");
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame game) throws SlickException {
		this.game=game;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("CREDITS",(Main.longueur-font.getWidth("CREDITS"))/2 , 100);
		g.drawString("Art Director",(Main.longueur-font.getWidth("Art Director"))/2 , 200);
		g.drawLine((Main.longueur-font.getWidth("Art Director"))/2, 202+font.getHeight("Art Director"), (Main.longueur+font.getWidth("Art Director"))/2, 202+font.getHeight("Art Director"));
		g.drawString("The Team",(Main.longueur-font.getWidth("The Team"))/2 , 400);
		g.drawLine((Main.longueur-font.getWidth("The Team"))/2, 400+font.getHeight("The Team"), (Main.longueur+font.getWidth("The Team"))/2, 400+font.getHeight("Art Director"));
		g.setFont(font2);
		g.drawString("G.Teset",(Main.longueur-font.getWidth("G.Teset"))/2 , 200+font.getHeight("Art Director")+30);
		for (int i=0;i<3;i++) {
			g.drawString(names.get(i),300 , 400+font.getHeight("The Team")+30+40*i);
			g.drawString(names.get(5-i),800 , 400+font.getHeight("The Team")+30+40*i);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyPressed(int key, char c) {
		if(key==Input.KEY_ENTER) {
			game.enterState(MainMenu.ID, new FadeOutTransition(), new FadeInTransition());
		}
	}


}
