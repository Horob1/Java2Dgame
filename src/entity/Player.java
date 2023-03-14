package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    public final int screenX;
    public final int screenY;


    // variable about obj
    public Player (GamePanel gp) {
        super(gp);

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        setDefaultPlayerValues();
        getImage();
    }

    void setDefaultPlayerValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
        //player status
        maxLife = 6;
        life = maxLife;

    }

    void getImage(){

            up1 = setUpImage("/player/move_up_1");
            up2 = setUpImage("/player/move_up_2");
            down1 = setUpImage("/player/move_down_1");
            down2 = setUpImage("/player/move_down_2");
            left1 = setUpImage("/player/move_left_1");
            left2 = setUpImage("/player/move_left_2");
            right1 = setUpImage("/player/move_right_1");
            right2 = setUpImage("/player/move_right_2");
    }


    public void updatePlayer(){
        if(gp.keyH.upPressed|| gp.keyH.downPressed|| gp.keyH.leftPressed|| gp.keyH.rightPressed)
        {
            if (gp.keyH.upPressed) {
                direction = "up";
            } else if(gp.keyH.downPressed){
                direction = "down";
            }
            else if(gp.keyH.rightPressed){
                direction = "right";
            }
            else {
                direction = "left";
            }

            // reset collision
            collisionOn =false;
            // check collision
            int indexOfOBJ = gp.cChecker.checkObject(this,true);
            pickUpObj(indexOfOBJ);
            gp.cChecker.checkTile(this);
            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
            interactNpc(npcIndex);
            //check event
            gp.eH.checkEvent();
            if(collisionOn==false){
                switch (direction) {
                    case "up":
                        worldY-=speed;
                        break;

                    case "down":
                        worldY+=speed;
                        break;

                    case "right":
                        worldX+=speed;
                        break;

                    case "left":
                        worldX-=speed;
                        break;
                }

            }


            spriteCounter++;
            if (spriteCounter >12)
            {
                if(spriteNum==1){
                    spriteNum=2;
                } else if(spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }

    }

    private void interactNpc(int npcIndex) {
        if(npcIndex!=999){
            if(gp.keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[npcIndex].speak();
            }
            gp.npc[npcIndex].dialogueIndex = 0;
        }
    }

    public void pickUpObj(int indexObj) {
        if(indexObj !=999)
        {

        }
    }
    public void drawPlayer(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if(spriteNum==2){
                    image = up2;
                }
                break;

            case "down":
                if(spriteNum==1){
                    image = down1;
                }
                if(spriteNum==2){
                    image = down2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if(spriteNum==2){
                    image = right2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                if(spriteNum==2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}

