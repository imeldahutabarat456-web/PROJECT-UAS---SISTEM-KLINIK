/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Obat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author LEWI
 */
public class ObatDAO {
private Connection connection;

    public ObatDAO() {
        try {
            connection = config.Koneksi.getKoneksi(); 
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public List<Obat> getAll() {
        List<Obat> list = new ArrayList<>();
        String sql = "SELECT * FROM obat";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Obat o = new Obat();
                o.setIdObat(rs.getString("id_obat"));
                o.setNamaObat(rs.getString("nama_obat"));
                o.setJenisObat(rs.getString("jenis_obat"));
                o.setHarga(rs.getDouble("harga"));
               
                
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
    JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
        }
        return list;
    }

    public void insert(Obat o) {
        String sql = "INSERT INTO obat (id_obat, nama_obat, jenis_obat, harga) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, o.getIdObat());
            st.setString(2, o.getNamaObat());
            st.setString(3, o.getJenisObat());
            st.setDouble(4, o.getHarga());
           
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insert: " + e.getMessage());
        }
    }

    public void update(Obat o) {
    String sql = "UPDATE obat SET nama_obat=?, jenis_obat=?, harga=? WHERE id_obat=?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, o.getNamaObat()); 
        st.setString(2, o.getJenisObat());
        st.setDouble(3, o.getHarga());
        st.setString(4, o.getIdObat()); 
        
        st.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error update: " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM obat WHERE id_obat=?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error delete: " + e.getMessage());
            }
        }
}
