package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
	GamePanel gp;
	public OBJ_Boots(GamePanel gp) {
		this.gp = gp;
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
