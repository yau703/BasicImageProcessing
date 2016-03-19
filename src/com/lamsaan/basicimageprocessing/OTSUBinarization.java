package com.lamsaan.basicimageprocessing;

import java.awt.image.BufferedImage;

/**
 * OTSU在对光线不均匀的图像进行二值化时，效果可能不是很好，如果图像分辨率过大计算时间可能较长
 * @author LamsaanYau
 *
 */

public class OTSUBinarization {
	private static int mWidth,mHeight;
	private static int[] hist;
	
	/**
	 * 获取要处理图像属性
	 * 
	 * @param image
	 */
	private static void setProperty(BufferedImage image){
		mWidth = image.getWidth();
		mHeight = image.getHeight();
		hist=new int[256];
		for(int i=0;i<mWidth;i++)
			for(int j=0;j<mHeight;j++)
				hist[image.getRGB(i, j)&0xff]++;
//		for(int i=0;i<256;i++)
//			System.out.println(i+":"+hist[i]);
	}
	
	/**
	 * 二值化
	 * 
	 * @param image
	 */
	public static void binarizing(BufferedImage image){
		setProperty(image);
		int t=getThreshold();
//		System.out.println(t);
		int gray;
		for(int i=0;i<mWidth;i++)
			for(int j=0;j<mHeight;j++){
				gray=image.getRGB(i, j)&0xff;
//				System.out.println(gray);
				if(gray>t){
					gray = 255;
					image.setRGB(i, j, (0xff << 24) | (gray << 16) | (gray << 8) | gray);
				}else{
					gray =0;
					image.setRGB(i, j, (0xff << 24) | (gray << 16) | (gray << 8) | gray);
				}
					
			}

		}
	
	/**
	 * 根据OTSU大津算法计算阈值ֵ
	 * 
	 * @return
	 */
	private static int getThreshold(){
		double total = mWidth * mHeight;//图像像素总数
		int threshold=0;
		double vari=0,minVari=Double.MAX_VALUE;//方差、最小方差
		double w0, u0 ,vari0,w1 ,u1,vari1,count;//分组权重，均值，方差，区间像素数
		
		 // 分别以i∈[0,255]为阈值计算类内方差
        for(int i=0; i<256; i++)  
        {  
        	//计算阈值i前一部分方差
            w0 = 0; u0 = 0;vari0 = 0;count = 0;  
            for(int t=0; t<i; t++){  
                count += hist[t];  
                u0 += hist[t] * t;  
            }
            w0 = count / total;  
            u0 = (count == 0) ? 0 :(u0 / count);  
            for(int t=0; t<i; t++)  
            {  
                vari0 += (Math.pow((t-u0),2) * hist[t]);  
            }  
            vari0 = (count == 0) ? 0 : (vari0 / count);  //组1方差
            
            //计算阈值i后一部分方差
            w1 = 0; u1 = 0;vari1 = 0;count = 0;  
            for(int t=i; t<hist.length; t++)  
            {  
                count += hist[t];  
                u1 += hist[t] * t;  
            }  
            if(count==0)   //当后半部分像素数为0时既可退出计算
            	break;
            w1 = count / total;  
            u1 = (count == 0) ? 0 : (u1 / count);  
            for(int t=i; t<hist.length; t++)  
            {  
                vari1 += (Math.pow((t-u1),2) * hist[t]);  
            }  
            vari1 = (count == 0) ? 0 : (vari1 / count);   //组2方差
            
            vari = w0 * vari0 + w1 * vari1;   //计算类内方差
            if(vari<minVari){
            	minVari=vari;
            	threshold = i;
            }
        } 
        return threshold;
	}
	
}
