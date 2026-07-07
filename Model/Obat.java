/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEWI
 */
public class Obat {
   private String idObat;
    private String namaObat;
    private String jenisObat;
    private double harga;
   
    public Obat() {
    }

    public void insert (Obat o){
        String sql = "INSERT INTO obat(id_obat, nama_obat, jenis_Obat, harga) VALUES (?, ?, ?, ?)";
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.jenisObat = jenisObat;
        this.harga = harga;
    }


    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
