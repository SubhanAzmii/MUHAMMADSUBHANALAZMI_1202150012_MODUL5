package com.example.android.muhammadsubhanalazmi_1202150012_modul5;

/**
 * Created by Kizuna on 24-Mar-18.
 */

public class todo  {
    int id;
    String nama;
    String Deskripsi;
    String priority;

    public todo(){
    }

    public todo(String nama, String deskripsi, String priority) {

        this.nama = nama;
        this.Deskripsi = deskripsi;
        this.priority = priority;
    }

    public todo(int id, String nama, String deskripsi, String priority) {
        this.id = id;
        this.nama = nama;
        Deskripsi = deskripsi;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
