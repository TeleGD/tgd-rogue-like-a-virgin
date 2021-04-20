package games.rogueLikeAVirgin;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

public class Credits extends BasicGameState {

	private int ID;
	private Font font;
	private Font font2;
	private List<String> names;

	public Credits(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
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
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(1 /* Choice */, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		int width = container.getWidth();
		int height = container.getHeight();
		context.setColor(Color.white);
		context.setFont(font);
		context.drawString("CREDITS",(width-font.getWidth("CREDITS"))/2, height / 2 - 260);
		context.drawString("Art Director",(width-font.getWidth("Art Director"))/2,  height / 2 - 160);
		context.drawLine((width-font.getWidth("Art Director"))/2, height / 2 - 160 + font.getHeight("Art Director"), (width+font.getWidth("Art Director"))/2, height / 2 - 160 + font.getHeight("Art Director"));
		context.drawString("The Team",(width-font.getWidth("The Team"))/2, height / 2 + 40);
		context.drawLine((width-font.getWidth("The Team"))/2, height / 2 + 40 + font.getHeight("The Team"), (width+font.getWidth("The Team"))/2, height / 2 + 40 + font.getHeight("Art Director"));
		context.setFont(font2);
		context.drawString("G.Teset",(width-font.getWidth("G.Teset"))/2, height / 2 - 130 + font.getHeight("Art Director"));
		for (int i=0;i<3;i++) {
			context.drawString(names.get(i), width / 2 - 340, height / 2 + 70 + font.getHeight("The Team") + 40 * i);
			context.drawString(names.get(5-i), width / 2 + 160, height / 2 + 70 + font.getHeight("The Team") + 40 * i);
		}
	}

}
