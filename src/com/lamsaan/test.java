package com.lamsaan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.lamsaan.basicimageprocessing.OTSUBinarization;
import com.lamsaan.basicimageprocessing.Graying;

/**
 * 
 * @author LamsaanYau
 *
 */
public class test {

	public static void main(String[] args) {
		
		//replace the file with a valid one if you want to run this example
		File imageFile=new File("demo.png");
		try {
			
			BufferedImage image = ImageIO.read(imageFile);
			Graying.grayProcessing(image);
			OTSUBinarization.binarizing(image);
			String fileName = imageFile.getName().substring(0, imageFile.getName().lastIndexOf('.'))+"copy";
			String fileType = imageFile.getName().substring(imageFile.getName().lastIndexOf('.')+1);
			ImageIO.write(image, fileType, new File("G:\\"+ fileName +  "."+fileType));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
