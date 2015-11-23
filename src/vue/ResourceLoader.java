package vue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	public static BufferedImage getImage(String relativePath) {
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(ResourceLoader.class.getResourceAsStream("/ressources/images/"+relativePath));
		} catch (IOException e) {
			System.err.println("Couldn't open file " + "/ressources/images/"+relativePath);
		}
		return bi;
	}
}
