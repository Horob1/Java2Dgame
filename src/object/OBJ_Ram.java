package object;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Ram extends SuperObject{
	GamePanel gp;
	public OBJ_Ram(GamePanel gp) {
		this.gp = gp;
		name = "Ram";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/ram.png"));
			image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
