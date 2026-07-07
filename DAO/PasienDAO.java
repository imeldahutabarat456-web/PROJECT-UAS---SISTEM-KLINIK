/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Pasien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.Koneksi;
/**
 *
 * @author LEWI
 */
public class PasienDAO {
    public void insert(Pasien p) {
        String sql = "INSERT INTO pasien( id_pasien, nik, nama, tanggal_lahir, umur, jenis_kelamin, alamat, no_telepon) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
        try {
            PreparedStatement st = Koneksi.getKoneksi().prepareStatement(sql);
            st.setString(1, p.getIdPasien());
            st.setString(2, p.getNik());
            st.setString(3, p.getNamaPasien());
            st.setString(4, p.getTglLahir());
            st.setInt(5, p.getUmur());
            st.setString(6, p.getJenisKelamin());
            st.setString(7, p.getAlamat());
            st.setString(8, p.getNoTelp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pasien> getAll() {
        List<Pasien> list = new ArrayList<>();
        String sql = "SELECT * FROM pasien";
        try {
            Statement st = Koneksi.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
              Pasien p = new Pasien();
                p.setIdPasien(rs.getString("id_pasien"));
                p.setNik(rs.getString("nik"));
                p.setNamaPasien(rs.getString("nama"));
                p.setTglLahir(rs.getString("tanggal_lahir"));
                p.setUmur(rs.getInt("umur"));
                p.setJenisKelamin(rs.getString("jenis_kelamin"));
                p.setAlamat(rs.getString("alamat"));
                p.setNoTelp(rs.getString("no_telepon"));
                list.add(p);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
public void update(Pasien p) {
        String sql = "UPDATE pasien SET nik=?, nama=?, tgl_lahir=?, umur=?, jenis_kelamin=?, alamat=?, no_telp=? WHERE id_pasien=?";
        try {
            PreparedStatement st = Koneksi.getKoneksi().prepareStatement(sql);
            st.setString(1, p.getNik());
            st.setString(2, p.getNamaPasien());
            st.setString(3, p.getTglLahir());
            st.setInt(4, p.getUmur());
            st.setString(5, p.getJenisKelamin());
            st.setString(6, p.getAlamat());
            st.setString(7, p.getNoTelp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM pasien WHERE id_pasien=?";
        try {
            PreparedStatement st = Koneksi.getKoneksi().prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
