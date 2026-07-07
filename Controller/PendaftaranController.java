/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.PendaftaranDAO;
import View.FormPendaftaran;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
/**
 *
 * @author LEWI
 */
public class PendaftaranController {
    private final FormPendaftaran view;
    private final PendaftaranDAO dao;

    public PendaftaranController(FormPendaftaran view) {
        this.view = view;
        this.dao = new PendaftaranDAO();
    }

    public void tampilkanData(String kataKunci, int halamanSekarang, int limitData, javax.swing.JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        int offset = (halamanSekarang - 1) * limitData;
        
        List<Map<String, String>> listData = dao.tampilkanData(kataKunci, limitData, offset);

        for (Map<String, String> data : listData) {
            model.addRow(new Object[]{
                data.get("no_daftar"),
                data.get("id_pasien"),
                data.get("id_dokter"),
                data.get("tanggal"),
                data.get("keluhan")
            });
        }
    }

    public void prosesSimpanAtauUbah(String noDaftar, java.util.Date tglDaftar, int pasienIndex, 
                                     Object pasienItem, int dokterIndex, Object dokterItem, String keluhan) {
        if (noDaftar.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No Pendaftaran tidak boleh kosong!");
            return;
        }
        if (pasienIndex == 0 || dokterIndex == 0) {
            JOptionPane.showMessageDialog(view, "Silakan pilih Pasien dan Dokter terlebih dahulu!");
            return;
        }

        try {
            // Format tanggal dari JCalendar/JDateChooser ke MySQL format (yyyy-MM-dd)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = sdf.format(tglDaftar);
            
            String idPasien = pasienItem.toString();
            String idDokter = dokterItem.toString();

            boolean sukses;
            if (dao.cekNoDaftar(noDaftar)) {
                sukses = dao.ubah(noDaftar, idPasien, idDokter, tanggal, keluhan);
                if (sukses) JOptionPane.showMessageDialog(view, "Data Pendaftaran Berhasil Diperbarui!");
            } else {
                sukses = dao.simpan(noDaftar, idPasien, idDokter, tanggal, keluhan);
                if (sukses) JOptionPane.showMessageDialog(view, "Pendaftaran Baru Berhasil Disimpan!");
            }

            if (sukses) {
                view.tampilkanData(); 
            } else {
                JOptionPane.showMessageDialog(view, "Gagal memproses data ke database.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error pada Controller: " + e.getMessage());
        }
    }

    public void prosesHapus(String noDaftar) {
        if (noDaftar.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pendaftaran pada tabel yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus transaksi pendaftaran ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean sukses = dao.hapus(noDaftar);
            if (sukses) {
                JOptionPane.showMessageDialog(view, "Data Pendaftaran Berhasil Dihapus!");
                view.tampilkanData(); // Refresh tabel
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menghapus data.");
            }
        }
    }
}