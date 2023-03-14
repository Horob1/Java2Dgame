package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] tileMapNum;
    UtilityTool uTool = new UtilityTool();

    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tile = new Tile[50];
        this.tileMapNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        // no use
        setUpImage(0,"grass00", false);
        setUpImage(1,"grass00", true);
        setUpImage(2,"grass00", true);
        setUpImage(3,"grass00", false);
        setUpImage(4,"grass00", true);
        setUpImage(6,"grass00", false);
        setUpImage(7,"grass00", false);
        setUpImage(8,"grass00", false);
        setUpImage(9,"grass00", false);

        setUpImage(10,"grass00", false);
        setUpImage(11,"grass01", false);
        setUpImage(12,"water00", true);
        setUpImage(13,"water01", true);
        setUpImage(14,"water02", true);
        setUpImage(15,"water03", true);
        setUpImage(16,"water04", true);
        setUpImage(17,"water05", true);
        setUpImage(18,"water06", true);
        setUpImage(19,"water07", true);
        setUpImage(20,"water08", true);
        setUpImage(21,"water09", true);
        setUpImage(22,"water10", true);
        setUpImage(23,"water11", true);
        setUpImage(24,"water12", true);
        setUpImage(25,"water13", true);
        setUpImage(26,"road00", false);
        setUpImage(27,"road01", false);
        setUpImage(28,"road02", false);
        setUpImage(29,"road03", false);
        setUpImage(30,"road04", false);
        setUpImage(31,"road05", false);
        setUpImage(32,"road06", false);
        setUpImage(33,"road07", false);
        setUpImage(34,"road08", false);
        setUpImage(35,"road09", false);
        setUpImage(36,"road10", false);
        setUpImage(37,"road11", false);
        setUpImage(38,"road12", false);
        setUpImage(39,"earth", false);
        setUpImage(40,"wall", true);
        setUpImage(41,"tree", true);



    }

    void setUpImage(int index, String tileName, boolean collision){
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+tileName+".png"));
            tile[index].image = uTool.stepUpImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){

        try {
                InputStream is = getClass().getResourceAsStream("/maps/worldV2.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int col = 0 , row = 0;
                while(col<gp.maxWorldCol && row<gp.maxWorldRow) {
                    String line = br.readLine();
                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        tileMapNum[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        row++;
                        col = 0;

                    }

                }
                br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawTile(Graphics2D g2){
        int worldCol = 0, worldRow = 0;
        while (worldCol < gp.maxWorldCol  && worldRow < gp.maxWorldRow)
        {
            int tileNum = tileMapNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX  + gp.player.screenX;
            int screenY = worldY - gp.player.worldY  + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX  - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX  + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY  - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY  + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            worldCol++;


            if(worldCol == gp.maxWorldCol) {
                worldCol=0;
                worldRow++;

            }
        }
    }


}
