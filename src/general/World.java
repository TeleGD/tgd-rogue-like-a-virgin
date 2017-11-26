package general;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tests.MusicListenerTest;

import entity.enemies.Enemy;
import entity.enemies.Enemy1;
import entity.Player;
import entity.Projectile;
import entity.Item;
import general.Main;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;
import general.utils.FontUtils;
import map.Generation;
import map.Salle;
import menus.MainMenu;

public class World extends BasicGameState {
	
	public static int ID=2;

	public final static String GAME_NAME="RogueLikeAVirgin";
	
	public final static String GAME_FOLDER_NAME="RogueLike";
	public final static String DIRECTORY_SOUNDS="sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	private static StateBasedGame game;
	
	public static ArrayList<Enemy> enemies,enemiesTmp;
	public static ArrayList<Projectile> projectiles,projectilesTmp;
	public static Player player;
	public static ArrayList<Item> item;
	public static Salle map;
	public static Enemy Nico;
	public static int score;
	
	private Image coeur,coin;
	private Button jouer,atkUp,speedUp,delayUp,oneUp,rejouer ;
	private int atkCoin,speedCoin,delayCoin,oneCoin;
	private boolean gameOn,gameOver;
	private static Sound saxGuy;

	private static Music mainMusic;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		game=arg1;
		gameOn = false;
		gameOver = false;
		score = 0;
		saxGuy=new Sound("musics/boss.ogg");
		mainMusic=new Music("musics/music.ogg");
		//Il faudra voir s'il faut bouger ces inits dans enter(...) si ca prend trop de temps
		enemies = new ArrayList<Enemy>();
		enemiesTmp = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		projectilesTmp = new ArrayList<Projectile>();
		item = new ArrayList<Item>();
		map =  Generation.genereSalle(0, 20,20 ,0);
		atkCoin = 10;
		speedCoin = 5;
		delayCoin = 10;
		oneCoin = 1;
		
		
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		coeur = new Image(World.DIRECTORY_IMAGES+"vie.png");
		coin = new Image(World.DIRECTORY_IMAGES+"itemCoin.png");
		
		jouer = new Button("Jouer",container,786,294,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
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
		
		rejouer = new Button("Rejouer",container,786,294,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		rejouer.setTextSize(32);
		rejouer.setBackgroundColor(new Color(255,255,255));
		rejouer.setSize(420,120);
		rejouer.setTextColor(Color.black);
		rejouer.setPadding(70,100,70,100);
		rejouer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	try {
					startAgain();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
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
            		World.player.setAtk(World.player.getAtk()+1);
                    if (atkCoin > 499999){
                    	atkCoin = 999999;
                    }else{
                    	atkCoin = atkCoin*2;
                    }
            	}
                
            }});
		
		speedUp = new Button("Vitesse Up",container,1040,570,TGDComponent.AUTOMATIC,30);
		speedUp.setBackgroundColor(new Color(255,255,255));
		speedUp.setTextColor(Color.black);
		speedUp.setPadding(7,10,7,10);
		speedUp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	if (player.getCoin()>=speedCoin){
	            	player.setCoin(player.getCoin()-speedCoin);
	                World.player.setSpeed(World.player.getSpeed()*1.1);
	                if (speedCoin > 499999){
	                	speedCoin = 999999;
	                }else{
	                	speedCoin = speedCoin*2;
	                }
            	}

            }});
		
		delayUp = new Button("Cadence Up",container,780,630,TGDComponent.AUTOMATIC,30);
		delayUp.setBackgroundColor(new Color(255,255,255));
		delayUp.setTextColor(Color.black);
		delayUp.setPadding(7,10,7,10);
		delayUp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	if (player.getCoin()>=delayCoin){
	            	player.setCoin(player.getCoin()-delayCoin);
	            	if(World.player.getPeriode() >= 10)
	            		World.player.setPeriod(World.player.getPeriode()-5);
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
	            	if(World.player.getHp() <= 19)
	            		World.player.setHp(World.player.getHp()+1);
	            	if (oneCoin != 999999){
	            		oneCoin = oneCoin+1;
	                }
            	}

            }});
		mainMusic.play();
	}
	
	public static void changeMap(Salle s){
		map = s;
	}
	
	public void startGame(){
		gameOn = true;
		try {
			player = new Player();
			score = 0;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startAgain() throws SlickException{
		score = 0;
		gameOn = true;
		gameOver = false;
		enemies = new ArrayList<Enemy>();
		enemiesTmp = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		projectilesTmp = new ArrayList<Projectile>();
		item = new ArrayList<Item>();
		map = Generation.genereNewSalle(0, 1, 1);
		player.setMap(World.map.getCases());
		player.setX(10*36);
		player.setY(10*36);
		player.getHitbox().setX(10*36+4);
		player.getHitbox().setY(10*36+4);
		atkCoin = 10;
		speedCoin = 5;
		delayCoin = 10;
		oneCoin = 1;
		player = new Player();
		mainMusic.play();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (!gameOn && !gameOver){
			jouer.render(container, game, g);
		} else if (!gameOn && gameOver){
			rejouer.render(container, game, g);
		}
		

		map.render(container, game, g);
		for(Item i : item){
			i.render(container, game, g);
		}
		
		if (gameOn){
			player.render(container, game, g);
			
			for(Enemy e : enemies){
				e.render(container, game, g);
			}

			for(Projectile p : projectiles){
				p.render(container, game, g);
			}
			for (int i = 0; i < World.player.getHp(); i++){
				if ( i < 10 ){
					g.drawImage(coeur, 756+i*50, 36);
				} else {
					g.drawImage(coeur, 756+(i-10)*50, 86);
				}
			}
			g.setLineWidth(36);
			g.setColor(Color.white);
			g.drawString("Vitesse : "+(Math.floor(World.player.getSpeed()*100)/100), 756, 100+((World.player.getHp()-1)/10)*50);
			g.drawString("Puissance : "+World.player.getAtk(), 756, 150+((World.player.getHp()-1)/10)*50);
			g.drawString("Cadence de tir : "+World.player.getPeriode(), 756, 200+((World.player.getHp()-1)/10)*50);
			g.drawString("Score : "+score, 945, 345);
			g.drawString("Pièces : "+World.player.getCoin(), 936, 500);
			if (player.getCoin() == 0){
				g.drawImage(coin, 1044, 491);
			} else {
				g.drawImage(coin,(float) ((float)1044+8*Math.floor(Math.log10(player.getCoin()))), 491);
			}
			
			atkUp.render(container, game, g);
			oneUp.render(container, game, g);
			speedUp.render(container, game, g);
			delayUp.render(container, game, g);
			
			g.setLineWidth(36);
			g.setColor(Color.white);
			g.drawString(""+atkCoin, 915, 576);
			g.drawImage(coin,(float) ((float)938+8*Math.floor(Math.log10(atkCoin))), 567);
			g.drawString(""+delayCoin, 904, 635);
			g.drawImage(coin,(float) ((float)927+8*Math.floor(Math.log10(delayCoin))), 626);
			g.drawString(""+speedCoin, 1152, 576);
			g.drawImage(coin,(float) ((float)1175+8*Math.floor(Math.log10(speedCoin))), 567);
			g.drawString(""+oneCoin, 1132, 635);
			g.drawImage(coin,(float) ((float)1158+8*Math.floor(Math.log10(oneCoin))), 626);
		}
		
		
		
		
		
		
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (gameOn){
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
				gameOver = true;
				gameOn = false;
			}
		}
		
		
	}
	
	public void keyReleased(int key, char c) {
		if (gameOn){
			player.keyReleased(key,c);
		}
	}


	public void keyPressed(int key, char c) {
		if (gameOn){
			player.keyPressed(key,c);
		}
		if (!gameOn && !gameOver){
			if (key==Input.KEY_ENTER){
				startGame();
			}
		}
		if (!gameOn && gameOver){
			if (key==Input.KEY_ENTER){
				try {
					startAgain();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}
	
	public static void stopMusic() {
		mainMusic.pause();
	}
	
	public static void resumeMusic() {
		mainMusic.resume();
	}
	public static boolean isSaxGuy() {
		return saxGuy.playing();
	}
	
	public static void changeMusic() {
		if(saxGuy.playing()) {
			saxGuy.stop();
			System.out.println("on a bien");
			mainMusic.resume();
			//mainMusic.stop();
		}else {
			mainMusic.pause();
			saxGuy.play();
		}
	}
}
