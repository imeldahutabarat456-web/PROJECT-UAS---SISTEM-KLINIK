/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEWI
 */
public class Transaksi {
    private int idTransaksi;
    private String idPasien;
    private String namaPasien;
    private String idDokter;
    private String namaDokter;
    private String idObat;
    private String namaObat;
    private String tindakan;
    private String jenisObat;
    private double hargaObat;

    public Transaksi() {}

    public int getIdTransaksi() { 
        return idTransaksi; 
    }
    public void setIdTransaksi(int idTransaksi) { 
        this.idTransaksi = idTransaksi; 
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
    public String getIdDokter() { 
        return idDokter; 
    }
    public void setIdDokter(String idDokter) { 
        this.idDokter = idDokter; 
    }
    public String getNamaDokter() { 
        return namaDokter;
    }
    public void setNamaDokter(String namaDokter) { 
        this.namaDokter = namaDokter; 
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
    public String getTindakan() { 
        return tindakan; 
    }
    public void setTindakan(String tindakan) {
        this.tindakan = tindakan; 
    }
    public String getJenisObat() { 
        return jenisObat; 
    }
    public void setJenisObat(String jenisObat) { 
        this.jenisObat = jenisObat; 
    }
    public double getHargaObat() { 
        return hargaObat;
    }
    public void setHargaObat(double hargaObat) { 
        this.hargaObat = hargaObat; 
    }
}