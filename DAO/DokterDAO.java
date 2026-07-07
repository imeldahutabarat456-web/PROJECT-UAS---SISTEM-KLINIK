/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Dokter;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LEWI
 */
public class DokterDAO {

    public List<Dokter> getAll() {
    List<Dokter> listDokter = new ArrayList<>();
    String sql = "SELECT * FROM dokter"; 
    
    try {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            Dokter d = new Dokter();
            d.setIdDokter(rs.getString("id_dokter"));
            d.setNamaDokter(rs.getString("nama_dokter"));
            d.setNip(rs.getString("nip"));
            d.setSpesialis(rs.getString("spesialis"));
            d.setTglLahir(rs.getString("tanggal_lahir"));
            d.setAlamat(rs.getString("alamat"));
            d.setNoTelp(rs.getString("nomor_telepon"));

            listDokter.add(d);
        }
    } catch (SQLException e) {
        System.out.println("Error ambil data: " + e.getMessage());
    }
    
    return listDokter;
}

    public void insert(Dokter d) {
        String sql = "INSERT INTO dokter (id_dokter, nama_dokter, nip, spesialis, tanggal_lahir, alamat, nomor_telepon) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getIdDokter());
            ps.setString(2, d.getNamaDokter());
            ps.setString(3, d.getNip());
            ps.setString(4, d.getSpesialis());
            ps.setString(5, d.getTglLahir());
            ps.setString(6, d.getAlamat());
            ps.setString(7, d.getNoTelp());
 
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Insert: " + e.getMessage());
        }
    }

    public void update(Dokter d) {
    String sql = "UPDATE dokter SET nama_dokter = ?, spesialis = ? WHERE id_dokter = ?";
        try {
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getNamaDokter());
            ps.setString(2, d.getSpesialis());
            ps.setString(3, d.getIdDokter());
            ps.setString(4, d.getSpesialis());
            ps.setString(5, d.getTglLahir());
            ps.setString(6, d.getAlamat());
            ps.setString(7, d.getNoTelp());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Update: " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM dokter WHERE id_dokter = ?";
        try {
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error Delete: " + e.getMessage());
        }
    }
}
