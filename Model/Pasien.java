/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEWI
 */
public class Pasien {
    private String idPasien;
    private String namaPasien;
    private String jenisKelamin;
    private String alamat;
    private String tglLahir;
    private String noTelp;
    private String nik;
    private String nama;
    private int umur;

    
    public Pasien() {
    }
    
    public Pasien(String idPasien, String namaPasien, String jenisKelamin, String alamat, String tglLahir, String noTelp) {
        this.idPasien = idPasien;
        this.namaPasien = namaPasien;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.tglLahir = tglLahir;
        this.noTelp = noTelp;
    }

    public String getIdPasien() { 
        return idPasien; 
    }
    public void setIdPasien(String idPasien) { 
        this.idPasien = idPasien; 
    }
    public String getNamaPasien() { 
        return namaPasien; 
    }
    public void setNamaPasien(String namaPasien) { 
        this.namaPasien = namaPasien; 
    }
    public String getJenisKelamin() { 
        return jenisKelamin; 
    }
    public void setJenisKelamin(String jenisKelamin) { 
        this.jenisKelamin = jenisKelamin; 
    }
    public String getAlamat() { 
        return alamat; 
    }
    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }
    public String getTglLahir() { 
        return tglLahir; 
    }
    public void setTglLahir(String tglLahir) { 
        this.tglLahir = tglLahir; 
    }
    public String getNoTelp() { 
        return noTelp;
    }
    public void setNoTelp(String noTelp) { 
        this.noTelp = noTelp; 
    }
    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }
    public int getUmur() {
        return umur;
    }
    public void setUmur(int umur) {
        this.umur = umur;
    }
}
