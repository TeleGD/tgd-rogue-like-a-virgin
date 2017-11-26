package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.World;




public class MainMenu extends Menu{

	public static int ID = -3;	
	
	public MainMenu(){
		super.setTitrePrincipal("TGD MULTI-3-GAME");
		super.setTitreSecondaire("Main Menu");
		super.setItems(World.GAME_NAME,"Scores","Credits", "Quit");
		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position) {
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			World.reset();
			game.enterState(World.ID, new FadeOutTransition(),
					new FadeInTransition());
			//background.stop();
			break;
		case 1:
			//Score board
			game.enterState(ScoreMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			
			
			break;
		case 2:
			System.exit(0);
			break;
		case 3:
			System.exit(0);
			break;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
