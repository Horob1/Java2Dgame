package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Choncc extends Entity{
    public Rectangle solidArea = new Rectangle(0,0,46,46);
    public int
            solidAreaDefaultX =solidArea.x, solidAreaDefaultY = solidArea.y;
    public NPC_Choncc(GamePanel gp){
        super(gp);
        speed =0;
        direction = "left";
        getImage();
        setDialogue();
    }
    int actionCounter=0;


    public void setAction(){
        Random random = new Random();
        int intOfRandom = random.nextInt(100)+1;
        actionCounter++;
        if(actionCounter==30){
            if(intOfRandom<=50 ) {
                direction = "left";
            }
            if(intOfRandom<=100 && intOfRandom>50) {
                direction = "right";
            }
            actionCounter=0;
        }
    }
    void getImage(){
        left1 = setUpImage("/NPC/choncc/choncc2");
        right1 = setUpImage("/NPC/choncc/choncc1");

    }

    public void setDialogue(){
        dialogues[0] = "Hungry!";
        dialogues[1] = "Candy!";
        dialogues[2] = "Ducky!";
        dialogues[3] = "DCuH! DCuH! DCuH!";


    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX  + gp.player.screenX;
        int screenY = worldY - gp.player.worldY  + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX  - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX  + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY  - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY  + gp.player.screenY) {
            switch (direction) {

                case "right":
                    image = right1;
                    break;
                case "left":
                    image = left1;
                    break;
            }
            g2.drawImage(image, screenX, screenY,gp.tileSize+15,gp.tileSize+15, null);
        }
    }

    public void speak(){
        if(dialogues[dialogueIndex]== null) {
            dialogueIndex=0;
            gp.gameState = gp.playState;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":  break;
            case "down": break;
            case "right": direction = "left"; break;
            case "left": direction = "right"; break;
        }
    }

}
