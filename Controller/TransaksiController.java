/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.TransaksiDAO;
import Model.Transaksi;
import View.FormTransaksi;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LEWI
 */
public class TransaksiController {
    private final FormTransaksi view;
    private final TransaksiDAO dao;
    private List<Transaksi> listTransaksi;
    
    public TransaksiController(FormTransaksi view) {
        this.view = view;
        this.dao = new TransaksiDAO();
    }

    public void isiComboBox() {
        view.getCmbPasien().removeAllItems();
        for (String pasien : dao.getDaftarPasien()) {
            view.getCmbPasien().addItem(pasien);
        }

        view.getCmbDokter().removeAllItems();
        for (String dokter : dao.getDaftarDokter()) {
            view.getCmbDokter().addItem(dokter);
        }

        view.getCmbObat().removeAllItems();
        for (String obat : dao.getDaftarObat()) {
            view.getCmbObat().addItem(obat);
        }
    }

    public void reset() {
        view.getTxtTindakan().setText("");
        if(view.getCmbPasien().getItemCount() > 0) view.getCmbPasien().setSelectedIndex(0);
        if(view.getCmbDokter().getItemCount() > 0) view.getCmbDokter().setSelectedIndex(0);
        if(view.getCmbObat().getItemCount() > 0) view.getCmbObat().setSelectedIndex(0);
    }

    public void isiTabel() {
        listTransaksi = dao.getAll();
        String[] kolom = {"Pasien", "Dokter", "Obat", "Tindakan", "Harga"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        for (Transaksi t : listTransaksi) {
            Object[] baris = new Object[6];
            baris[0] = t.getNamaPasien();
            baris[1] = t.getNamaDokter();
            baris[2] = t.getNamaObat();
            baris[3] = t.getTindakan();
            baris[4] = t.getHargaObat();
            model.addRow(baris);
        }
        view.getjTableTransaksi().setModel(model);
    }

    public void simpan() {
        if (view.getCmbPasien().getSelectedItem() == null || view.getTxtTindakan().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }

        Transaksi t = new Transaksi();
        
        String idPasien = view.getCmbPasien().getSelectedItem().toString().split(" - ")[0];
        String idDokter = view.getCmbDokter().getSelectedItem().toString().split(" - ")[0];
        String idObat = view.getCmbObat().getSelectedItem().toString().split(" - ")[0];

        t.setIdPasien(idPasien);
        t.setIdDokter(idDokter);
        t.setIdObat(idObat);
        t.setTindakan(view.getTxtTindakan().getText());

        dao.insert(t);
        JOptionPane.showMessageDialog(view, "Transaksi Berhasil!");
        isiTabel();
        reset();
    }

    public void hapus() {
        int row = view.getjTableTransaksi().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih baris di tabel transaksi terlebih dahulu!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus transaksi ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            int idTransaksi = listTransaksi.get(row).getIdTransaksi();
            dao.delete(idTransaksi);
            JOptionPane.showMessageDialog(view, "Transaksi Dihapus!");
            isiTabel();
            reset();
        }
    }

    public void pilihData(int baris) {
        if (baris >= 0 && baris < listTransaksi.size()) {
        Transaksi t = listTransaksi.get(baris);
        view.getTxtTindakan().setText(t.getTindakan());
        }
    }

    public void halamanSebelumnya() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void halamanBerikutnya() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cariData() {
String keyword = view.getTxtCari().getText();
    listTransaksi = dao.search(keyword); 
    
    DefaultTableModel model = (DefaultTableModel) view.getjTableTransaksi().getModel();
    model.setRowCount(0);
    for (Transaksi t : listTransaksi) {
        model.addRow(new Object[]{t.getNamaPasien(), t.getNamaDokter(), t.getNamaObat(), t.getTindakan(), t.getJenisObat(), t.getHargaObat()});
    }
    
    }
}