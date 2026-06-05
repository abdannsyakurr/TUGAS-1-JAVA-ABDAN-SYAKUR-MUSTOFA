// ============================================================
// Kelas Menu - Mengelola semua item menu restoran
// ============================================================
import java.util.ArrayList;
import java.io.*;

public class Menu {

    private ArrayList<MenuItem> daftarMenu;
    private static final String FILE_MENU = "menu.txt";

    // Constructor
    public Menu() {
        daftarMenu = new ArrayList<>();
        muatDariFile(); // otomatis muat menu dari file saat program dijalankan
    }

    // Tambah item ke menu
    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        simpanKeFile();
        System.out.println("✔ Item \"" + item.getNama() + "\" berhasil ditambahkan!");
    }

    // Tampilkan semua item menu
    public void tampilkanMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("  Menu masih kosong.");
            return;
        }

        System.out.println("\n========== DAFTAR MENU RESTORAN ==========");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.println("  No. " + (i + 1));
            daftarMenu.get(i).tampilMenu(); // Polymorphism
            System.out.println("  ------------------------------------------");
        }
    }

    // Ambil item berdasarkan nomor (exception handling)
    public MenuItem getItem(int nomor) throws Exception {
        if (nomor < 1 || nomor > daftarMenu.size()) {
            throw new Exception("Item nomor " + nomor + " tidak ditemukan dalam menu!");
        }
        return daftarMenu.get(nomor - 1);
    }

    // Cek apakah menu kosong
    public boolean isEmpty() {
        return daftarMenu.isEmpty();
    }

    // Jumlah item dalam menu
    public int jumlahItem() {
        return daftarMenu.size();
    }

    // ── Operasi File ──────────────────────────────────────────

    // Simpan menu ke file teks
    public void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MENU))) {
            for (MenuItem item : daftarMenu) {
                writer.write(item.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    // Muat menu dari file teks
    public void muatDariFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MENU))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] parts = baris.split("\\|");
                if (parts.length < 4) continue;

                String tipe   = parts[0];
                String nama   = parts[1];
                double harga  = Double.parseDouble(parts[2]);
                String extra  = parts[3];

                switch (tipe) {
                    case "MAKANAN":
                        daftarMenu.add(new Makanan(nama, harga, extra));
                        break;
                    case "MINUMAN":
                        daftarMenu.add(new Minuman(nama, harga, extra));
                        break;
                    case "DISKON":
                        daftarMenu.add(new Diskon(nama, harga, Double.parseDouble(extra)));
                        break;
                }
            }
            if (!daftarMenu.isEmpty()) {
                System.out.println("✔ Menu berhasil dimuat dari file (" + daftarMenu.size() + " item).");
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat menu: " + e.getMessage());
        }
    }
}
