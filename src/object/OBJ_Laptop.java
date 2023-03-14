package object;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Laptop extends SuperObject{
	GamePanel gp;
	public OBJ_Laptop(GamePanel gp) {
		this.gp = gp;
		name = "Laptop";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/laptop.png"));
			image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
