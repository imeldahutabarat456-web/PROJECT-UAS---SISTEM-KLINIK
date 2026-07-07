/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEWI
 */
public class Dokter {
    private String idDokter;
    private String nama;
    private String nip;
    private String spesialis;
    private String tglLahir;
    private String alamat;
    private String noTelp;

    public Dokter() {
    }
    
    public Dokter(String idDokter, String nama, String nip, String spesialis, String tglLahir, String alamat, String noTelp) {
        this.idDokter = idDokter;
        this.nama = nama;
        this.nip = nip;
        this.spesialis = spesialis;
        this.tglLahir = tglLahir;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }
    public String getIdDokter() { 
        return idDokter; 
    }
    public void setIdDokter(String idDokter) { 
        this.idDokter = idDokter; 
    }
    public String getNama() { 
        return nama; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }
    public String getNip() { 
        return nip; 
    }
    public void setNip(String nip) { 
        this.nip = nip; 
    }
    public String getSpesialis() { 
        return spesialis; 
    }
    public void setSpesialis(String spesialis) { 
        this.spesialis = spesialis; 
    }
    public String getTglLahir() { 
        return tglLahir; 
    }
    public void setTglLahir(String tglLahir) { 
        this.tglLahir = tglLahir; 
    }
    public String getAlamat() { 
        return alamat; 
    }
    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }
    public String getNoTelp() { 
        return noTelp; 
    }
    public void setNoTelp(String noTelp) { 
        this.noTelp = noTelp; 
    }
    public String getNamaDokter() {
        return this.nama;
    }
    public String setNamaDokter(String nama) {
       this.nama = nama;
        return null;
    }
}
