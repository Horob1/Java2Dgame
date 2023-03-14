package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.*;

public class UI {
	
	public boolean gameNotifyOn = false;
	GamePanel gp;
	Graphics2D g2;
	public int commandNum = 0;
	public int commandNumF2 = 0;
	public int titleScreenOption = 0;
	BufferedImage heart_full, heart_half, heart_blank;
	Font arial_80, arial_40, arial_20,arial_20B,arial_40B,arial_80B,
			alagard,alagard_20,alagard_40,alagard_80,alagard_20B,alagard_40B,alagard_80B, alagard_30B,
			purisaB,purisaB_20,purisaB_40,purisaB_60,purisaB_15;
	public boolean gameFinished = false;
	public String message ="";
	int messageCounter = 0;
	public String currentDialogue = "";
	public UI(GamePanel gp) {

		this.gp =gp;
		getUsedFont();
		//create HUB
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank =heart.image3;

	}

	public void getUsedFont(){
		try {
			InputStream is = getClass().getResourceAsStream("/font/alagard.ttf");
			alagard = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		}
		alagard_20 = alagard.deriveFont(Font.PLAIN,20);
		alagard_40 = alagard.deriveFont(Font.PLAIN,40);
		alagard_80 = alagard.deriveFont(Font.PLAIN,80);
		alagard_20B = alagard.deriveFont(Font.BOLD,20);
		alagard_40B = alagard.deriveFont(Font.BOLD,40);
		alagard_80B = alagard.deriveFont(Font.BOLD,80);
		alagard_30B = alagard.deriveFont(Font.BOLD,30);

		purisaB_15 = purisaB.deriveFont(Font.BOLD,15);
		purisaB_20 = purisaB.deriveFont(Font.BOLD,20);
		purisaB_40 = purisaB.deriveFont(Font.BOLD,40);
		purisaB_60 = purisaB.deriveFont(Font.BOLD,60);

		arial_20 = new Font("Arial",Font.PLAIN,20);
		arial_40 = new Font("Arial",Font.PLAIN,40);
		arial_80 = new Font("Arial",Font.PLAIN,80);
		arial_20B = new Font("Arial",Font.BOLD,20);
		arial_40B = new Font("Arial",Font.BOLD,40);
		arial_80B = new Font("Arial",Font.BOLD,80);

	}
	public void draw(Graphics2D g2)
	{	
		this.g2 =g2;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		if(gp.gameState==gp.titleState){
			drawTitleScreen();

		} else if(gp.gameState == gp.playState){
			drawPlayerLife();
		} else if(gp.gameState==gp.pauseState){
			drawPlayerLife();
			showPauseScreen();
		} else if(gp.gameState == gp.dialogueState){
			drawPlayerLife();
			drawDialogueScreen();
		}
	}

	private void drawPlayerLife() {
		int x =gp.tileSize/2;
		int y =gp.tileSize/2;
		int i=0;
		// draw blank
		while (i<gp.player.maxLife/2){
			g2.drawImage(heart_blank,x,y,null);
			i++;
			x+=gp.tileSize;
		}
		//Rest
		x =gp.tileSize/2;
		i=0;
		while (i<gp.player.life){
			g2.drawImage(heart_half,x,y,null);
			i++;
			if(i<gp.player.life)
			{
				g2.drawImage(heart_full,x,y,null);
			}
			i++;
			x+=gp.tileSize;
		}
	}

	private void drawTitleScreen() {
		int x,y;
		String text;
		if(titleScreenOption==1) {
			g2.setColor(new Color(232,104,68));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

			text = "FL Adventure";
			g2.setFont(alagard_80B);
			x = getXForCenteredText(text);
			y = gp.tileSize*3;
			//tile

			g2.setColor(Color.black);
			g2.drawString(text,x+5,y+5);

			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			//image
			y+= gp.tileSize;

			x=gp.screenWidth/2-gp.tileSize*3/2;
			g2.drawImage(gp.player.down1,x,y,gp.tileSize*3,gp.tileSize*3,null);
			g2.drawImage(gp.player.down1,x-4*gp.tileSize,y,gp.tileSize*3,gp.tileSize*3,null);
			g2.drawImage(gp.player.down1,x+4*gp.tileSize,y,gp.tileSize*3,gp.tileSize*3,null);

			x=gp.screenWidth/2-gp.tileSize;
			g2.setFont(alagard_30B);
			text = "WARRIOR";
			y+= gp.tileSize*4;
			x=getXForCenteredText(text);
			g2.drawString(text,x,y);
			if(commandNum==0 && commandNumF2==0){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}

			g2.setFont(alagard_30B);
			text = "MAGE";
			x=getXForCenteredText(text)-4*gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1 && commandNumF2==0){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}



			g2.setFont(alagard_30B);
			text = "ARCHER";
			x=getXForCenteredText(text)+4*gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2 && commandNumF2==0){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}

			g2.setFont(alagard_30B);
			text = "Back";
			x=getXForCenteredText(text);
			y+=gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNumF2==1){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}


			g2.setFont(alagard_40);
			x = gp.screenWidth-200;
			y = gp.screenHeight - 25;
			g2.drawString("@Horob1",x,y);
		}
		else if(titleScreenOption==0){
			g2.setColor(new Color(232,104,68));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

			text = "Select your class!";
			g2.setFont(alagard_40B);
			x = getXForCenteredText(text);
			y = gp.tileSize*3;
			//tile

			g2.setColor(Color.black);
			g2.drawString(text,x+5,y+5);

			g2.setColor(Color.white);
			g2.drawString(text,x,y);
			//image
			y+= gp.tileSize;

			x=gp.screenWidth/2-gp.tileSize*3/2;
			g2.drawImage(gp.player.down1,x,y,gp.tileSize*3,gp.tileSize*3,null);

			g2.setFont(alagard_30B);

			text = "NEW GAME";
			x = getXForCenteredText(text) ;
			y+= gp.tileSize*4;
			g2.drawString(text,x,y);
			if(commandNum==0){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}

			g2.setFont(alagard_30B);
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y+= 40;
			g2.drawString(text,x,y);
			if(commandNum==1){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}



			g2.setFont(alagard_30B);
			text = "QUIT";
			x =getXForCenteredText(text);
			y+= 40;
			g2.drawString(text,x,y);
			if(commandNum==2){
				g2.setFont(alagard_40B);
				g2.drawString(">",x-gp.tileSize,y);
			}


			g2.setFont(alagard_40);
			x = gp.screenWidth-200;
			y = gp.screenHeight - 25;
			g2.drawString("@Horob1",x,y);
		}


	}

	private void drawSubWindow(int x, int y, int width, int height) {
		Color color = new Color(0,0,0,200);
		g2.setColor(color);
		g2.fillRoundRect(x,y,width,height, 35, 35 );

		color = new Color(255,255,255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x,y,width,height, 35, 35 );
	}
	public void drawDialogueScreen() {
		int x= gp.tileSize*2,
				y= gp.tileSize/2;
		int width= gp.screenWidth-gp.tileSize*4;
		int	height = gp.tileSize*4;
		drawSubWindow(x,  y,  width,  height);

		g2.setFont(purisaB_15);
		x+= gp.tileSize;
		y+=gp.tileSize;

		for(String line : currentDialogue.split("\n"))
		{
			g2.drawString(line,x,y);
			y+= gp.tileSize/2;
		}


		g2.setFont(purisaB_20);
		x= width-10;
		y= height;
		g2.drawString("Press C",x,y);
	}

	public void showPauseScreen(){
		String text = "PAUSED";
		g2.setColor(Color.yellow);
		g2.setFont(purisaB_40);
		int x = getXForCenteredText(text);
		int y = gp.screenHeight/2+10;

		g2.drawString(text,x,y);
	}
	public int getXForCenteredText(String text){
		return gp.screenWidth/2 - (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
	}

}
