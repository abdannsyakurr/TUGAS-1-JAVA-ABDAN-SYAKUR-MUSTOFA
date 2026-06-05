// ============================================================
// Kelas Diskon - Subkelas dari MenuItem
// ============================================================
public class Diskon extends MenuItem {

    private double persenDiskon; // contoh: 10.0 berarti diskon 10%

    // Constructor
    public Diskon(String nama, double harga, double persenDiskon) {
        super(nama, harga, "Diskon");
        this.persenDiskon = persenDiskon;
    }

    // Getter & Setter
    public double getPersenDiskon() { return persenDiskon; }
    public void setPersenDiskon(double persenDiskon) { this.persenDiskon = persenDiskon; }

    // Hitung harga setelah diskon
    public double getHargaSetelahDiskon() {
        return getHarga() - (getHarga() * persenDiskon / 100);
    }

    // Implementasi metode abstrak tampilMenu() - Polymorphism
    @Override
    public void tampilMenu() {
        System.out.println("  [DISKON]  " + getNama());
        System.out.println("  Diskon   : " + persenDiskon + "%");
        System.out.println("  Harga Asal   : Rp " + String.format("%,.0f", getHarga()));
        System.out.println("  Harga Diskon : Rp " + String.format("%,.0f", getHargaSetelahDiskon()));
    }

    // Format untuk disimpan ke file
    @Override
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + getHarga() + "|" + persenDiskon;
    }
}
