package OpenCvYuzTanima;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconGetir {
	
	public static ImageIcon onayIcon(){
		Image imgOnay=null;
		try {
			imgOnay = ImageIO.read(new File("resim\\onay.png"));
			imgOnay = imgOnay.getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImageIcon(imgOnay);
	}
	
	public static ImageIcon hataIcon(){
		Image imgHata=null;
		try {
			imgHata = ImageIO.read(new File("resim\\uyari.png"));
			imgHata = imgHata.getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImageIcon(imgHata);
	}
	
	public static ImageIcon hata2Icon(){
		Image imgHata=null;
		try {
			imgHata = ImageIO.read(new File("resim\\hata.png"));
			imgHata = imgHata.getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImageIcon(imgHata);
	}
}
