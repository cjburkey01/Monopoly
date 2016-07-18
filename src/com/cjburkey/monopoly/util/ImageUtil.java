package com.cjburkey.monopoly.util;

import java.io.InputStream;
import com.cjburkey.monopoly.Monopoly;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class ImageUtil {
	
	public static final Image loadImage(String path, Point2D size, boolean smooth) {
		try {
			InputStream stream = ImageUtil.class.getClassLoader().getResourceAsStream(path);
			if(stream != null) {
				Image img;
				if(size.equals(Point2D.ZERO)) img = new Image(stream); else img = new Image(stream, size.getX(), size.getY(), true, smooth);
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