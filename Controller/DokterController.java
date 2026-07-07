/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DokterDAO;
import Model.Dokter;
import View.FormDokter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LEWI
 */
public class DokterController {
    private final FormDokter view;
    private final DokterDAO dao;

    public DokterController(FormDokter view) {
        this.view = view;
        this.dao = new DokterDAO();
    }

    public void reset() {
       view.getTxtIdDokter().setText("");
        view.getTxtNama().setText("");
        view.getTxtNip().setText("");
        
        if (view.getCmbSpesialis().getItemCount() > 0) {
            view.getCmbSpesialis().setSelectedIndex(0); 
        }
        
        view.getTxtTgl().setText("");
        view.getTxtAlamat().setText("");
        view.getTxtTelp().setText("");
        view.getTxtIdDokter().setEditable(true);
        view.getTxtIdDokter().requestFocus();
    }

    public void isiTabel() {
        List<Dokter> list = dao.getAll();
        String[] kolom = {"ID Dokter", "Nama Dokter", "NIP", "Spesialis", "Tgl Lahir", "Alamat", "No Telp"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        for (Dokter d : list) {
            Object[] baris = new Object[7];
            baris[0] = d.getIdDokter();
            baris[1] = d.getNama();
            baris[2] = d.getNip();
            baris[3] = d.getSpesialis();
            baris[4] = d.getTglLahir();
            baris[5] = d.getAlamat();
            baris[6] = d.getNoTelp();
            model.addRow(baris);
        }
        view.getjTableDokter().setModel(model);
    }

    public void isiField(int row) {
        view.getTxtIdDokter().setText(view.getjTableDokter().getValueAt(row, 0).toString());
        view.getTxtNama().setText(view.getjTableDokter().getValueAt(row, 1).toString());
        view.getTxtNip().setText(view.getjTableDokter().getValueAt(row, 2).toString());
        view.getCmbSpesialis().setSelectedItem(view.getjTableDokter().getValueAt(row, 3).toString());
        
        Object tgl = view.getjTableDokter().getValueAt(row, 4);
        view.getTxtTgl().setText(tgl != null ? tgl.toString() : "");
        
        view.getTxtAlamat().setText(view.getjTableDokter().getValueAt(row, 5).toString());
        view.getTxtTelp().setText(view.getjTableDokter().getValueAt(row, 6).toString());

        view.getTxtIdDokter().setEditable(false); 
    }

    public void simpan() {
        if (view.getTxtIdDokter().getText().trim().isEmpty() || view.getTxtNama().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID Dokter dan Nama wajib diisi!");
            return;
        }

        Dokter d = new Dokter();
        d.setIdDokter(view.getTxtIdDokter().getText());
        d.setNama(view.getTxtNama().getText());
        d.setNip(view.getTxtNip().getText());
        d.setSpesialis(view.getCmbSpesialis().getSelectedItem().toString());
        d.setTglLahir(view.getTxtTgl().getText());
        d.setAlamat(view.getTxtAlamat().getText());
        d.setNoTelp(view.getTxtTelp().getText());

        dao.insert(d);
        JOptionPane.showMessageDialog(view, "Data Dokter Berhasil Disimpan!");
        isiTabel();
        reset();
    }

    public void ubah() {
        if (view.getTxtIdDokter().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin diubah!");
            return;
        }

        Dokter d = new Dokter();
        d.setIdDokter(view.getTxtIdDokter().getText());
        d.setNama(view.getTxtNama().getText());
        d.setNip(view.getTxtNip().getText());
        d.setSpesialis(view.getCmbSpesialis().getSelectedItem().toString());
        d.setTglLahir(view.getTxtTgl().getText());
        d.setAlamat(view.getTxtAlamat().getText());
        d.setNoTelp(view.getTxtTelp().getText());

        dao.update(d);
        JOptionPane.showMessageDialog(view, "Data Dokter Berhasil Diperbarui!");
        isiTabel();
        reset();
    }

    public void hapus() {
        String id = view.getTxtIdDokter().getText();
        if (id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus data dokter ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            dao.delete(id);
            JOptionPane.showMessageDialog(view, "Data Dokter Berhasil Dihapus!");
            isiTabel();
            reset();
        }
    }
}
