package com.lamsaan.basicimageprocessing;
import java.awt.image.*;

/**
 * 
 * @author LamssanYau
 * 
 */
public class Graying {
	/**
	 * 
	 * @param image 要处理的图像
	 */
	public static void grayProcessing(BufferedImage image){
		
		int width=image.getWidth();
		int height=image.getHeight();
		int color,newColor;
		int a,r,g,b;
		int gray;
//		System.out.println(width+":"+height);
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++){
				color = image.getRGB(i, j);
                a = (color >> 24) & 0xff;  
                r = (color >> 16) & 0xff;  
                g = (color >> 8) & 0xff;  
                b = color & 0xff;  
                gray= (int)(0.3 *r + 0.59*g + 0.11*b);  
                newColor  = (a << 24) | (gray << 16) | (gray << 8) | gray;  
                image.setRGB(i, j, newColor);
			}
	}
}
