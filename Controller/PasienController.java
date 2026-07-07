/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PasienDAO;
import Model.Pasien;
import View.FormPasien; 
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LEWI
 */
public class PasienController {
    private final FormPasien view;
    private final PasienDAO dao;
    
    public PasienController(FormPasien view) { 
    this.view = view; 
    this.dao = new PasienDAO();
}

    
    public void reset() {
        view.getTxtId().setText("");
        view.getTxtNik().setText("");
        view.getTxtNama().setText("");
        view.getTxtTgl().setText(""); 
        view.getTxtUmur().setText("");
        view.getCmbJk().setSelectedIndex(0);
        view.getTxtAlamat().setText("");
        view.getTxtTelp().setText("");
        view.getTxtId().setEditable(true);
        view.getTxtId().requestFocus();
    }
    
    public void isiTabel() {
        List<Pasien> list = dao.getAll();
        String[] kolom = {"ID Pasien", "NIK", "Nama", "Tgl Lahir", "Umur", "JK", "Alamat", "No Telp"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);
        
        for (Pasien p : list) {
            Object[] baris = new Object[8];
            baris[0] = p.getIdPasien();
            baris[1] = p.getNik();
            baris[2] = p.getNamaPasien();
            baris[3] = p.getTglLahir();
            baris[4] = String.valueOf(p.getUmur());
            baris[5] = p.getJenisKelamin();
            baris[6] = p.getAlamat();
            baris[7] = p.getNoTelp();
            model.addRow(baris);
        }
        view.getjTablePasien().setModel(model);
    }
    
    public void isiField(int row) {
        view.getTxtId().setText(view.getjTablePasien().getValueAt(row, 0).toString());
        view.getTxtNik().setText(view.getjTablePasien().getValueAt(row, 1).toString());
        view.getTxtNama().setText(view.getjTablePasien().getValueAt(row, 2).toString());
        
        Object tgl = view.getjTablePasien().getValueAt(row, 3);
        view.getTxtTgl().setText(tgl != null ? tgl.toString() : "");
        
        view.getTxtUmur().setText(view.getjTablePasien().getValueAt(row, 4).toString());
        view.getCmbJk().setSelectedItem(view.getjTablePasien().getValueAt(row, 5).toString());
        view.getTxtAlamat().setText(view.getjTablePasien().getValueAt(row, 6).toString());
        view.getTxtTelp().setText(view.getjTablePasien().getValueAt(row, 7).toString());
        
        view.getTxtId().setEditable(false);
    }

    public void simpan() {
        if (view.getTxtId().getText().trim().isEmpty() || view.getTxtNama().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID Pasien dan Nama wajib diisi!");
            return;
        }

        Pasien p = new Pasien();
        p.setIdPasien(view.getTxtId().getText());
        p.setNik(view.getTxtNik().getText());
        p.setNamaPasien(view.getTxtNama().getText());
        p.setTglLahir(view.getTxtTgl().getText()); 
        try {
            p.setUmur(Integer.parseInt(view.getTxtUmur().getText()));
        } catch (NumberFormatException e) {
            p.setUmur(0);
        }
        p.setJenisKelamin(view.getCmbJk().getSelectedItem().toString());
        p.setAlamat(view.getTxtAlamat().getText());
        p.setNoTelp(view.getTxtTelp().getText());
        
        String tglInput = view.getTxtTgl().getText(); 
        String[] bagianTgl = tglInput.split("-"); 
        String tglDatabase = bagianTgl[2] + "-" + bagianTgl[1] + "-" + bagianTgl[0]; // Menjadi YYYY-MM-DD

p.setTglLahir(tglDatabase);
        dao.insert(p);
        JOptionPane.showMessageDialog(view, "Data Pasien Berhasil Disimpan!");
        isiTabel();
        reset();
    }

    public void ubah() {
        if (view.getTxtId().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin diubah!");
            return;
        }

        Pasien p = new Pasien();
        p.setIdPasien(view.getTxtId().getText());
        p.setNik(view.getTxtNik().getText());
        p.setNamaPasien(view.getTxtNama().getText());
        p.setTglLahir(view.getTxtTgl().getText());
        try {
            p.setUmur(Integer.parseInt(view.getTxtUmur().getText()));
        } catch (NumberFormatException e) {
            p.setUmur(0);
        }
        p.setJenisKelamin(view.getCmbJk().getSelectedItem().toString());
        p.setAlamat(view.getTxtAlamat().getText());
        p.setNoTelp(view.getTxtTelp().getText());

        dao.update(p);
        JOptionPane.showMessageDialog(view, "Data Pasien Berhasil Diperbarui!");
        isiTabel();
        reset();
    }

    public void hapus() {
        String id = view.getTxtId().getText();
        if (id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus data pasien ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            dao.delete(id);
            JOptionPane.showMessageDialog(view, "Data Pasien Berhasil Dihapus!");
            isiTabel();
            reset();
        }
    }
}
