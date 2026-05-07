//NAMA  : ABDAN SYAKUR MUSTOFA
//NIM   : 053117448
//UPBJJ : UT JAKARTA

//TUGAS 1 PEMROGRAMAN BERBASIS DESKTOP

//1. Input Menu Restoran: Program harus memiliki data menu makanan dan minuman beserta harganya.
//  Data ini dapat disimpan dalam sebuah array. Setiap item menu harus memiliki nama, harga, dan 
// kategori (makanan, minuman).

//2.Pemesanan: Program harus memungkinkan pelanggan untuk memesan makanan dan minuman berdasarkan 
// daftar menu yang ditampilkan sebelumnya. Pelanggan dapat memasukkan menu-menu yang ingin 
// mereka pesan (maksimal 4 menu) dan menyertakan jumlahnya.

//3.Menghitung Total Biaya: Program harus dapat menghitung total biaya pesanan berdasarkan item-menu 
// yang dipilih dan jumlahnya. Total Biaya keseluruhan pesanan ditambahkan dengan biaya 
// pajak 10% dari total biaya keseluruhan dan biaya pelayanan sebesar Rp. 20.000,-. 
// Selain itu, restoran ini juga menerapkan diskon atau penawaran khusus:
//A. Diskon 10% jika total biaya keseluruhan pesanan melebihi Rp 100.000,-
//B.Penawaran beli satu gratis satu untuk salah satu kategori minuman jika total biaya 
// keseluruhan pesanan melebihi Rp 50.000

//4. Mencetak Struk Pesanan: Program harus dapat mencetak struk pesanan yang mencantumkan item-menu
//  yang dipesan, jumlahnya, harga per item, total harga per item, total biaya pemesanan, informasi 
// pajak dan biaya pajak, dan biaya pelayanan. jika ada diskon dan harga setelah diskon / penawaran
//  , tampilkan informasinya ke dalam struk pembayaran.

import java.util.Scanner;

//BAGIAN 1
class Menu {
    String nama;
    double harga;
    String kategori;

    Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

// BAGIAN 2
public class Main {

    static Menu[] daftarMenu = {
            new Menu("Nasi Padang", 25000, "Makanan"),
            new Menu("Mie Ayam", 18000, "Makanan"),
            new Menu("Ayam Bakar", 30000, "Makanan"),
            new Menu("Gado-Gado", 15000, "Makanan"),
            new Menu("Soto Betawi", 22000, "Makanan"),
            new Menu("Es Teh Manis", 8000, "Minuman"),
            new Menu("Jus Alpukat", 15000, "Minuman"),
            new Menu("Es Jeruk", 10000, "Minuman"),
            new Menu("Kopi Susu", 18000, "Minuman"),
            new Menu("Air Mineral", 5000, "Minuman")
    };

    static String[] namaPesan = new String[4];
    static int[] jumlahPesan = new int[4];
    static double[] hargaPesan = new double[4];
    static String[] kategoriPesan = new String[4];
    static int totalItem = 0;

    // BAGIAN 3
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tampilkanMenu();
        terimaOrder(sc);
        cetakStruk();
        sc.close();
    }

    // ── Tampilkan Menu ─────────────────────────
    static void tampilkanMenu() {
        System.out.println("================================");
        System.out.println("   SELAMAT DATANG RESTO NUSANTARA");
        System.out.println("================================");

        System.out.println("\n--- MENU MAKANAN ---");
        System.out.printf("%-3s %-18s %s%n", "No", "Nama", "Harga");

        // BAGIAN 4
        if (daftarMenu[0].kategori.equals("Makanan"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "1.", daftarMenu[0].nama, daftarMenu[0].harga);
        if (daftarMenu[1].kategori.equals("Makanan"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "2.", daftarMenu[1].nama, daftarMenu[1].harga);
        if (daftarMenu[2].kategori.equals("Makanan"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "3.", daftarMenu[2].nama, daftarMenu[2].harga);
        if (daftarMenu[3].kategori.equals("Makanan"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "4.", daftarMenu[3].nama, daftarMenu[3].harga);
        if (daftarMenu[4].kategori.equals("Makanan"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "5.", daftarMenu[4].nama, daftarMenu[4].harga);

        System.out.println("\n--- MENU MINUMAN ---");
        System.out.printf("%-3s %-18s %s%n", "No", "Nama", "Harga");

        if (daftarMenu[5].kategori.equals("Minuman"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "1.", daftarMenu[5].nama, daftarMenu[5].harga);
        if (daftarMenu[6].kategori.equals("Minuman"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "2.", daftarMenu[6].nama, daftarMenu[6].harga);
        if (daftarMenu[7].kategori.equals("Minuman"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "3.", daftarMenu[7].nama, daftarMenu[7].harga);
        if (daftarMenu[8].kategori.equals("Minuman"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "4.", daftarMenu[8].nama, daftarMenu[8].harga);
        if (daftarMenu[9].kategori.equals("Minuman"))
            System.out.printf("%-3s %-18s Rp %.0f%n", "5.", daftarMenu[9].nama, daftarMenu[9].harga);

        System.out.println("\n* Maksimal 4 menu per pesanan");
    }

    // ── Terima Pesanan ─────────────────────────
    static void terimaOrder(Scanner sc) {
        System.out.println("\n--- FORM PEMESANAN ---");
        System.out.println("Format: Nama Menu = Jumlah");
        System.out.println("Ketik 'selesai' untuk mengakhiri.\n");

        System.out.print("Pesanan 1 : "); 
        String p1 = sc.nextLine().trim();
        if (!p1.equalsIgnoreCase("selesai")) {
            prosesInput(p1, 0);
            totalItem = 1;

            // BAGIAN 5
            System.out.print("Pesanan 2 : ");
            String p2 = sc.nextLine().trim();
            if (!p2.equalsIgnoreCase("selesai")) {
                prosesInput(p2, 1);
                totalItem = 2;

                System.out.print("Pesanan 3 : ");
                String p3 = sc.nextLine().trim();
                if (!p3.equalsIgnoreCase("selesai")) {
                    prosesInput(p3, 2);
                    totalItem = 3;

                    System.out.print("Pesanan 4 : ");
                    String p4 = sc.nextLine().trim();
                    if (!p4.equalsIgnoreCase("selesai")) {
                        prosesInput(p4, 3);
                        totalItem = 4;
                    }
                }
            }
        }
    }

    // BAGIAN 6
    // TAHAP1
    // ── Proses Input ───────────────────────────
    static void prosesInput(String input, int slot) {
        String[] bagian = input.split("=");
        String cari = bagian[0].trim();
        int qty = Integer.parseInt(bagian[1].trim());

        // Cari indeks menu yang cocok dengan nama
        int indeks = -1;
        if (daftarMenu[0].nama.equalsIgnoreCase(cari))
            indeks = 0;
        else if (daftarMenu[1].nama.equalsIgnoreCase(cari))
            indeks = 1;
        else if (daftarMenu[2].nama.equalsIgnoreCase(cari))
            indeks = 2;
        else if (daftarMenu[3].nama.equalsIgnoreCase(cari))
            indeks = 3;
        else if (daftarMenu[4].nama.equalsIgnoreCase(cari))
            indeks = 4;
        else if (daftarMenu[5].nama.equalsIgnoreCase(cari))
            indeks = 5;
        else if (daftarMenu[6].nama.equalsIgnoreCase(cari))
            indeks = 6;
        else if (daftarMenu[7].nama.equalsIgnoreCase(cari))
            indeks = 7;
        else if (daftarMenu[8].nama.equalsIgnoreCase(cari))
            indeks = 8;
        else if (daftarMenu[9].nama.equalsIgnoreCase(cari))
            indeks = 9;

        // TAHAP2
        // Switch case untuk memproses menu berdasarkan indeks yang ditemukan
        switch (indeks) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                namaPesan[slot] = daftarMenu[indeks].nama;
                hargaPesan[slot] = daftarMenu[indeks].harga;
                kategoriPesan[slot] = daftarMenu[indeks].kategori;
                jumlahPesan[slot] = qty;
                System.out.println(">> " + daftarMenu[indeks].nama + " ditambahkan ke pesanan.");
                break;
            default:
                System.out.println("Menu '" + cari + "' tidak ditemukan. Slot dikosongkan.");
        }
    }

    // BAGIAN 7
    // LANGKAH1
    // ── Cetak Struk ────────────────────────────
    static void cetakStruk() {
        // Hitung subtotal
        double subtotal = 0;
        if (totalItem >= 1)
            subtotal += hargaPesan[0] * jumlahPesan[0];
        if (totalItem >= 2)
            subtotal += hargaPesan[1] * jumlahPesan[1];
        if (totalItem >= 3)
            subtotal += hargaPesan[2] * jumlahPesan[2];
        if (totalItem >= 4)
            subtotal += hargaPesan[3] * jumlahPesan[3];

        // Cek diskon
        double diskon10 = 0;
        double diskonMinu = 0;
        String namaGratis = "-";

        // LANGKAH 2
        boolean adaDiskon10 = subtotal > 100000;
        boolean adaPromo = subtotal > 50000;

        if (adaDiskon10)
            diskon10 = subtotal * 0.10;

        // Cek promo beli 1 gratis 1 minuman termurah
        if (adaPromo) {
            double termurah = Double.MAX_VALUE;
            if (totalItem >= 1 && "Minuman".equals(kategoriPesan[0]) && hargaPesan[0] < termurah) {
                termurah = hargaPesan[0];
                namaGratis = namaPesan[0];
            }
            if (totalItem >= 2 && "Minuman".equals(kategoriPesan[1]) && hargaPesan[1] < termurah) {
                termurah = hargaPesan[1];
                namaGratis = namaPesan[1];
            }
            if (totalItem >= 3 && "Minuman".equals(kategoriPesan[2]) && hargaPesan[2] < termurah) {
                termurah = hargaPesan[2];
                namaGratis = namaPesan[2];
            }
            if (totalItem >= 4 && "Minuman".equals(kategoriPesan[3]) && hargaPesan[3] < termurah) {
                termurah = hargaPesan[3];
                namaGratis = namaPesan[3];
            }
            if (termurah != Double.MAX_VALUE)
                diskonMinu = termurah;
        }

        // LANGKAH 3
        double totalAkhir = subtotal - diskon10 - diskonMinu;
        double pajak = totalAkhir * 0.10;
        double biayaLayanan = 20000;
        double grandTotal = totalAkhir + pajak + biayaLayanan;

        // Cetak struk
        System.out.println("\n================================");
        System.out.println("        STRUK PEMBAYARAN");
        System.out.println("       RESTO NUSANTARA");
        System.out.println("================================");
        System.out.printf("%-22s %5s %10s%n", "Item", "Qty", "Harga");
        System.out.println("--------------------------------");

        if (totalItem >= 1)
            System.out.printf("%-22s %5d  Rp %.0f%n", namaPesan[0], jumlahPesan[0], hargaPesan[0] * jumlahPesan[0]);
        if (totalItem >= 2)
            System.out.printf("%-22s %5d  Rp %.0f%n", namaPesan[1], jumlahPesan[1], hargaPesan[1] * jumlahPesan[1]);
        if (totalItem >= 3)
            System.out.printf("%-22s %5d  Rp %.0f%n", namaPesan[2], jumlahPesan[2], hargaPesan[2] * jumlahPesan[2]);
        if (totalItem >= 4)
            System.out.printf("%-22s %5d  Rp %.0f%n", namaPesan[3], jumlahPesan[3], hargaPesan[3] * jumlahPesan[3]);

        System.out.println("--------------------------------");
        System.out.printf("%-28s Rp %.0f%n", "Subtotal", subtotal);

        if (adaDiskon10)
            System.out.printf("%-28s -Rp %.0f%n", "Diskon 10%", diskon10);
        if (adaPromo && diskonMinu > 0)
            System.out.printf("%-28s -Rp %.0f%n", "Gratis: " + namaGratis, diskonMinu);

        if (adaDiskon10 || (adaPromo && diskonMinu > 0))
            System.out.printf("%-28s Rp %.0f%n", "Total setelah diskon", totalAkhir);

        System.out.printf("%-28s Rp %.0f%n", "Pajak 10%", pajak);
        System.out.printf("%-28s Rp %.0f%n", "Biaya Pelayanan", biayaLayanan);
        System.out.println("================================");
        System.out.printf("%-28s Rp %.0f%n", "TOTAL BAYAR", grandTotal);
        System.out.println("================================");
        System.out.println("  Terima kasih sudah berkunjung!");
        System.out.println("================================");
    }
}

// Kalau subtotal di atas Rp100.000 → dapat diskon 10%
// Kalau subtotal di atas Rp50.000 dan ada minuman → dapat promo beli 1 gratis 1
// untuk
// minuman termurah