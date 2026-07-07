/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Transaksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEWI
 */
public class TransaksiDAO {
    private Connection connection;
    
    public TransaksiDAO() {
        try {
            connection = config.Koneksi.getKoneksi();
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public List<Transaksi> getAll() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.idtransaksi, p.nama, d.nama_dokter, o.nama_obat, t.tindakan, o.harga " +
                 "FROM transaksi t " +
                 "LEFT JOIN pasien p ON t.pasien = p.id_pasien " +
                 "LEFT JOIN dokter d ON t.dokter = d.id_dokter " +
                 "LEFT JOIN obat o ON t.obat = o.id_obat";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Transaksi t = new Transaksi();
                t.setIdTransaksi(rs.getInt("idtransaksi"));
                t.setNamaPasien(rs.getString("nama"));
                t.setNamaDokter(rs.getString("nama_dokter"));
                t.setNamaObat(rs.getString("nama_obat"));
                t.setTindakan(rs.getString("tindakan"));
                t.setHargaObat(rs.getDouble("harga"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error getAll: " + e.getMessage());
        }
        return list;
    }

    public void insert(Transaksi t) {
        String sql = "INSERT INTO transaksi (pasien, dokter,obat, tindakan) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getIdPasien());
            st.setString(2, t.getIdDokter());
            st.setString(3, t.getIdObat());
            st.setString(4, t.getTindakan());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insert: " + e.getMessage());
        }
    }

    public void delete(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE idtransaksi = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idTransaksi);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error delete: " + e.getMessage());
        }
    }

    public List<String> getDaftarPasien() {
        List<String> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_pasien, nama FROM pasien");
            while(rs.next()) {
                list.add(rs.getString("id_pasien") + " - " + rs.getString("nama"));
            }
        } catch (Exception e) {}
        return list;
    }

    public List<String> getDaftarDokter() {
        List<String> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_dokter, nama_dokter FROM dokter");
            while(rs.next()) {
                list.add(rs.getString("id_dokter") + " - " + rs.getString("nama_dokter"));
            }
        } catch (Exception e) {}
        return list;
    }

    public List<String> getDaftarObat() {
        List<String> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_obat, nama_obat FROM obat");
            while(rs.next()) {
                list.add(rs.getString("id_obat") + " - " + rs.getString("nama_obat"));
            }
        } catch (Exception e) {}
        return list;
    }

    public List<Transaksi> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
