import java.util.Scanner;

public class HitungGajiKaryawan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array gaji pokok berdasarkan golongan (A=0, B=1, C=2)
        double[] gajiPokok = {5000000, 6500000, 9500000};

        // Array persentase lembur (1 jam=30%, 2 jam=32%, dst.)
        double[] persenLembur = {30, 32, 34, 36, 38};

        // Input data karyawan
        System.out.print("Masukkan Nama Karyawan  : ");
        String nama = input.nextLine();

        System.out.print("Masukkan Golongan (A/B/C): ");
        String golongan = input.nextLine().toUpperCase();

        System.out.print("Masukkan Jam Lembur     : ");
        int jamLembur = input.nextInt();

        // Tentukan index array gaji berdasarkan golongan
        int indexGaji;
        if (golongan.equals("A")) {
            indexGaji = 0;
        } else if (golongan.equals("B")) {
            indexGaji = 1;
        } else if (golongan.equals("C")) {
            indexGaji = 2;
        } else {
            System.out.println("Golongan tidak valid!");
            return;
        }

        double gaji = gajiPokok[indexGaji];

        // Tentukan index array lembur berdasarkan jam lembur
        double totalLembur = 0;
        if (jamLembur > 0) {
            int indexLembur;
            if (jamLembur == 1) {
                indexLembur = 0;
            } else if (jamLembur == 2) {
                indexLembur = 1;
            } else if (jamLembur == 3) {
                indexLembur = 2;
            } else if (jamLembur == 4) {
                indexLembur = 3;
            } else {
                indexLembur = 4; // >= 5 jam
            }
            totalLembur = gaji * persenLembur[indexLembur] / 100;
        }

        double totalGaji = gaji + totalLembur;

        // Output
        System.out.println("\n===== SLIP GAJI KARYAWAN =====");
        System.out.println("Nama Karyawan  : " + nama);
        System.out.println("Golongan       : " + golongan);
        System.out.println("Gaji Pokok     : Rp. " + String.format("%,.0f", gaji));
        System.out.println("Jam Lembur     : " + jamLembur + " Jam");
        System.out.println("Tunjangan Lembur: Rp. " + String.format("%,.0f", totalLembur));
        System.out.println("Total Gaji     : Rp. " + String.format("%,.0f", totalGaji));
        System.out.println("==============================");

        input.close();
    }
}
