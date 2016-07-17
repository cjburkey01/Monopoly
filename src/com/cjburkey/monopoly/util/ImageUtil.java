package com.cjburkey.monopoly.util;

import java.io.InputStream;
import com.cjburkey.monopoly.Monopoly;
import javafx.scene.image.Image;

public class ImageUtil {
	
	public static final Image loadImage(String path) {
		try {
			InputStream stream = ImageUtil.class.getClassLoader().getResourceAsStream(path);
			if(stream != null) {
				Image img = new Image(stream);
				return img;
			} else {
				Monopoly.log("Image not found: " + path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}