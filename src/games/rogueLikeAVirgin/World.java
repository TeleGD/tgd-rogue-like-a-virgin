package games.rogueLikeAVirgin;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;
import app.ui.Button;
import app.ui.TGDComponent;
import app.ui.TGDComponent.OnClickListener;

import games.rogueLikeAVirgin.entity.Item;
import games.rogueLikeAVirgin.entity.Player;
import games.rogueLikeAVirgin.entity.Projectile;
import games.rogueLikeAVirgin.entity.enemies.Enemy;
import games.rogueLikeAVirgin.map.Generation;
import games.rogueLikeAVirgin.map.Salle;

public class World extends BasicGameState {

	public static int longueur = 1280;
	public static int hauteur = 720;

	private int ID;
	private int state;

	public ArrayList<Enemy> enemies,enemiesTmp;
	public ArrayList<Projectile> projectiles,projectilesTmp;
	public Player player;
	public ArrayList<Item> item;
	public Salle map;
	public Enemy Nico;
	public int score;
	public Generation generation;

	private Image coeur,coin;
	private Button jouer,atkUp,speedUp,delayUp,oneUp,rejouer,pauseMode, exit ;
	private int atkCoin,speedCoin,delayCoin,oneCoin;
	private boolean gameOn,gameOver;
	private Audio saxGuy;

	private Audio mainMusic;
	private float mainMusicPos;
	private boolean scoreTime;
	public String name;
	private int pauseCompt;
	private boolean pause,maj;
	private StateBasedGame game;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) {
		/* Méthode exécutée une unique fois au chargement du programme */
		this.game=arg1;
		saxGuy=AppLoader.loadAudio("/sounds/rogueLikeAVirgin/boss.ogg");
		mainMusic=AppLoader.loadAudio("/musics/rogueLikeAVirgin/music.ogg");

		coeur = AppLoader.loadPicture("/images/rogueLikeAVirgin/vie.png");
		coin = AppLoader.loadPicture("/images/rogueLikeAVirgin/itemCoin.png");

		jouer = new Button("Play",container,786,294,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		jouer.setTextSize(32);
		jouer.setBackgroundColor(new Color(255,255,255));
		jouer.setSize(420,120);
		jouer.setTextColor(Color.black);
		jouer.setPadding(70,100,70,100);
		jouer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				startGame();
			}});

		rejouer = new Button("Try again",container,786,234,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		rejouer.setTextSize(32);
		rejouer.setBackgroundColor(new Color(255,255,255));
		rejouer.setSize(420,120);
		rejouer.setTextColor(Color.black);
		rejouer.setPadding(70,100,70,100);
		rejouer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				initGame();
				startGame();
			}});

		pauseMode = new Button("Continue",container,786,234,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		pauseMode.setTextSize(32);
		pauseMode.setBackgroundColor(new Color(255,255,255));
		pauseMode.setSize(420,120);
		pauseMode.setTextColor(Color.black);
		pauseMode.setPadding(70,100,70,100);
		pauseMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if(pauseCompt==0) {
					pause=false;
				}else {
					game.enterState(1 /* MainMenu */, new FadeInTransition(), new FadeOutTransition());
				}

			}});

		exit = new Button("Quitter",container,786,384,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		exit.setTextSize(32);
		exit.setBackgroundColor(new Color(255,255,255));
		exit.setSize(420,120);
		exit.setTextColor(Color.black);
		exit.setPadding(70,100,70,100);
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				game.enterState(1 /* MainMenu */, new FadeInTransition(), new FadeOutTransition());
			}});

		atkUp = new Button("Puissance Up",container,780,570,TGDComponent.AUTOMATIC,30);
		atkUp.setBackgroundColor(new Color(255,255,255));
		atkUp.setTextColor(Color.black);
		atkUp.setPadding(7,10,7,10);
		atkUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (player.getCoin()>=atkCoin){
					player.setCoin(player.getCoin()-atkCoin);
					player.setAtk(player.getAtk()+1);
					if (atkCoin > 499999){
						atkCoin = 999999;
					}else{
						atkCoin = atkCoin*2;
					}
				}

			}});

		speedUp = new Button("Speed Up",container,1040,570,TGDComponent.AUTOMATIC,30);
		speedUp.setBackgroundColor(new Color(255,255,255));
		speedUp.setTextColor(Color.black);
		speedUp.setPadding(7,10,7,10);
		speedUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (player.getCoin()>=speedCoin){
					player.setCoin(player.getCoin()-speedCoin);
					player.setSpeed(player.getSpeed()*1.1);
					if (speedCoin > 499999){
						speedCoin = 999999;
					}else{
						speedCoin = speedCoin*2;
					}
				}

			}});

		delayUp = new Button("Firerate Up",container,780,630,TGDComponent.AUTOMATIC,30);
		delayUp.setBackgroundColor(new Color(255,255,255));
		delayUp.setTextColor(Color.black);
		delayUp.setPadding(7,10,7,10);
		delayUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (player.getCoin()>=delayCoin){
					player.setCoin(player.getCoin()-delayCoin);
					if(player.getPeriode() >= 10)
						player.setPeriod(player.getPeriode()-5);
					if (delayCoin > 333334){
						delayCoin = 999999;
					}else{
						delayCoin = delayCoin*3;
					}
				}

			}});

		oneUp = new Button("One Up",container,1040,630,TGDComponent.AUTOMATIC,30);
		oneUp.setBackgroundColor(new Color(255,255,255));
		oneUp.setTextColor(Color.black);
		oneUp.setPadding(7,10,7,10);
		oneUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (player.getCoin()>=oneCoin){
					player.setCoin(player.getCoin()-oneCoin);
					if(player.getHp() <= 19)
						player.setHp(player.getHp()+1);
					if (oneCoin != 999999){
						oneCoin = oneCoin+1;
					}
				}

			}});

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
		mainMusic.playAsMusic(1f, .3f, true);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	public void changeMap(Salle s){
		map = s;
	}

	public void startGame(){
		gameOn = true;
	}

	public void initGame() {
		score = 0;
		gameOn = false;
		gameOver = false;
		pause = false;
		pauseCompt = 0;
		enemies = new ArrayList<Enemy>();
		enemiesTmp = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		projectilesTmp = new ArrayList<Projectile>();
		item = new ArrayList<Item>();
		generation = new Generation(this);
		map =  generation.genereSalle(0, 20,20 ,0);
		player = new Player(this);
		player.setMap(map.getCases());
		player.setX(10*36);
		player.setY(10*36);
		player.getHitbox().setX(10*36+4);
		player.getHitbox().setY(10*36+4);
		atkCoin = 10;
		speedCoin = 5;
		delayCoin = 10;
		oneCoin = 1;
		scoreTime=false;
		maj=false;
		mainMusic.playAsMusic(1f, .3f, true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		/* Méthode exécutée environ 60 fois par seconde */
		map.render(container, game, g);
		for(Item i : item){
			i.render(container, game, g);
		}


		if(!scoreTime) {
			//jouer ou rejouer si la partie n'est pas lancee ou qu'elle vient de se terminer
			if (!gameOn && !gameOver){
				jouer.render(container, game, g);
			} else if (!gameOn && gameOver){
				rejouer.render(container, game, g);
				exit.render(container, game, g);
			}

			if (gameOn){
				player.render(container, game, g);

				for(Enemy e : enemies){
					e.render(container, game, g);
				}

				for(Projectile p : projectiles){
					p.render(container, game, g);
				}
				for (int i = 0; i < player.getHp(); i++){
					if ( i < 10 ){
						g.drawImage(coeur, 756+i*50, 36);
					} else {
						g.drawImage(coeur, 756+(i-10)*50, 86);
					}
				}

				if(pause) {
					pauseMode.render(container,game,g);
					exit.render(container, game, g);
				}else {

					g.setLineWidth(36);
					g.setColor(Color.white);
					g.drawString("Speed : "+(Math.floor(player.getSpeed()*100)/100), 756, 100+((player.getHp()-1)/10)*50);
					g.drawString("Power : "+player.getAtk(), 756, 150+((player.getHp()-1)/10)*50);
					g.drawString("Cadence de tir : "+player.getPeriode(), 756, 200+((player.getHp()-1)/10)*50);
					g.drawString("Score : "+score, 900, 320);
					g.drawString("Money : "+player.getCoin(), 936, 500);
					if (player.getCoin() == 0){
						g.drawImage(coin, 1044, 491);
					} else {
						g.drawImage(coin,(float) (1044+8*Math.floor(Math.log10(player.getCoin()))), 491);
					}

					atkUp.render(container, game, g);
					oneUp.render(container, game, g);
					speedUp.render(container, game, g);
					delayUp.render(container, game, g);

					g.setLineWidth(36);
					g.setColor(Color.white);
					g.drawString(""+atkCoin, 915, 576);
					g.drawImage(coin,(float) (938+8*Math.floor(Math.log10(atkCoin))), 567);
					g.drawString(""+delayCoin, 904, 635);
					g.drawImage(coin,(float) (927+8*Math.floor(Math.log10(delayCoin))), 626);
					g.drawString(""+speedCoin, 1152, 576);
					g.drawImage(coin,(float) (1175+8*Math.floor(Math.log10(speedCoin))), 567);
					g.drawString(""+oneCoin, 1132, 635);
					g.drawImage(coin,(float) (1158+8*Math.floor(Math.log10(oneCoin))), 626);
				}
			}
		} else {
			g.drawString("Score: "+score, 900, 320);
			g.drawString("Enter your name: "+name,870,350);
		}











	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		// Input input = container.getInput();
		// if (input.isKeyDown(Input.KEY_ESCAPE)) {
		// 	this.setState(1);
		// 	game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		// }
		if (gameOn){
			if(!pause) {
				player.update(container, game, delta);
				for(int i = 0; i < item.size(); i++){
					item.get(i).update(container, game, delta);
				}
				for(int i = 0; i < enemies.size(); i++){
					enemies.get(i).update(container, game, delta);
				}
				for(int i = 0; i < projectiles.size(); i++){
					projectiles.get(i).update(container, game, delta);
				}
				map.update(container, game, delta);
				if (player.getHP()<=0){
					scoreTime=true;
					name="";
					gameOver = true;
					gameOn = false;
				}
			}
		}


	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		this.initGame();
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	@Override
	public void keyReleased(int key, char c) {
		if (gameOn){
			player.keyReleased(key,c);
		}
		if(scoreTime) {
			if(key==Input.KEY_LSHIFT)
				maj=false;
		}
	}


	@Override
	public void keyPressed(int key, char c) {
		if ((pause || gameOver) && key == Input.KEY_ESCAPE) {
			game.enterState(1 /* MainMenu */, new FadeInTransition(), new FadeOutTransition());
		}
		if(scoreTime){
			if(!maj && !(key==Input.KEY_LSHIFT &&!(key==Input.KEY_ENTER))) {
				name+=c;
				System.out.println(name);
			}
			else if(!(key==Input.KEY_LSHIFT &&!(key==Input.KEY_ENTER))){
				System.out.println(name);
				name+=(""+c).toUpperCase();
			}
			switch (key) {

			case Input.KEY_BACK://.KEY_RETURN:
				if (name.length()>=2) {
					name=name.substring(0, name.length()-2);
				}
				break;
			case Input.KEY_ENTER :
				name=name.replace("'", "''");
				name=name.substring(0, name.length()-1);

				// Dao.addScore(name, score);
				scoreTime=false;
				break;
			case Input.KEY_LSHIFT:
				maj=true;
			}
		}else if(!pause) {
			if (gameOn){
				player.keyPressed(key,c);
				if(key==Input.KEY_ESCAPE) {
					pause=true;
					pauseCompt=0;
				}
			}
			if (!gameOn && !gameOver){
				if (key==Input.KEY_ENTER){
					startGame();
				}
			}
			if (!gameOn && gameOver){
				if (key==Input.KEY_ENTER){
					initGame();
				}
			}
		} else {
			if(pauseCompt==1 && key==Input.KEY_ENTER) {
				game.enterState(1 /* MainMenu */,new FadeOutTransition(), new FadeInTransition());
			}else if(key==Input.KEY_Z || key==Input.KEY_S) {
				pauseCompt=1-pauseCompt;
				if(pauseCompt==0) {
					pauseMode.setText("Continue");
				}else {
					pauseMode.setText("Quit");
				}
			}else if(pauseCompt==0 && key==Input.KEY_ENTER) {
				pause=false;
			}
		}
	}

	public void stopMusic() {
		mainMusicPos = mainMusic.getPosition();
		mainMusic.stop();
	}

	public void resumeMusic() {
		mainMusic.playAsMusic(1, .3f, true);
		mainMusic.setPosition(mainMusicPos);
	}
	public boolean isSaxGuy() {
		return saxGuy.isPlaying();
	}

	public void changeMusic() {
		if(saxGuy.isPlaying()) {
			saxGuy.stop();
			System.out.println("on a bien");
			mainMusic.playAsMusic(1, .3f, true);
			mainMusic.setPosition(mainMusicPos);
		}else {
			mainMusicPos = mainMusic.getPosition();
			mainMusic.stop();
			saxGuy.playAsSoundEffect(1, .3f, false);
		}
	}
}
