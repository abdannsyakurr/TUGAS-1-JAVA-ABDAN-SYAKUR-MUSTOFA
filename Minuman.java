// ============================================================
// Kelas Minuman - Subkelas dari MenuItem
// ============================================================
public class Minuman extends MenuItem {

    private String jenisMinuman; // contoh: Dingin, Panas, Jus

    // Constructor
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    // Getter & Setter
    public String getJenisMinuman() { return jenisMinuman; }
    public void setJenisMinuman(String jenisMinuman) { this.jenisMinuman = jenisMinuman; }

    // Implementasi metode abstrak tampilMenu() - Polymorphism
    @Override
    public void tampilMenu() {
        System.out.println("  [MINUMAN] " + getNama());
        System.out.println("  Jenis    : " + jenisMinuman);
        System.out.println("  Harga    : Rp " + String.format("%,.0f", getHarga()));
    }

    // Format untuk disimpan ke file
    @Override
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }
}
