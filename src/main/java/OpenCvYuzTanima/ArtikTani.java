package OpenCvYuzTanima;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.opencv.core.Core;
//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

@SuppressWarnings("serial")
public class ArtikTani extends javax.swing.JFrame {

	VideoCapture videoDevice;
	int x1, x2, y1, y2;
	private MatOfRect faces;

	public ArtikTani() {

		initComponents();
		// Sistem Kütüphanesi yükleniyor
		//String[] path = Giris.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("YuzTanima");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Runnable rn = () -> {
			CascadeClassifier cascadeFaceClassifier = new CascadeClassifier(
					"lib\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_default.xml");
			videoDevice = new VideoCapture();

			videoDevice.open(0);

			while (videoDevice.isOpened()) {
				if (videoDevice.isOpened()) {
					Mat frameCapture = new Mat();

					try {
						videoDevice.read(frameCapture);

						faces = new MatOfRect();
						cascadeFaceClassifier.detectMultiScale(frameCapture, faces);

						// Yakalanan çerçeve varsa içerisinde dön ve yüzün
						// boyutları ölçüsünde bir kare çiz
						for (Rect rect : faces.toArray()) {
							x1 = rect.x + 15;
							y1 = rect.y + 10;
							x2 = rect.x + rect.height;
							y2 = rect.y + rect.width + 10;
							// Imgproc.putText(frameCapture, "Face", new
							// Point(rect.x, rect.y - 5), 1, 2,
							// new Scalar(0, 0, 255));
							Imgproc.rectangle(frameCapture, new Point(rect.x, rect.y),
									new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 100, 0), 3);

						}
						PushImage(ConvertMat2Image(frameCapture));
					} catch (Exception e) {
						// Kamera Kapalı hatası Uyarı vermeye gerek yok :D
					}
				} else {
					JOptionPane.showMessageDialog(this, "Kameraya Bağlanılamadı", "Uyarı", JOptionPane.PLAIN_MESSAGE,
							IconGetir.hataIcon());
					break;
				}
			}
		};
		new Thread(rn).start();
	}

	private BufferedImage ConvertMat2Image(Mat kameraVerisi) {
		MatOfByte byteMatVerisi = new MatOfByte();
		// Ara belleðe verilen formatta görüntü kodlar
		Imgcodecs.imencode(".jpg", kameraVerisi, byteMatVerisi);
		// Mat nesnesinin toArray() metodu elemanlarý byte dizisine çevirir
		byte[] byteArray = byteMatVerisi.toArray();
		BufferedImage goruntu = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			goruntu = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return goruntu;
	}

	public void PushImage(Image img) {
		img = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_AREA_AVERAGING);
		ImageIcon icon = new ImageIcon(img);
		lbl.setIcon(icon);
	}

	private void initComponents() {

		lbl = new javax.swing.JLabel();

		lblTani = new javax.swing.JLabel();

		lbl.setSize(640, 480);
		lbl.setLocation(0, 0);

		lblTani.setSize(128, 128);
		lblTani.setLocation(500, 320);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		this.setTitle("Kullanıcı Tanıma V1.0 - Kullanıcı Tanıma");
		try {
			Image imgPro;
			imgPro = ImageIO.read(new File("resim\\proIco.png"));
			imgPro = imgPro.getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING);
			this.setIconImage(imgPro);
			Image imgResCek;
			imgResCek = ImageIO.read(new File("resim\\tani.png"));
			imgResCek = imgResCek.getScaledInstance(120, 120, Image.SCALE_AREA_AVERAGING);
			ImageIcon ResCekICon = new ImageIcon(imgResCek);
			lblTani.setIcon(ResCekICon);
		} catch (Exception e) {
			// Resim Yüklenemedi
		}

		Dimension formBoyut = new Dimension(640, 480);
		this.setMaximumSize(formBoyut);
		this.setMaximumSize(formBoyut);
		this.setPreferredSize(formBoyut);
		this.setSize(formBoyut);
		this.setLayout(null);
		this.setResizable(false);
		Container form = this.getContentPane();
		form.add(lbl);
		// form.add(btnResimCek);

		lbl.add(lblTani);
		lblTani.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblCekimMouseClicked(evt);
			}
		});
		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		videoDevice.release();
		Giris giris = new Giris();
		giris.setVisible(true);
	}

	private void lblCekimMouseClicked(java.awt.event.MouseEvent evt) {
		Mat kameraResim = new Mat();
		videoDevice.read(kameraResim);

		Rect dikdortgen = new Rect(new Point(x1, y1), new Point(x2, y2));
		Mat yeniGoruntu = new Mat(kameraResim, dikdortgen);
		Mat boyutAyarla = new Mat();
		Imgproc.resize(yeniGoruntu, boyutAyarla, new Size(160, 160));
		BufferedImage aranacakResim = ConvertMat2Image(boyutAyarla);

		OpenCVFaceRecognizer openFace = new OpenCVFaceRecognizer();
		openFace.resimBul(aranacakResim);

	}

	// Variables declaration - do not modify
	private javax.swing.JLabel lblTani;
	private javax.swing.JLabel lbl;
	// End of variables declaration
}
