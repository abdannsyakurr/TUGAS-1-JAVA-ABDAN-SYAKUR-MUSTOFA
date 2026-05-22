import java.util.Scanner;

public class RestaurantApp {

    // DATA MENU (Array)

    static String[] nama = new String[50];
    static int[] harga = new int[50];
    static String[] kategori = new String[50];
    static int totalMenu = 10;

    static {
        String[] namAwal = { "Nasi Goreng", "Mie Ayam", "Soto Ayam", "Ayam Bakar", "Gado-Gado",
                "Es Teh Manis", "Jus Jeruk", "Kopi Hitam", "Es Campur", "Air Mineral" };
        int[] hrgAwal = { 25000, 20000, 18000, 30000, 15000,
                8000, 15000, 10000, 20000, 5000 };
        String[] katAwal = { "Makanan", "Makanan", "Makanan", "Makanan", "Makanan",
                "Minuman", "Minuman", "Minuman", "Minuman", "Minuman" };
        for (int i = 0; i < totalMenu; i++) {
            nama[i] = namAwal[i];
            harga[i] = hrgAwal[i];
            kategori[i] = katAwal[i];
        }
    }

    // Data pesanan pelanggan
    static int[] pesananIdx = new int[100];
    static int[] pesananQty = new int[100];
    static int totalPesan = 0;

    static Scanner sc = new Scanner(System.in);

    // MAIN

    public static void main(String[] args) {
        String pilih;

        // [DO-WHILE] Menu utama terus tampil sampai user pilih "0"
        do {
            System.out.println("\n===== RESTORAN NUSANTARA =====");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Pengelolaan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilih = sc.nextLine().trim();

            // [SWITCH-CASE] Navigasi menu utama
            switch (pilih) {
                case "1":
                    menuPelanggan();
                    break;
                case "2":
                    menuPengelolaan();
                    break;
                case "0":
                    System.out.println("Sampai jumpa!");
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid.");
            }
        } while (!pilih.equals("0"));
    }

    // TAMPILKAN DAFTAR MENU

    static void tampilMenu() {
        System.out.println("\nNo | Nama Menu           | Kategori | Harga");
        System.out.println("---|---------------------|----------|----------");

        // [FOR] Menampilkan semua menu beserta nomornya
        for (int i = 0; i < totalMenu; i++) {
            System.out.printf("%-3d| %-20s| %-9s| Rp%,d%n",
                    (i + 1), nama[i], kategori[i], harga[i]);
        }
    }

    // CEK MENU TERSEDIA (FOR-EACH)

    static void tampilKategori(String kat) {
        System.out.println("\nDaftar " + kat + " yang tersedia:");

        // [FOR-EACH] Iterasi array kategori untuk mencocokkan kategori yang dipilih
        int no = 1;
        for (int i = 0; i < totalMenu; i++) {
            if (kategori[i].equals(kat)) {
                System.out.println("  " + no + ". " + nama[i] + " - Rp" + String.format("%,d", harga[i]));
                no++;
            }
        }
    }

    // MENU PELANGGAN

    static void menuPelanggan() {
        String pilih;

        // [DO-WHILE] Menu pelanggan terus tampil sampai pilih "0"
        do {
            System.out.println("\n--- MENU PELANGGAN ---");
            System.out.println("1. Pesan Makanan/Minuman");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = sc.nextLine().trim();

            if (pilih.equals("1")) {
                prosesPemesanan();
            } else if (!pilih.equals("0")) {
                System.out.println("[!] Pilihan tidak valid.");
            }
        } while (!pilih.equals("0"));
    }

    // PROSES PEMESANAN

    static void prosesPemesanan() {
        totalPesan = 0;

        // Tampilkan menu makanan & minuman pakai for-each
        tampilKategori("Makanan");
        tampilKategori("Minuman");

        System.out.println("\nKetik nama menu untuk memesan, atau 'selesai' untuk mengakhiri.");

        // [WHILE] Terus menerima pesanan hingga input "selesai"
        String input = "";
        while (!input.equalsIgnoreCase("selesai")) {
            System.out.print("Pesan: ");
            input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("selesai")) {
                if (totalPesan == 0) {
                    System.out.println("[!] Belum ada pesanan, silakan pesan minimal 1 item.");
                    input = ""; // Reset agar loop lanjut
                }
                continue;
            }

            // Cari menu berdasarkan nama input
            // [FOR-EACH] Ambil daftar nama menu yang aktif untuk dicek
            String[] menuAktif = new String[totalMenu];
            for (int i = 0; i < totalMenu; i++)
                menuAktif[i] = nama[i];

            int idxMenu = -1;
            for (String m : menuAktif) {
                if (m.equalsIgnoreCase(input)) {
                    for (int i = 0; i < totalMenu; i++) {
                        if (nama[i].equalsIgnoreCase(input)) {
                            idxMenu = i;
                            break;
                        }
                    }
                    break;
                }
            }

            // [IF-ELSE] Validasi apakah menu ditemukan
            if (idxMenu == -1) {
                System.out.println("[!] Menu tidak ditemukan. Coba lagi.");
            } else {
                System.out.print("Jumlah: ");
                int qty;
                try {
                    qty = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("[!] Jumlah tidak valid.");
                    continue;
                }

                if (qty <= 0) {
                    System.out.println("[!] Jumlah harus lebih dari 0.");
                    continue;
                }

                // Cek apakah menu sudah dipesan sebelumnya
                boolean sudahAda = false;
                for (int i = 0; i < totalPesan; i++) {
                    if (pesananIdx[i] == idxMenu) {
                        pesananQty[i] += qty;
                        sudahAda = true;
                        break;
                    }
                }

                if (!sudahAda) {
                    pesananIdx[totalPesan] = idxMenu;
                    pesananQty[totalPesan] = qty;
                    totalPesan++;
                }
                System.out.println("Ditambahkan: " + nama[idxMenu] + " x" + qty);
            }
        }

        cetakStruk();
    }

    // CETAK STRUK

    static void cetakStruk() {
        // Hitung total biaya awal
        int totalBiaya = 0;
        for (int i = 0; i < totalPesan; i++) {
            totalBiaya += harga[pesananIdx[i]] * pesananQty[i];
        }

        // [IF] Cek diskon 10% jika total > Rp100.000
        int diskon10 = 0;
        if (totalBiaya > 100000) {
            diskon10 = totalBiaya / 10;
        }

        // [IF] Cek promo beli 1 gratis 1 minuman jika total > Rp50.000
        int promoGratis = 0;
        int idxPromo = -1;
        if (totalBiaya > 50000) {
            int hargaTerendah = Integer.MAX_VALUE;
            for (int i = 0; i < totalPesan; i++) {
                int idx = pesananIdx[i];
                if (kategori[idx].equals("Minuman") && harga[idx] < hargaTerendah) {
                    hargaTerendah = harga[idx];
                    idxPromo = i;
                }
            }
            if (idxPromo != -1) {
                promoGratis = hargaTerendah;
            }
        }

        int totalSetelahDiskon = totalBiaya - diskon10 - promoGratis;
        int pajak = totalSetelahDiskon / 10;
        int pelayanan = 20000;
        int grandTotal = totalSetelahDiskon + pajak + pelayanan;

        // Cetak struk
        System.out.println("\n======================================");
        System.out.println("         STRUK PEMBAYARAN            ");
        System.out.println("       RESTORAN NUSANTARA            ");
        System.out.println("======================================");
        System.out.printf("%-20s %4s %10s%n", "Item", "Qty", "Subtotal");
        System.out.println("--------------------------------------");

        // [FOR] Cetak setiap item pesanan
        for (int i = 0; i < totalPesan; i++) {
            int idx = pesananIdx[i];
            int subtot = harga[idx] * pesananQty[i];
            System.out.printf("%-20s x%-3d Rp%,7d%n", nama[idx], pesananQty[i], subtot);
        }

        System.out.println("--------------------------------------");
        System.out.printf("%-24s Rp%,7d%n", "Total Pesanan", totalBiaya);

        if (diskon10 > 0) {
            System.out.printf("%-24s -Rp%,6d%n", "Diskon 10%", diskon10);
        }
        if (promoGratis > 0) {
            System.out.printf("%-24s -Rp%,6d%n", "Promo B1G1 (" + nama[pesananIdx[idxPromo]] + ")", promoGratis);
        }
        if (diskon10 > 0 || promoGratis > 0) {
            System.out.printf("%-24s Rp%,7d%n", "Setelah Diskon/Promo", totalSetelahDiskon);
        }

        System.out.printf("%-24s Rp%,7d%n", "Pajak (10%)", pajak);
        System.out.printf("%-24s Rp%,7d%n", "Biaya Pelayanan", pelayanan);
        System.out.println("======================================");
        System.out.printf("%-24s Rp%,7d%n", "GRAND TOTAL", grandTotal);
        System.out.println("======================================");
        System.out.println("   Terima kasih telah berkunjung!   ");
    }

    // MENU PENGELOLAAN

    static void menuPengelolaan() {
        String pilih;

        // [DO-WHILE] Menu pengelolaan terus tampil sampai pilih "0"
        do {
            System.out.println("\n--- MENU PENGELOLAAN ---");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = sc.nextLine().trim();

            switch (pilih) {
                case "1":
                    tambahMenu();
                    break;
                case "2":
                    ubahHarga();
                    break;
                case "3":
                    hapusMenu();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid.");
            }
        } while (!pilih.equals("0"));
    }

    // TAMBAH MENU

    static void tambahMenu() {
        System.out.print("Nama menu baru : ");
        String namaBaru = sc.nextLine().trim();

        if (namaBaru.isEmpty()) {
            System.out.println("[!] Nama tidak boleh kosong.");
            return;
        }

        System.out.print("Harga          : Rp ");
        int hargaBaru;
        try {
            hargaBaru = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Harga tidak valid.");
            return;
        }

        // [WHILE] Validasi input kategori
        String katBaru = "";
        while (!katBaru.equals("Makanan") && !katBaru.equals("Minuman")) {
            System.out.print("Kategori (Makanan/Minuman): ");
            katBaru = sc.nextLine().trim();
            if (!katBaru.equalsIgnoreCase("Makanan") && !katBaru.equalsIgnoreCase("Minuman")) {
                System.out.println("[!] Kategori harus 'Makanan' atau 'Minuman'.");
            } else {
                katBaru = katBaru.substring(0, 1).toUpperCase() + katBaru.substring(1).toLowerCase();
            }
        }

        nama[totalMenu] = namaBaru;
        harga[totalMenu] = hargaBaru;
        kategori[totalMenu] = katBaru;
        totalMenu++;
        System.out.println("Menu '" + namaBaru + "' berhasil ditambahkan!");
    }

    // UBAH HARGA

    static void ubahHarga() {
        tampilMenu();

        int no = pilihNomorMenu();

        System.out.println("Menu          : " + nama[no]);
        System.out.println("Harga saat ini: Rp" + String.format("%,d", harga[no]));
        System.out.print("Harga baru    : Rp ");

        int hargaBaru;
        try {
            hargaBaru = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Harga tidak valid.");
            return;
        }

        System.out.print("Yakin ubah harga menjadi Rp" + String.format("%,d", hargaBaru) + "? (Ya/Tidak): ");
        String konfirm = sc.nextLine().trim();

        if (konfirm.equalsIgnoreCase("Ya")) {
            harga[no] = hargaBaru;
            System.out.println("Harga berhasil diubah!");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    // HAPUS MENU

    static void hapusMenu() {
        tampilMenu();

        int no = pilihNomorMenu();

        System.out.println("Menu yang akan dihapus: " + nama[no]);
        System.out.print("Yakin ingin menghapus? (Ya/Tidak): ");
        String konfirm = sc.nextLine().trim();

        if (konfirm.equalsIgnoreCase("Ya")) {
            // [FOR] Geser elemen array ke kiri setelah penghapusan
            for (int i = no; i < totalMenu - 1; i++) {
                nama[i] = nama[i + 1];
                harga[i] = harga[i + 1];
                kategori[i] = kategori[i + 1];
            }
            totalMenu--;
            System.out.println("Menu berhasil dihapus!");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    // HELPER: Pilih Nomor Menu

    static int pilihNomorMenu() {
        // [WHILE] Terus meminta input sampai nomor valid
        while (true) {
            System.out.print("Masukkan nomor menu (1-" + totalMenu + "): ");
            try {
                int no = Integer.parseInt(sc.nextLine().trim());
                if (no >= 1 && no <= totalMenu) {
                    return no - 1;
                }
                System.out.println("[!] Nomor di luar rentang.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan angka yang valid.");
            }
        }
    }
}  
