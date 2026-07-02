package model;

public class Surat {

    private String jenis;
    private String nomor;
    private String tanggal;
    private String pengirim;
    private String perihal;

    // Constructor
    public Surat(String jenis, String nomor, String tanggal,
                 String pengirim, String perihal) {

        this.jenis = jenis;
        this.nomor = nomor;
        this.tanggal = tanggal;
        this.pengirim = pengirim;
        this.perihal = perihal;
    }

    // Getter
    public String getJenis() {
        return jenis;
    }

    public String getNomor() {
        return nomor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getPerihal() {
        return perihal;
    }

    // Setter
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

}