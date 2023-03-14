package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{
    int actionCounter=0;
    public NPC_OldMan(GamePanel gp){
        super(gp);
        direction = "down";
        speed =1;

        getImage();
        setDialogue();
    }

    public void setAction(){
        Random random = new Random();
        int intOfRandom = random.nextInt(100)+1;
        actionCounter++;
        if(actionCounter==120){
            if(intOfRandom<=25) {
                direction = "up";
            }
            if(intOfRandom<=50 && intOfRandom>25) {
                direction = "down";
            }
            if(intOfRandom<=75 && intOfRandom>50) {
                direction = "left";
            }
            if(intOfRandom<=100 && intOfRandom>75) {
                direction = "right";
            }
            actionCounter=0;
        }
    }
    public void setDialogue(){
        dialogues[0] = "Hello, boy! Welcome to league of legends.";
        dialogues[1] = "Oh! Sorry, i forget. Welcome to Tutorial island.";
        dialogues[2] = "So you've come to this island to find your laptop?";
        dialogues[3] = "I used to be a great wizard but now ... \nI'm a bit too old for taking an adventure with you.";
        dialogues[4] = "I will support for you.";
        dialogues[5] = "I'm growing old and forgetful.";
        dialogues[6] = "Boy? Who are you?";
        dialogues[7] = "Well, good luck on you!";
        dialogues[8] = "Where is my scepter?";
        dialogues[9] = "";
        dialogues[10] = "";
        dialogues[11] = "";
        dialogues[12] = "";

    }
    public void speak(){
        super.speak();
    }
    void getImage(){

        up1 = setUpImage("/NPC/oldMan/oldman_up_1");
        up2 = setUpImage("/NPC/oldMan/oldman_up_2");
        down1 = setUpImage("/NPC/oldMan/oldman_down_1");
        down2 = setUpImage("/NPC/oldMan/oldman_down_2");
        left1 = setUpImage("/NPC/oldMan/oldman_left_1");
        left2 = setUpImage("/NPC/oldMan/oldman_left_2");
        right1 = setUpImage("/NPC/oldMan/oldman_right_1");
        right2 = setUpImage("/NPC/oldMan/oldman_right_2");
    }

}
