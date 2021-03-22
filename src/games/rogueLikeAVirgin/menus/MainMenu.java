package games.rogueLikeAVirgin.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends Menu{

	private int ID;

	public MainMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.init(container, game);
		super.setTitrePrincipal("Rogue Like a Virgin");
		super.setTitreSecondaire("Main Menu");
		super.setItems("Play","Scores (à débugguer)","Credits", "Quit");
		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}


	@Override
	public void enter(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void onOptionItemFocusedChanged(int position) {
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			game.enterState(3 /* World */, new FadeOutTransition(),
					new FadeInTransition());
			//background.stop();
			break;
		case 1:
			//Score board
			// game.enterState(4 /* ScoreMenu */, new FadeOutTransition(),
			//		new FadeInTransition());
			break;
		case 2:
			game.enterState(5 /* CreditsMenu */, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 3:
			container.exit();
			break;
		}
	}

}
