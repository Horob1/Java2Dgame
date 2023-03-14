package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Candy extends SuperObject{
    GamePanel gp;
    public OBJ_Candy(GamePanel gp) {
        this.gp = gp;
        name = "Candy";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/candy.png"));
            image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
