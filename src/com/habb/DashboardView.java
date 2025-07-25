/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.habb;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;

/**
 *
 * @author DELL
 */
public class DashboardView extends javax.swing.JFrame {
    DefaultTableModel model;

    /**
     * Creates new form DashboardView
     */
    public DashboardView() {
        initComponents();
    model = (DefaultTableModel) tableAnggaran.getModel();

    new javax.swing.SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            try {
                FileInputStream fis = new FileInputStream("anggaran_data.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Object[][] data = (Object[][]) ois.readObject();
                for (Object[] row : data) {
                    model.addRow(row);
                }

                ois.close();
                fis.close();

                System.out.println("Data berhasil dimuat dari file.");
            } catch (Exception ex) {
                System.out.println("Belum ada data disimpan, tabel kosong.");
            }
            return null;
        }

        @Override
        protected void done() {
            // PANGGIL UPDATE SALDO DI SINI, BUKAN DI LUAR!
            updateSaldo();
        }
    }.execute();
    }
    
  
    private void updateSaldo() {
        new javax.swing.SwingWorker<Integer, Void>() {
        @Override
        protected Integer doInBackground() throws Exception {
            int saldo = 0;
    for (int i = 0; i < model.getRowCount(); i++) {
        Object jenisObj = model.getValueAt(i, 0);
        Object jumlahObj = model.getValueAt(i, 2);

        if (jenisObj == null || jumlahObj == null) {
            continue; // skip baris yang tidak valid
        }

        String jenis = jenisObj.toString();
        int jumlah = Integer.parseInt(jumlahObj.toString());

        if (jenis.equalsIgnoreCase("Pemasukan")) {
            saldo += jumlah;
        } else {
            saldo -= jumlah;
        }
    }
    return saldo;
        }

        @Override
        protected void done() {
            try {
                int saldo = get();
                DecimalFormat df = new DecimalFormat("#,###");
                lblSaldo.setText("Total Saldo: Rp " + df.format(saldo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }.execute();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbJudul = new javax.swing.JLabel();
        lbKategori = new javax.swing.JLabel();
        txtKategori = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        scrollTabel = new javax.swing.JScrollPane();
        tableAnggaran = new javax.swing.JTable();
        comboJenis = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        lblSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbJudul.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbJudul.setText("HABB - Hitung Anggaran Belanja Bulanan");
        getContentPane().add(lbJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 290, 20));

        lbKategori.setText("Kategori :");
        getContentPane().add(lbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        txtKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKategoriActionPerformed(evt);
            }
        });
        getContentPane().add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 320, -1));

        jLabel3.setText("Jenis :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        tableAnggaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Jenis", "Kategoti", "Jumlah (Rp)", "tanggal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollTabel.setViewportView(tableAnggaran);

        getContentPane().add(scrollTabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 410, 160));

        comboJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pemasukan", "Pengeluaran", " " }));
        comboJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboJenisActionPerformed(evt);
            }
        });
        getContentPane().add(comboJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jLabel4.setText("Jumlah :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        getContentPane().add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 320, -1));

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        lblSaldo.setText("Total Saldo: Rp 0");
        getContentPane().add(lblSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKategoriActionPerformed

    private void comboJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboJenisActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String jenis = comboJenis.getSelectedItem().toString();
        String kategori = txtKategori.getText().trim();
        String jumlahText = txtJumlah.getText().trim();
        String tanggal = java.time.LocalDate.now().toString();

        if (kategori.isEmpty() || jumlahText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi semua field!");
            return;
        }

        try {
            int jumlah = Integer.parseInt(jumlahText);
            model.addRow(new Object[]{jenis, kategori, jumlah, tanggal});
            txtKategori.setText("");
            txtJumlah.setText("");
            updateSaldo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus angka!");
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tableAnggaran.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang mau di-update!");
            return;
        }   

        try {
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            model.setValueAt(comboJenis.getSelectedItem().toString(), selectedRow, 0);
            model.setValueAt(txtKategori.getText().trim(), selectedRow, 1);
            model.setValueAt(jumlah, selectedRow, 2);
            updateSaldo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus angka!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int selectedRow = tableAnggaran.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang mau dihapus!");
            return;
        }

        model.removeRow(selectedRow);
        updateSaldo();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            FileOutputStream fos = new FileOutputStream("anggaran_data.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            Object[][] data = new Object[rowCount][columnCount];

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    data[i][j] = model.getValueAt(i, j);
                }
            }

            oos.writeObject(data);
            oos.close();
            fos.close();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboJenis;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbJudul;
    private javax.swing.JLabel lbKategori;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JScrollPane scrollTabel;
    private javax.swing.JTable tableAnggaran;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKategori;
    // End of variables declaration//GEN-END:variables
}
