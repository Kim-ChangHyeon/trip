package com.poseidon.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TripImgReSize {
	public static void reSize(String url, String name) throws IOException {
		File in = new File(url + name);
		BufferedImage inputImg = ImageIO.read(in);
		
		int width = 160;
		int height = 160;
		
		String[] imgs = {"bmp", "gif","jpg","png"};
		
		for (String format : imgs) {
			BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D gr2d = outputImg.createGraphics();
			gr2d.drawImage(inputImg, 0, 0, width, height, null);
			File out = new File(url + "/thumbnail/" + name);//같은 경로에 넣으면 안된다.
			FileOutputStream fos = new FileOutputStream(out);
			ImageIO.write(outputImg, format, fos);
			fos.close();
		}				
		
	}
}
