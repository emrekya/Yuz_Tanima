package OpenCvYuzTanima;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Ayarlar extends javax.swing.JFrame {
	private JTextArea lblTanitim;
	private JButton btnVeriTabaniOlustur;

	public Ayarlar() {
		initComponents();
	}

	private void initComponents() {
		Dimension formBoyut = new Dimension(500, 300);
		this.setMaximumSize(formBoyut);
		this.setMaximumSize(formBoyut);
		this.setPreferredSize(formBoyut);
		this.setSize(formBoyut);
		this.setLayout(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setTitle("Kullanıcı Tanıma V1.0 - Ayarlar");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		Container form = this.getContentPane();
		form.setBackground(new Color(85, 96, 128));
		try {
			Image imgPro;
			imgPro = ImageIO.read(new File("resim\\proIco.png"));
			imgPro = imgPro.getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING);
			this.setIconImage(imgPro);
		} catch (Exception e) {
			// Resim Yüklenemedi
		}

		lblTanitim = new javax.swing.JTextArea();
		lblTanitim.setSize(450, 200);
		lblTanitim.setLocation(10, 10);
		lblTanitim.setBackground(new Color(85, 96, 128));
		lblTanitim.setForeground(Color.WHITE);
		lblTanitim.setFont(new java.awt.Font("Tahoma", 0, 11));
		lblTanitim.setWrapStyleWord(true);
		lblTanitim.setLineWrap(true);

		lblTanitim.setText(
				"\tBu program OpenCV 3.3.1 sürümü ve JavaCV 1.3.3 Platform kütüphaneleri kullanarak yazılmıştır."
						+ "Mysql veritabanına kullanıcı resimlerini çekerek kaydedip daha sonra yüz taraması yapıp kim olduğunu bulmak "
						+ "amaçlanmıştır.\n\tProgramın çalışabilmesi için öncelikler Mysql Serverin kurulu olması gerekmektedir. "
						+ "Bilgisayarınızda yoksa \n \thttps://dev.mysql.com/downloads/ \n "
						+ "mysql in kendi sitesinden ya da \n" + "\thttps://www.apachefriends.org/tr/index.html \n"
						+ "adresinden xampp kurulumu yaparak bilgisayarınıza kurabilirsiniz. Kurulumu bitirdikten sonra default "
						+ "giriş kullanıcı adı ve şifresini değiştirmemeniz gerekmektedir. Kullanıcı adı: root olup şifresi olmamalıdır. "
						+ "Bütün bunları yaptıktan sonra aşağıdaki butona tıklayarak veri tabanını oluşmasını sağlayıp programı kullanmaya"
						+ " başlayabilirsiniz.");
		form.add(lblTanitim);

		btnVeriTabaniOlustur = new javax.swing.JButton();
		btnVeriTabaniOlustur.setSize(200, 30);
		btnVeriTabaniOlustur.setLocation(135, 210);
		btnVeriTabaniOlustur.setText("Veri Tabanı Oluştur");
		btnVeriTabaniOlustur.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnVeriTabaniOlusturActionPerformed(evt);

			}
		});
		form.add(btnVeriTabaniOlustur);
		pack();
		setLocationRelativeTo(null);
	}

	private void btnVeriTabaniOlusturActionPerformed(ActionEvent evt) {
		this.setAlwaysOnTop(false);
		String query1 = "CREATE DATABASE IF NOT EXISTS `kullanicikontrol` DEFAULT CHARACTER SET utf8 COLLATE utf8_turkish_ci;";
		String query2 = "CREATE TABLE IF NOT EXISTS `kullanici` ( `kullaniciID` int(11) NOT NULL AUTO_INCREMENT,  `kullaniciAdi` varchar(50) COLLATE utf8_turkish_ci NOT NULL,  PRIMARY KEY (`kullaniciID`)) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci";
		String query3 = "CREATE TABLE IF NOT EXISTS `resim` (  `resimID` int(11) NOT NULL AUTO_INCREMENT,  `resimAdi` longblob NOT NULL,  `kullaniciID` int(11) NOT NULL,  `resimSira` tinyint(1) NOT NULL,  PRIMARY KEY (`resimID`)) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;";

		DB db = new DB("", "root", "");
		try {
			int vto = db.baglan().executeUpdate(query1);
			if (vto > 0) {
				JOptionPane.showMessageDialog(null, "Veri tabanı Başarıyla Oluşturuldu", "Onay",
						JOptionPane.PLAIN_MESSAGE, IconGetir.onayIcon());
			} else {
				JOptionPane.showMessageDialog(null, "Veri tabanı daha önceden oluşturulmuş", "Hata",
						JOptionPane.PLAIN_MESSAGE, IconGetir.hataIcon());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Veri tabanı oluşturma hatası", "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hataIcon());
		} finally {
			db.kapat();
		}
		db = new DB("kullanicikontrol", "root", "");
		try {
			db.baglan().executeUpdate(query2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Kullanıcı tablosu oluşturma hatası", "Hata", JOptionPane.PLAIN_MESSAGE,
					IconGetir.hataIcon());

		} finally {
			db.kapat();
		}
		db = new DB("kullanicikontrol", "root", "");
		try {
			db.baglan().executeUpdate(query3);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Resim tablosu oluşturma hatası", "Hata", JOptionPane.PLAIN_MESSAGE,
					IconGetir.hataIcon());
		} finally {
			db.kapat();
		}
		this.setAlwaysOnTop(true);
	}

}