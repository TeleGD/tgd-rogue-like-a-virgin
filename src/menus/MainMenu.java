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
	private Music background;
	
	public MainMenu(){
		super.setTitrePrincipal("TGD MULTI-3-GAME");
		super.setTitreSecondaire("Menu Principal");
		super.setItems(World.GAME_NAME,"Multitask", "Quitter");
		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		try {	
			background=new Music("musics/menu.ogg");
			background.loop();
			System.out.println("oui "+background.getPosition());
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
			background.stop();
			break;
		case 1:
			//Pareil pour le deuxieme item, etc
			break;
		case 2:
			System.out.println("exit");
			System.exit(0);
			break;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
