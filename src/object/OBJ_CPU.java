package object;

import main.GamePanel;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_CPU extends SuperObject{
	GamePanel gp;
	public OBJ_CPU(GamePanel gp) {
		this.gp = gp;
		name = "CPU";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/cpu.png"));
			image = uTool.stepUpImage(image,gp.tileSize,gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
