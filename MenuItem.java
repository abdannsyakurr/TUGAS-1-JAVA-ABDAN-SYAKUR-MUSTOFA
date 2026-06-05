// ============================================================
// Kelas Abstrak MenuItem - Kelas dasar untuk semua item menu
// ============================================================
public abstract class MenuItem {

    // Atribut (encapsulation - private)
    private String nama;
    private double harga;
    private String kategori;

    // Constructor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getter & Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    // Metode abstrak - wajib diimplementasikan oleh subkelas
    public abstract void tampilMenu();

    // Metode untuk format ke file
    public abstract String toFileString();
}
