/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Pendaftaran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author LEWI
 */
public class PendaftaranDAO {

    private String tanggal;
    public List<Map<String, String>> tampilkanData(String kataKunci, int limit, int offset) {
        List<Map<String, String>> listPendaftaran = new ArrayList<>();
        String sql;
        
        if (kataKunci.isEmpty()) {
            sql = "SELECT * FROM pendaftaran LIMIT ? OFFSET ?";
        } else {
            sql = "SELECT * FROM pendaftaran WHERE no_daftar LIKE ? OR id_pasien LIKE ? OR id_dokter LIKE ? OR keluhan LIKE ? LIMIT ? OFFSET ?";
        }

        try (Connection conn = config.Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (kataKunci.isEmpty()) {
                ps.setInt(1, limit);
                ps.setInt(2, offset);
            } else {
                String queryLike = "%" + kataKunci + "%";
                ps.setString(1, queryLike);
                ps.setString(2, queryLike);
                ps.setString(3, queryLike);
                ps.setString(4, queryLike);
                ps.setString(5, queryLike);
                ps.setInt(6, limit);
                ps.setInt(7, offset);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> data = new HashMap<>();
                    data.put("no_daftar", rs.getString("no_daftar"));
                    data.put("id_pasien", rs.getString("id_pasien"));
                    data.put("id_dokter", rs.getString("id_dokter"));
                     data.put("tanggal", rs.getString("tanggal"));
                    data.put("keluhan", rs.getString("keluhan"));
                    listPendaftaran.add(data);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error tampilkanData: " + e.getMessage());
        }
        return listPendaftaran;
    }

    public boolean cekNoDaftar(String noDaftar) {
        String sql = "SELECT COUNT(*) FROM pendaftaran WHERE no_daftar = ?";
        try (Connection conn = config.Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, noDaftar);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error cekNoDaftar: " + e.getMessage());
        }
        return false;
    }

    public boolean simpan(String noDaftar, String idPasien, String idDokter, String tanggal, String keluhan) {
        String sql = "INSERT INTO pendaftaran (no_daftar, id_pasien, id_dokter, tanggal, keluhan) VALUES (?, ?, ?, ?, ?)";
try (Connection conn = config.Koneksi.getKoneksi();   
        PreparedStatement st = conn.prepareStatement(sql)) {
            
            st.setString(1, noDaftar);
            st.setString(2, idPasien);
            st.setString(3, idDokter);
            st.setString(4, tanggal);
            st.setString(5, keluhan);
          
        return st.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

    public boolean ubah(String noDaftar, String idPasien, String idDokter, String tanggal, String keluhan) {
        String sql = "UPDATE pendaftaran SET no_daftar=?, id_pasien=?, id_dokter=?, tanggal=?, keluhan=? WHERE no_daftar=?";
        try (Connection conn = config.Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, noDaftar);
            ps.setString(2, idPasien);
            ps.setString(3, idDokter);
            ps.setString(4, tanggal);
            ps.setString(5, keluhan);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error ubah pendaftaran: " + e.getMessage());
            return false;
        }
    }

    public boolean hapus(String noDaftar) {
        String sql = "DELETE FROM pendaftaran WHERE no_daftar=?";
        try (Connection conn = config.Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, noDaftar);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error hapus pendaftaran: " + e.getMessage());
            return false;
        }
    }

   

    

    

    

    

}
