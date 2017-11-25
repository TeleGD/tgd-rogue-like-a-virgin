package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;
import entity.Player;
import general.World;

public class Porte extends Case {

	public Porte(int x, int y,int difficulte) {
		super(x, y,difficulte);
		try {
			sprite = new Image(World.DIRECTORY_IMAGES+"door"+getCouleur()+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("porte " + getCouleur() + " x : " + x + "   y " + y);
	}
	
	@Override
	public void ArriveSur(Entity e) {
		if(e instanceof Player){ //genere new map
			
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//System.out.println("player x : " +  Math.round(World.player.getX()/36) + " y : " + Math.round(World.player.getY()/36));
		//System.out.println("salle "+getCouleur()+" x : " +  x + " y : " +y);

		
		if(Math.round(World.player.getY()/36) == this.y && Math.round(World.player.getX()/36) == this.x){
			World.changeMap(Generation.genereNewSalle(niveau, y, x));

		}
	}
	
}
