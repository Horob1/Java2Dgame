package main;
public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent =true;
    public EventHandler (GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent(){
        //check if the player charter is more than 1 tile away form the last event
        int xDistance  = Math.abs(gp.player.worldX - previousEventX);
        int yDistance  = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent){
            if(hit(27,16,"right")){
                damagePit(gp.dialogueState);
            }
            if(hit(23,12,"any")){
                healingPool(23,12,gp.dialogueState);
            }
        }

    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.gameNotifyOn = true;
        gp.ui.currentDialogue = "You fall into a pit!\n Lost 1 hp";
        gp.player.life-=1;
        eventRect[col][row].eventDone =true;
        canTouchEvent = false;
    }

    public void damagePit( int gameState) {
        gp.gameState = gameState;
        gp.ui.gameNotifyOn = true;
        gp.ui.currentDialogue = "You fall into a pit!\n Lost 1 hp";
        gp.player.life-=1;
        canTouchEvent=false;
    }

    public boolean hit ( int col, int row, String reqDirection){
        boolean hit =false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone==false){
            if(gp.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")){
                hit= true;
                previousEventY = gp.player.worldY;
                previousEventX = gp.player.worldX;
            }
        }


        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return hit;
    }

    public void healingPool(int col, int row, int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState=gameState;
            gp.ui.gameNotifyOn = true;
            gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered.\nHp: full";
            gp.player.life=gp.player.maxLife;
            eventRect[col][row].eventDone =true;
        }
    }
}
