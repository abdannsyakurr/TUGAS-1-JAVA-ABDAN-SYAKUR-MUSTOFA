// ============================================================
// Kelas Makanan - Subkelas dari MenuItem
// ============================================================
public class Makanan extends MenuItem {

    private String jenisMakanan; // contoh: Nasi, Mie, Snack

    // Constructor
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    // Getter & Setter
    public String getJenisMakanan() { return jenisMakanan; }
    public void setJenisMakanan(String jenisMakanan) { this.jenisMakanan = jenisMakanan; }

    // Implementasi metode abstrak tampilMenu() - Polymorphism
    @Override
    public void tampilMenu() {
        System.out.println("  [MAKANAN] " + getNama());
        System.out.println("  Jenis    : " + jenisMakanan);
        System.out.println("  Harga    : Rp " + String.format("%,.0f", getHarga()));
    }

    // Format untuk disimpan ke file
    @Override
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }
}
