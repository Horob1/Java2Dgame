package object;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SSD extends SuperObject{
	GamePanel gp;
	public OBJ_SSD(GamePanel gp) {
		this.gp = gp;
		name = "SSD";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/ssd.png"));
			image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
