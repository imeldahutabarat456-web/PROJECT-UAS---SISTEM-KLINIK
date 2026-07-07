/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEWI
 */
public class Pendaftaran {
   private String noPendaftaran;
    private String idPasien;
    private String idDokter;
    private String tanggal;
    private String keluhan;
    
    public Pendaftaran() {
        
    }

    public Pendaftaran(String idPendaftaran, String idPasien, String idDokter, String tanggal, String keluhan) {
        this.noPendaftaran = noPendaftaran;
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.tanggal = tanggal;
        this.keluhan = keluhan;
    }

    public String getnoPendaftaran() { 
        return noPendaftaran; 
    }
    public void setnoPendaftaran(String idPendaftaran) { 
        this.noPendaftaran = idPendaftaran; 
    }
    public String getIdPasien() { 
        return idPasien; 
    }
    public void setIdPasien(String idPasien) { 
        this.idPasien = idPasien; 
    }
    public String getIdDokter() { 
        return idDokter; 
    }
    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter; 
    }
    public String getTanggal() { 
        return tanggal; 
    }
    public void setTglDaftar(String tglDaftar) { 
        this.tanggal = tanggal; 
    }
    public String getKeluhan() { 
        return keluhan; 
    }
    public void setKeluhan(String keluhan) { 
        this.keluhan = keluhan; 
    } 
}
