package Controller;

import DAO.ObatDAO;
import Model.Obat;
import View.FormObat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ObatController {
    private final FormObat view;
    private final ObatDAO dao;

    public ObatController(FormObat view) {
        this.view = view;
        this.dao = new ObatDAO();
    }

    public void reset() {
        view.getTxtIdObat().setText("");
        view.getTxtNamaObat().setText("");
        
        if (view.getCmbJenisObat().getItemCount() > 0) {
            view.getCmbJenisObat().setSelectedIndex(0); 
        }
        
        view.getTxtHarga().setText("");
        view.getTxtIdObat().setEditable(true);
        view.getTxtIdObat().requestFocus();
    }

    public void isiTabel() {
        List<Obat> list = dao.getAll();
        String[] kolom = {"ID Obat", "Nama Obat", "Jenis Obat", "Harga"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        for (Obat o : list) {
            Object[] baris = new Object[4];
            baris[0] = o.getIdObat();
            baris[1] = o.getNamaObat();
            baris[2] = o.getJenisObat();
            baris[3] = o.getHarga();
            model.addRow(baris);
        }
        view.getjTableObat().setModel(model);
    }

    public void isiField(int row) {
    view.getTxtIdObat().setText(view.getjTableObat().getValueAt(row, 0) != null ? view.getjTableObat().getValueAt(row, 0).toString() : "");
    view.getTxtNamaObat().setText(view.getjTableObat().getValueAt(row, 1) != null ? view.getjTableObat().getValueAt(row, 1).toString() : "");
    view.getCmbJenisObat().setSelectedItem(view.getjTableObat().getValueAt(row, 2) != null ? view.getjTableObat().getValueAt(row, 2).toString() : "");
    view.getTxtHarga().setText(view.getjTableObat().getValueAt(row, 3) != null ? view.getjTableObat().getValueAt(row, 3).toString() : "");
 
}

    public void simpan() {
        if (view.getTxtIdObat().getText().trim().isEmpty() || view.getTxtNamaObat().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID Obat dan Nama Obat wajib diisi!");
            return;
        }

        Obat o = new Obat();
        o.setIdObat(view.getTxtIdObat().getText());
        o.setNamaObat(view.getTxtNamaObat().getText());
        o.setJenisObat(view.getCmbJenisObat().getSelectedItem().toString());
        
        try {
            o.setHarga(Double.parseDouble(view.getTxtHarga().getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga harus berupa angka!");
            return;
        }

        dao.insert(o);
        JOptionPane.showMessageDialog(view, "Data Obat Berhasil Disimpan!");
        isiTabel();
        reset();
    }

    public void ubah() {
        if (view.getTxtIdObat().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin diubah!");
            return;
        }

        Obat o = new Obat();
        o.setIdObat(view.getTxtIdObat().getText());
        o.setNamaObat(view.getTxtNamaObat().getText());
        o.setJenisObat(view.getCmbJenisObat().getSelectedItem().toString());
        
        try {
            o.setHarga(Double.parseDouble(view.getTxtHarga().getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Harga harus berupa angka!");
            return;
        }

        dao.update(o);
        JOptionPane.showMessageDialog(view, "Data Obat Berhasil Diperbarui!");
        isiTabel();
        reset();
    }

    public void hapus() {
        String id = view.getTxtIdObat().getText();
        if (id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data pada tabel terlebih dahulu yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus data obat ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            dao.delete(id);
            JOptionPane.showMessageDialog(view, "Data Obat Berhasil Dihapus!");
            isiTabel();
            reset();
        }
    }
}