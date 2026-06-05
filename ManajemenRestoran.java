
// ============================================================
// Main Class - Program Manajemen Restoran
// Nama   : ABDAN SYAKUR MUSTOFA
// NIM    : 053117448
// ============================================================
import java.util.Scanner;

public class ManajemenRestoran {

    static Scanner sc = new Scanner(System.in);
    static Menu menu = new Menu();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     SELAMAT DATANG DI RESTORAN       ║");
        System.out.println("║        MANAJEMEN RESTORAN            ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean jalan = true;
        while (jalan) {
            tampilMenuUtama();
            System.out.print("Pilih menu: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    tambahItemMenu();
                    break;
                case "2":
                    menu.tampilkanMenu();
                    break;
                case "3":
                    buatPesanan();
                    break;
                case "4":
                    System.out.println("\nTerima kasih! Sampai jumpa! 👋");
                    jalan = false;
                    break;
                default:
                    System.out.println("  Pilihan tidak valid, coba lagi.");
            }
        }
        sc.close();
    }

    // ── Menu Utama ────────────────────────────────────────────
    static void tampilMenuUtama() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("  1. Tambah Item Menu");
        System.out.println("  2. Tampilkan Menu Restoran");
        System.out.println("  3. Buat Pesanan Pelanggan");
        System.out.println("  4. Keluar");
        System.out.println("================================");
    }

    // ── Fitur 1: Tambah Item Menu ─────────────────────────────
    static void tambahItemMenu() {
        System.out.println("\n--- Tambah Item Menu ---");
        System.out.println("  1. Makanan");
        System.out.println("  2. Minuman");
        System.out.println("  3. Diskon");
        System.out.print("Pilih tipe item: ");
        String tipe = sc.nextLine().trim();

        try {
            System.out.print("Nama item  : ");
            String nama = sc.nextLine();

            System.out.print("Harga (Rp) : ");
            double harga = Double.parseDouble(sc.nextLine().trim());

            switch (tipe) {
                case "1":
                    System.out.print("Jenis Makanan (mis. Nasi, Mie, Snack): ");
                    String jenisMakanan = sc.nextLine();
                    menu.tambahItem(new Makanan(nama, harga, jenisMakanan));
                    break;
                case "2":
                    System.out.print("Jenis Minuman (mis. Panas, Dingin, Jus): ");
                    String jenisMinuman = sc.nextLine();
                    menu.tambahItem(new Minuman(nama, harga, jenisMinuman));
                    break;
                case "3":
                    System.out.print("Persen Diskon (mis. 10 untuk 10%): ");
                    double diskon = Double.parseDouble(sc.nextLine().trim());
                    menu.tambahItem(new Diskon(nama, harga, diskon));
                    break;
                default:
                    System.out.println("  Tipe tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  Input angka tidak valid: " + e.getMessage());
        }
    }

    // ── Fitur 3: Buat Pesanan ─────────────────────────────────
    static void buatPesanan() {
        if (menu.isEmpty()) {
            System.out.println("  Menu masih kosong! Tambahkan item terlebih dahulu.");
            return;
        }

        System.out.print("\nMasukkan nama pelanggan: ");
        String namaPelanggan = sc.nextLine();
        Pesanan pesanan = new Pesanan(namaPelanggan);

        boolean pesanLagi = true;
        while (pesanLagi) {
            menu.tampilkanMenu();
            System.out.print("Pilih nomor item (0 = selesai pesan): ");
            try {
                int nomor = Integer.parseInt(sc.nextLine().trim());
                if (nomor == 0) {
                    pesanLagi = false;
                } else {
                    MenuItem item = menu.getItem(nomor); // bisa lempar Exception
                    pesanan.tambahPesanan(item);
                }
            } catch (NumberFormatException e) {
                System.out.println("  Input harus berupa angka!");
            } catch (Exception e) {
                System.out.println("  ⚠ " + e.getMessage()); // Exception handling
            }
        }

        if (!pesanan.isEmpty()) {
            pesanan.tampilkanStruk();
        } else {
            System.out.println("  Tidak ada item yang dipesan.");
        }
    }
}
