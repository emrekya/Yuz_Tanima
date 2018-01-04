package OpenCvYuzTanima;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Blob;

@SuppressWarnings("serial")
public class Uygulama extends javax.swing.JFrame {
	HashMap<String, String> hm = new HashMap<>();
	String kullanici = "";

	public Uygulama(String kullanici) {
		initComponents();
		kullaniciListele();
		kullaniciResimleri();
		this.kullanici = kullanici;
		String kullaniciAdi = hm.get(kullanici);
		lstKullanicilar.setSelectedValue(kullaniciAdi, true);
	}

	private void kullaniciResimleri() {
		ImageIcon icon = null;
		try {
			Image imgKaydet = ImageIO.read(new File("resim\\kaydet.png"));
			imgKaydet = imgKaydet.getScaledInstance(145, 145, Image.SCALE_AREA_AVERAGING);
			icon = new ImageIcon(imgKaydet);
		} catch (IOException e) {
			// resimyok
		}

		Resim1.setIcon(icon);
		Resim2.setIcon(icon);
		Resim3.setIcon(icon);
		Resim4.setIcon(icon);
		Resim5.setIcon(icon);
		Resim6.setIcon(icon);
		Resim7.setIcon(icon);
		Resim8.setIcon(icon);
		Resim9.setIcon(icon);
		Resim1.setToolTipText("kaydet");
		Resim2.setToolTipText("kaydet");
		Resim3.setToolTipText("kaydet");
		Resim4.setToolTipText("kaydet");
		Resim5.setToolTipText("kaydet");
		Resim6.setToolTipText("kaydet");
		Resim7.setToolTipText("kaydet");
		Resim8.setToolTipText("kaydet");
		Resim9.setToolTipText("kaydet");
		String query = "Select * from resim where kullaniciID='" + kullanici + "'";
		DB db = new DB();
		try {
			ResultSet rs = db.baglan().executeQuery(query);
			while (rs.next()) {

				Blob blob = (Blob) rs.getBlob("resimAdi");
				int blobLength = (int) blob.length();

				byte[] bytes = blob.getBytes(1, blobLength);
				blob.free();

				BufferedImage resimImg = ImageIO.read(new ByteArrayInputStream(bytes));

				int resimSira = rs.getInt("resimSira");

				Image img2 = resimImg.getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING);
				icon = new ImageIcon(img2);
				switch (resimSira) {
				case 1:
					Resim1.setIcon(icon);
					Resim1.setToolTipText("update");
					break;
				case 2:
					Resim2.setIcon(icon);
					Resim2.setToolTipText("update");
					break;
				case 3:
					Resim3.setIcon(icon);
					Resim3.setToolTipText("update");
					break;
				case 4:
					Resim4.setIcon(icon);
					Resim4.setToolTipText("update");
					break;
				case 5:
					Resim5.setIcon(icon);
					Resim5.setToolTipText("update");
					break;
				case 6:
					Resim6.setIcon(icon);
					Resim6.setToolTipText("update");
					break;
				case 7:
					Resim7.setIcon(icon);
					Resim7.setToolTipText("update");
					break;
				case 8:
					Resim8.setIcon(icon);
					Resim8.setToolTipText("update");
					break;
				case 9:
					Resim9.setIcon(icon);
					Resim9.setToolTipText("update");
					break;

				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "VeriTabani Resim Bulma Hatası: " + e, "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
		} finally {
			db.kapat();
		}

	}

	private void kullaniciListele() {
		DefaultListModel<String> dlm = new DefaultListModel<String>();

		DB db = new DB();

		String query = "Select * from kullanici";
		try {

			ResultSet rs = db.baglan().executeQuery(query);
			while (rs.next()) {
				String kulID = rs.getString("kullaniciID");
				String kulAdi = rs.getString("kullaniciAdi");
				hm.put(kulID, kulAdi);
				// Kullanicilar.add(hm);

				dlm.addElement(kulAdi);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Veri Tabanı Kullanıcı Listeleme Hatası: " + e, "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hata2Icon());
		} finally {
			db.kapat();
		}

		lstKullanicilar.setModel(dlm);
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		lstKullanicilar = new javax.swing.JList<>();
		Resim1 = new javax.swing.JLabel();
		Resim2 = new javax.swing.JLabel();
		Resim3 = new javax.swing.JLabel();
		Resim6 = new javax.swing.JLabel();
		Resim4 = new javax.swing.JLabel();
		Resim5 = new javax.swing.JLabel();
		Resim7 = new javax.swing.JLabel();
		Resim8 = new javax.swing.JLabel();
		Resim9 = new javax.swing.JLabel();
		lblKullaniciBilgi = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		txtKullanici = new javax.swing.JTextField();
		btnYeniKullanici = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Kullanıcı Tanıma V1.0 - Kullanıcı ve Resim Ekleme");
		try {
			Image imgPro;
			imgPro = ImageIO.read(new File("resim\\proIco.png"));
			imgPro = imgPro.getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING);
			this.setIconImage(imgPro);
		} catch (Exception e) {
			// Resim Yüklenemedi
		}
		lstKullanicilar.setModel(new javax.swing.AbstractListModel<String>() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		lstKullanicilar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				lstKullanicilarValueChanged(evt);
			}
		});
		jScrollPane1.setViewportView(lstKullanicilar);

		Resim1.setToolTipText("1");
		Resim1.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 1",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim1MouseClicked(evt);
			}
		});

		Resim2.setToolTipText("2");
		Resim2.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 2",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim2MouseClicked(evt);
			}
		});

		Resim3.setToolTipText("3");
		Resim3.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 3",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim3MouseClicked(evt);
			}
		});

		Resim6.setToolTipText("6");
		Resim6.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 6",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim6MouseClicked(evt);
			}
		});

		Resim4.setToolTipText("4");
		Resim4.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 4",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim4MouseClicked(evt);
			}
		});

		Resim5.setToolTipText("5");
		Resim5.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 5",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim5MouseClicked(evt);
			}
		});

		Resim7.setToolTipText("7");
		Resim7.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 7",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim7MouseClicked(evt);
			}
		});

		Resim8.setToolTipText("8");
		Resim8.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 8",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim8MouseClicked(evt);
			}
		});

		Resim9.setToolTipText("9");
		Resim9.setBorder(javax.swing.BorderFactory.createTitledBorder(new TitledBorder(null, "Resim 9",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))));
		Resim9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Resim9MouseClicked(evt);
			}
		});

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Yeni Kullanıcı Ekleme",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.CENTER,
				new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255)));
		jPanel1.setBackground(new Color(85, 96, 128));

		btnYeniKullanici.setText("Kaydet");
		btnYeniKullanici.setOpaque(true);
		btnYeniKullanici.setBackground(new java.awt.Color(255, 255, 255));
		// btnYeniKullanici.setForeground(new java.awt.Color(255, 255, 255));
		btnYeniKullanici.setBorderPainted(false);

		btnYeniKullanici.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnYeniKullaniciActionPerformed(evt);
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		Dimension formBoyut = new Dimension(700, 600);
		this.setBounds(100, 100, 700, 600);
		this.setMaximumSize(formBoyut);
		this.setMaximumSize(formBoyut);
		this.setPreferredSize(formBoyut);
		this.setBackground(new Color(85, 96, 128));
		this.setResizable(false);
		Container form = this.getContentPane();
		form.setBackground(new Color(85, 96, 128));
		form.setLayout(null);
		form.add(jPanel1);
		jPanel1.setLayout(null);
		jPanel1.setBounds(0, 0, 190, 100);
		txtKullanici.setBounds(20, 25, 150, 25);
		btnYeniKullanici.setBounds(20, 60, 150, 25);
		lstKullanicilar.setBounds(210, 110, 190, 500);
		jPanel1.add(txtKullanici);
		jPanel1.add(btnYeniKullanici);
		form.add(jScrollPane1);
		jScrollPane1.setBounds(1, 101, 190, 458);
		form.add(Resim1);
		form.add(Resim2);
		form.add(Resim3);
		form.add(Resim4);
		form.add(Resim5);
		form.add(Resim6);
		form.add(Resim7);
		form.add(Resim8);
		form.add(Resim9);
		form.add(lblKullaniciBilgi);
		Resim1.setBounds(195, 60, 160, 160);
		Resim2.setBounds(358, 60, 160, 160);
		Resim3.setBounds(521, 60, 160, 160);
		Resim4.setBounds(195, 222, 160, 160);
		Resim5.setBounds(358, 222, 160, 160);
		Resim6.setBounds(521, 222, 160, 160);
		Resim7.setBounds(195, 384, 160, 160);
		Resim8.setBounds(358, 384, 160, 160);
		Resim9.setBounds(521, 384, 160, 160);
		lblKullaniciBilgi.setBounds(195, 0, 485, 55);
		lblKullaniciBilgi.setBackground(new Color(85, 96, 128));
		lblKullaniciBilgi.setForeground(Color.WHITE);
		lblKullaniciBilgi.setFont(new Font("Tohoma", Font.BOLD, 12));
		lblKullaniciBilgi.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullaniciBilgi.setVerticalAlignment(SwingConstants.CENTER);
		lblKullaniciBilgi.setOpaque(true);
		lblKullaniciBilgi.setText("Kullanıcı seçmediniz!!!");
		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void btnYeniKullaniciActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnYeniKullaniciActionPerformed
		HataAyiklama ht = new HataAyiklama();
		if (ht.metinMi(txtKullanici.getText())) {
			DB db = new DB();
			String kullaniciAdi = ht.adSoyadHarfDuzenle(txtKullanici.getText());

			String query = "insert into kullanici values(null,'" + kullaniciAdi + "')";
			try {
				int ekle = db.baglan().executeUpdate(query);
				if (ekle > 0) {
					JOptionPane.showMessageDialog(null,
							"Yeni kayıt eklendi.\nAşağıdaki listeden seçerek resim ekleyebilirsiniz.", "Onay",
							JOptionPane.PLAIN_MESSAGE, IconGetir.onayIcon());
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Veri tabanı kayıt hatası", "Hata", JOptionPane.PLAIN_MESSAGE,
						IconGetir.hata2Icon());
			} finally {
				db.kapat();
			}

			kullaniciListele();
		} else {
			JOptionPane.showMessageDialog(this, "Giridiğiniz isim, isim kurallarına uymamaktadır!!!", "Hata",
					JOptionPane.PLAIN_MESSAGE, IconGetir.hataIcon());
		}

	}

	private void resimEkle(String resimSira, String islem) {
		if (lstKullanicilar.getSelectedIndex() >= 0) {
			kullanici = "" + lstKullanicilar.getSelectedValue();
			for (Entry<String, String> entry : hm.entrySet()) {

				String kulID = entry.getKey();
				String kulAdi = entry.getValue();
				if (kulAdi.equalsIgnoreCase(kullanici)) {
try{
					new YuzTani(kulID, Integer.valueOf(resimSira), islem).setVisible(true);
}catch (Exception e) {
	JOptionPane.showMessageDialog(this, e);
}
					dispose();
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Önce Kullanıcı Seçiniz", "Uyarı", JOptionPane.PLAIN_MESSAGE,
					IconGetir.hataIcon());
		}

	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		Giris giris = new Giris();
		giris.setVisible(true);
	}

	private void Resim1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim1MouseClicked
		if (Resim1.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("1", "update");
		} else {
			resimEkle("1", "kaydet");
		}

	}// GEN-LAST:event_Resim1MouseClicked

	private void Resim2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim2MouseClicked
		if (Resim2.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("2", "update");
		} else {
			resimEkle("2", "kaydet");
		}
	}// GEN-LAST:event_Resim2MouseClicked

	private void Resim3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim3MouseClicked
		if (Resim3.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("3", "update");
		} else {
			resimEkle("3", "kaydet");
		}
	}// GEN-LAST:event_Resim3MouseClicked

	private void Resim4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim4MouseClicked
		if (Resim4.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("4", "update");
		} else {
			resimEkle("4", "kaydet");
		}
	}// GEN-LAST:event_Resim4MouseClicked

	private void Resim5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim5MouseClicked
		if (Resim5.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("5", "update");
		} else {
			resimEkle("5", "kaydet");
		}
	}// GEN-LAST:event_Resim5MouseClicked

	private void Resim6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim6MouseClicked
		if (Resim6.getToolTipText().equalsIgnoreCase("update")) {

			resimEkle("6", "update");
		} else {
			resimEkle("6", "kaydet");
		}
	}// GEN-LAST:event_Resim6MouseClicked

	private void Resim7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim7MouseClicked
		if (Resim7.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("7", "update");
		} else {
			resimEkle("7", "kaydet");
		}
	}// GEN-LAST:event_Resim7MouseClicked

	private void Resim8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim8MouseClicked
		if (Resim8.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("8", "update");
		} else {
			resimEkle("8", "kaydet");
		}
	}// GEN-LAST:event_Resim8MouseClicked

	private void Resim9MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_Resim9MouseClicked
		if (Resim9.getToolTipText().equalsIgnoreCase("update")) {
			resimEkle("9", "update");
		} else {
			resimEkle("9", "kaydet");
		}
	}// GEN-LAST:event_Resim9MouseClicked

	private void lstKullanicilarValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_lstKullanicilarValueChanged
		if (lstKullanicilar.getSelectedIndex() >= 0) {
			kullanici = "" + lstKullanicilar.getSelectedValue();
			for (Entry<String, String> entry : hm.entrySet()) {
				String kulID = entry.getKey();
				String kulAdi = entry.getValue();
				if (kulAdi.equalsIgnoreCase(kullanici)) {
					kullanici = "" + kulID;
					lblKullaniciBilgi.setText(lstKullanicilar.getSelectedValue() + " kullanıcısının resimleri");
					kullaniciResimleri();
				}
			}
		}
	}// GEN-LAST:event_lstKullanicilarValueChanged

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel Resim1;
	private javax.swing.JLabel Resim2;
	private javax.swing.JLabel Resim3;
	private javax.swing.JLabel Resim4;
	private javax.swing.JLabel Resim5;
	private javax.swing.JLabel Resim6;
	private javax.swing.JLabel Resim7;
	private javax.swing.JLabel Resim8;
	private javax.swing.JLabel Resim9;
	private javax.swing.JLabel lblKullaniciBilgi;
	private javax.swing.JButton btnYeniKullanici;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList<String> lstKullanicilar;
	private javax.swing.JTextField txtKullanici;
	// End of variables declaration//GEN-END:variables
}
