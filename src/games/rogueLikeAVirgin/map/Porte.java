package games.rogueLikeAVirgin.map;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.rogueLikeAVirgin.World;
import games.rogueLikeAVirgin.entity.Entity;
import games.rogueLikeAVirgin.entity.Item;
import games.rogueLikeAVirgin.entity.Player;

public class Porte extends Case {

	private World world;
	int rotation;

	public Porte(World world, int x, int y,int difficulte) {
		super(x, y,difficulte);
		this.world = world;
		sprite = AppLoader.loadPicture("/images/rogueLikeAVirgin/door"+getCouleur()+".png");

		//System.out.println("porte " + getCouleur() + " x : " + x + "   y " + y);
	}

	@Override
	public void ArriveSur(Entity e) {
		if(e instanceof Player){ //genere new map

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {

		int pX = Math.round(this.world.player.getX()/36);
		int pY = Math.round(this.world.player.getY()/36);


		if(pY == this.y && pX == this.x){
			this.world.changeMap(this.world.generation.genereNewSalle(niveau, y, x));
			this.world.player.setMap(this.world.map.getCases());
			this.world.score += niveau * niveau * 10;
			Random r = new Random();
			int prob = r.nextInt(3);
			if(prob == 0)
				new Item(this.world);

		if(pX == 0)
			pX = 18;
		else if(pX == 19)
			pX = 1;
		if(pY == 0)
			pY = 18;
		else if(pY == 19)
			pY = 1;


			this.world.player.setX(pX*36);
			this.world.player.setY(pY*36);
			this.world.player.getHitbox().setX(pX*36+4);
			this.world.player.getHitbox().setY(pY*36+4);
		}
	}

}
