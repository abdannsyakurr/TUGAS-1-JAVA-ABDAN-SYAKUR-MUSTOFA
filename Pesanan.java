// ============================================================
// Kelas Pesanan - Mencatat pesanan pelanggan
// ============================================================
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pesanan {

    private String namaPelanggan;
    private ArrayList<MenuItem> itemPesanan;
    private static final String FILE_STRUK = "struk_pesanan.txt";

    // Constructor
    public Pesanan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
        this.itemPesanan = new ArrayList<>();
    }

    // Tambah item ke pesanan
    public void tambahPesanan(MenuItem item) {
        itemPesanan.add(item);
        System.out.println("  ✔ \"" + item.getNama() + "\" ditambahkan ke pesanan.");
    }

    // Hitung total biaya (dengan diskon jika ada)
    public double hitungTotal() {
        double total = 0;
        for (MenuItem item : itemPesanan) {
            if (item instanceof Diskon) {
                total += ((Diskon) item).getHargaSetelahDiskon();
            } else {
                total += item.getHarga();
            }
        }
        return total;
    }

    // Tampilkan struk pesanan di layar
    public void tampilkanStruk() {
        String waktu = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         STRUK PESANAN RESTORAN       ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("  Pelanggan : " + namaPelanggan);
        System.out.println("  Waktu     : " + waktu);
        System.out.println("  ─────────────────────────────────────");

        if (itemPesanan.isEmpty()) {
            System.out.println("  (Tidak ada item yang dipesan)");
        } else {
            for (MenuItem item : itemPesanan) {
                if (item instanceof Diskon) {
                    System.out.printf("  %-22s Rp %,.0f (diskon %.0f%%)%n",
                        item.getNama(),
                        ((Diskon) item).getHargaSetelahDiskon(),
                        ((Diskon) item).getPersenDiskon());
                } else {
                    System.out.printf("  %-22s Rp %,.0f%n",
                        item.getNama(), item.getHarga());
                }
            }
        }

        System.out.println("  ─────────────────────────────────────");
        System.out.printf("  %-22s Rp %,.0f%n", "TOTAL", hitungTotal());
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("      Terima kasih telah berkunjung!    ");

        simpanStrukKeFile(waktu);
    }

    // Simpan struk ke file teks
    public void simpanStrukKeFile(String waktu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_STRUK, true))) {
            writer.write("========================================");
            writer.newLine();
            writer.write("STRUK PESANAN - " + waktu);
            writer.newLine();
            writer.write("Pelanggan : " + namaPelanggan);
            writer.newLine();
            writer.write("----------------------------------------");
            writer.newLine();

            for (MenuItem item : itemPesanan) {
                if (item instanceof Diskon) {
                    writer.write(String.format("%-22s Rp %,.0f (diskon %.0f%%)",
                        item.getNama(),
                        ((Diskon) item).getHargaSetelahDiskon(),
                        ((Diskon) item).getPersenDiskon()));
                } else {
                    writer.write(String.format("%-22s Rp %,.0f",
                        item.getNama(), item.getHarga()));
                }
                writer.newLine();
            }

            writer.write("----------------------------------------");
            writer.newLine();
            writer.write(String.format("%-22s Rp %,.0f", "TOTAL", hitungTotal()));
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.newLine();

            System.out.println("✔ Struk berhasil disimpan ke \"" + FILE_STRUK + "\"");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }

    public boolean isEmpty() {
        return itemPesanan.isEmpty();
    }
}
