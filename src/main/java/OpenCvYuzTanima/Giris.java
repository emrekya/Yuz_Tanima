package OpenCvYuzTanima;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;


@SuppressWarnings({ "serial"})
public class Giris extends javax.swing.JFrame {



	public Giris() {
		initComponents();

	}

	private void initComponents() {
		Dimension butonBoyut = new Dimension(300, 300);
		Dimension formBoyut = new Dimension(700, 600);
		this.setBounds(100, 100, 700, 600);
		this.setMaximumSize(formBoyut);
		this.setMaximumSize(formBoyut);
		this.setPreferredSize(formBoyut);
		this.setTitle("Kullanıcı Tanıma V1.0");
		this.setResizable(false);
		btnKullaniciveResimEkleme = new javax.swing.JButton();
		btnKullaniciTanima = new javax.swing.JButton();
		btnAyarlar = new javax.swing.JButton();
		Container ArkaPlan = this.getContentPane();
		ArkaPlan.setBackground(new Color(85, 96, 128));
		ArkaPlan.setLayout(null);
		Image imgKaydet, imgBul, imgPro,imgAyarlar;
		try {
			imgPro = ImageIO.read(new File("resim\\proIco.png"));
			imgPro = imgPro.getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING);
			// Image programIcon=new ImageIcon(imgPro);
			this.setIconImage(imgPro);
			imgKaydet = ImageIO.read(new File("resim\\kaydet.png"));
			ImageIcon kaydetIcon = new ImageIcon(imgKaydet);
			btnKullaniciveResimEkleme.setIcon(kaydetIcon);
			imgBul = ImageIO.read(new File("resim\\bul.png"));
			ImageIcon bulIcon = new ImageIcon(imgBul);
			btnKullaniciTanima.setIcon(bulIcon);
			imgAyarlar = ImageIO.read(new File("resim\\ayarlar.png"));
			imgAyarlar = imgAyarlar.getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING);
			ImageIcon AyarIcon = new ImageIcon(imgAyarlar);
			btnAyarlar.setIcon(AyarIcon);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Program bulunduğu dizindeki resimlere ulaşılamadı" , "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
		}

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		btnKullaniciveResimEkleme.setBorder(
				javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Kullanıcı ve Resim Ekle",
						javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
						new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))));
		btnKullaniciveResimEkleme.setPreferredSize(butonBoyut);
		btnKullaniciveResimEkleme.setBounds(40, 150, 275, 275);
		btnKullaniciveResimEkleme.setBackground(new Color(85, 96, 128));
		btnKullaniciveResimEkleme.setContentAreaFilled(false);
		btnKullaniciveResimEkleme.setOpaque(true);
		// btnKullaniciveResimEkleme.setBorderPainted(false);
		btnKullaniciveResimEkleme.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnKullaniciveResimEklemeActionPerformed(evt);

			}
		});
		btnKullaniciTanima.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null,
				"Kullanıcı Tanı", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))));
		btnKullaniciTanima.setPreferredSize(butonBoyut);
		btnKullaniciTanima.setBounds(370, 150, 275, 275);
		btnKullaniciTanima.setBackground(new Color(85, 96, 128));
		btnKullaniciTanima.setContentAreaFilled(false);
		btnKullaniciTanima.setOpaque(true);
		btnKullaniciTanima.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnKullaniciTanimaActionPerformed(evt);
			}
		});

		btnAyarlar.setPreferredSize(new Dimension(32,32));
		btnAyarlar.setBounds(650, 10, 32, 32);
		btnAyarlar.setBackground(new Color(85, 96, 128));
		btnAyarlar.setContentAreaFilled(false);
		btnAyarlar.setOpaque(true);
		btnAyarlar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAyarlarActionPerformed(evt);
			}
		});
		ArkaPlan.add(btnKullaniciveResimEkleme);
		ArkaPlan.add(btnKullaniciTanima);
		ArkaPlan.add( btnAyarlar);
		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void btnKullaniciveResimEklemeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnKullaniciveResimEklemeActionPerformed
		Uygulama uyg = new Uygulama("");
		uyg.setVisible(true);
		dispose();
	}// GEN-LAST:event_btnKullaniciveResimEklemeActionPerformed

	private void btnKullaniciTanimaActionPerformed(java.awt.event.ActionEvent evt) {
		ArtikTani art = new ArtikTani();
		art.setVisible(true);
		dispose();
	}

	private void btnAyarlarActionPerformed(java.awt.event.ActionEvent evt) {
		Ayarlar ayr = new Ayarlar();
		ayr.setVisible(true);
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Giris().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnKullaniciTanima;
	private javax.swing.JButton btnKullaniciveResimEkleme;
	private javax.swing.JButton btnAyarlar;

	// End of variables declaration//GEN-END:variables
}
