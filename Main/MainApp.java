package Main; // Sesuaikan dengan nama package kamu

import config.Koneksi; // 
import View.Dashboard1;  
import config.Koneksi;
import java.sql.Connection;

public class MainApp {
    
    public static void main(String[] args) {
        
        System.out.println("Mencoba menghubungkan ke database MySQL...");
        Connection conn = Koneksi.getKoneksi();
        
        if (conn != null) {
            System.out.println("Koneksi sukses! Menjalankan aplikasi...");

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Dashboard1().setVisible(true);
                }
            });
            
        } else {
            System.out.println("Aplikasi gagal berjalan karena tidak ada koneksi ke database.");
        }
    }
}