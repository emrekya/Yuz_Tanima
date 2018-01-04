package OpenCvYuzTanima;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.IntBuffer;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;

import com.mysql.jdbc.Blob;
import org.bytedeco.javacpp.opencv_core.MatVector;

public class OpenCVFaceRecognizer {
	private MatVector images = null;
	private Mat labels = null;

	private int resimSayisiAl() {
		DB db = new DB();
		int resimSayisi = 0;
		String queryResimSayisi = "Select count(*) as resimsayisi from resim";
		try {
			ResultSet rs = db.baglan().executeQuery(queryResimSayisi);
			while (rs.next()) {
				resimSayisi = rs.getInt("resimsayisi");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "VeriTabani ResimSayısı Bulma Hatası: " + e, "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
		} finally {
			db.kapat();
		}
		return resimSayisi;
	}

	private void KullaniciResimleriYukle() throws Exception {
		DB db = new DB();
		if (resimSayisiAl() > 0) {
			images = new MatVector(resimSayisiAl());
			labels = new Mat(resimSayisiAl(), 1, CV_32SC1);
			IntBuffer labelsBuf = labels.createBuffer();
			int sayac = 0;

			String query = "Select * from resim";
			try {
				ResultSet rs = db.baglan().executeQuery(query);
				while (rs.next()) {

					Blob blob = (Blob) rs.getBlob("resimAdi");
					int blobLength = (int) blob.length();
					byte[] bytes = blob.getBytes(1, blobLength);
					blob.free();
					BufferedImage resimImg = ImageIO.read(new ByteArrayInputStream(bytes));
					int resID = rs.getInt("resimID");

					Mat img = bufferedImageToMat(resimImg);

					int label = resID;
					images.put(sayac, img);
					labelsBuf.put(sayac, label);
					sayac++;

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Veri tabanı resim bulma hatası: " + e, "Hata",
						JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
			}
		} else {
			throw new Exception("Veri tabanında hiç resim yok!!! "
					+ "\n Önce kullanıcı kaydı ve resim ekleme işlemleri yapınız!!!");
		}
	}

	public Mat bufferedImageToMat(BufferedImage bi) {
		Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CV_LOAD_IMAGE_GRAYSCALE);

		int r, g, b;
		UByteRawIndexer indexer = mat.createIndexer();
		for (int y = 0; y < bi.getHeight() - 3; y++) {
			for (int x = 0; x < bi.getWidth() - 3; x++) {
				int rgb = bi.getRGB(x, y);

				r = (byte) ((rgb >> 0) & 0xFF);
				g = (byte) ((rgb >> 8) & 0xFF);
				b = (byte) ((rgb >> 16) & 0xFF);

				indexer.put(y, x, 0, r);
				indexer.put(y, x, 1, g);
				indexer.put(y, x, 2, b);
			}
		}
		indexer.release();
		return mat;
	}

	public void resimBul(BufferedImage aranacakResim) {
		try {
			KullaniciResimleriYukle();
			Mat kResim = bufferedImageToMat(aranacakResim);
			FaceRecognizer faceRecognizer3 = createLBPHFaceRecognizer();

			IntPointer label = new IntPointer(1);
			DoublePointer confidence = new DoublePointer(1);
			faceRecognizer3.train(images, labels);
			faceRecognizer3.predict(kResim, label, confidence);
			int eslesenResim = label.get(0);
			int dogruluk = 100 - (int) confidence.get(0);
			if (dogruluk > 50) {
				JOptionPane.showMessageDialog(null, (kullaniciAdi(eslesenResim) + " " + "Eşleşme : " + dogruluk),
						"Eşleşme Bulundu", JOptionPane.PLAIN_MESSAGE, IconGetir.onayIcon());
			} else {
				JOptionPane.showMessageDialog(null, "Eşleşme Bulunamadı", "Eşleşme Bulunamadı",
						JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Resim Bulmada Hata", JOptionPane.PLAIN_MESSAGE,
					IconGetir.hataIcon());
		}
	}

	private String kullaniciAdi(int id) {
		DB db = new DB();
		String kullanici = "";
		String query = "select * from resim INNER join kullanici on kullanici.kullaniciID=resim.kullaniciID where  resimID="
				+ id;
		try {
			ResultSet rs = db.baglan().executeQuery(query);
			while (rs.next()) {
				kullanici = rs.getString("kullaniciAdi");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Veri Tabanı Kullanıcı adı bulma hatası: " + e, "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
		}

		return kullanici;
	}

}
