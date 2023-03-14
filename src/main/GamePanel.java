package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Step up about screen
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale ; // 48x48
    public final int maxScreenRow = 12;
    public final int maxScreenCol = 16;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    //Thread
    Thread gameThread;
    //FPS
    int FPS =60;
    //Key handler
    public KeyHandler keyH = new KeyHandler(this);
    //event
    public EventHandler eH = new EventHandler(this);
    //Player
    public Player player = new Player(this);
    //Load Map
    TileManager tileM = new TileManager(this);
    public SuperObject[] obj = new SuperObject[50];
    AssetSetter aS = new AssetSetter(this);
    //NPC
    public Entity npc[] = new Entity[10];
    //Collision
    public CollisionChecker cChecker = new CollisionChecker(this);
    //ui
    public UI ui =new UI(this);
    //music
    public Sound sound = new Sound();
    public Sound music = new Sound();
    //game state
    public int gameState;
    final int titleState = 0 ;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // draw
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        double timeDrawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += ( currentTime - lastTime ) / timeDrawInterval;
            lastTime = currentTime;

            if (delta>=1) {
                //1 Update info about position of player
                update();
                //2 repaint with new info
                repaint();

                delta--;
            }
        }
    }

    private void update() {
        if(gameState==playState){
            //update player
            player.updatePlayer();
            //update npc
            for(int indexOfNPC =0; indexOfNPC<npc.length;indexOfNPC++)
            {
                if(npc[indexOfNPC]!=null) npc[indexOfNPC].update();
            }
        }else if(gameState == pauseState ){
        }else if(gameState == dialogueState){
            if(keyH.cPressed && ui.gameNotifyOn==false){
                    npc[cChecker.checkEntity(player,npc)].speak();
                    keyH.cPressed=false;
            }
            else if(keyH.cPressed && ui.gameNotifyOn){
                gameState = playState;
                ui.gameNotifyOn=false;
                keyH.cPressed=false;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //Draw map
        if(gameState == titleState){

        }else {
            tileM.drawTile(g2);
            //obj
            for(int indexOfOBJ =0; indexOfOBJ<obj.length;indexOfOBJ++)
            {
                if(obj[indexOfOBJ]!=null) obj[indexOfOBJ].draw(g2, this);
            }
            //NPC
            for(int indexOfNPC =0; indexOfNPC<npc.length;indexOfNPC++)
            {
                if(npc[indexOfNPC]!=null) npc[indexOfNPC].draw(g2);
            }
            //player
            player.drawPlayer(g2);
            //ui

        }
        ui.draw(g2);
        g2.dispose();

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {
        aS.setObject();
        aS.setNPC();
        gameState = titleState;
    }

    public void playMusic(int indexOfSound) {
        music.setFile(indexOfSound);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int indexOfSound) {
        sound.setFile(indexOfSound);
        sound.play();
    }
}
