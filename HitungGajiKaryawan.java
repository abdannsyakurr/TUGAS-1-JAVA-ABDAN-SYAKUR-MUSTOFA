import java.util.Scanner;

public class HitungGajiKaryawan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Deklarasi Variabel
        String golongan;
        int jamLembur;
        double gajiPokok = 0;
        double lembur = 0;
        double totalGaji;

        // Input
        System.out.print("Masukkan Golongan (A/B/C): ");
        golongan = input.next().toUpperCase();

        System.out.print("Masukkan Jam Lembur: ");
        jamLembur = input.nextInt();

        // gaji pokok
        if (golongan.equals("A")) {
            gajiPokok = 5000000;
        } else if (golongan.equals("B")) {
            gajiPokok = 6500000;
        } else if (golongan.equals("C")) {
            gajiPokok = 9500000;
        } else {
            System.out.println("Golongan tidak valid!");
        }

        // lembur
        if (jamLembur == 0) {
            lembur = 0;
        } else if (jamLembur == 1) {
            lembur = 0.30 * gajiPokok;
        } else if (jamLembur == 2) {
            lembur = 0.32 * gajiPokok;
        } else if (jamLembur == 3) {
            lembur = 0.34 * gajiPokok;
        } else if (jamLembur == 4) {
            lembur = 0.36 * gajiPokok;
        } else if (jamLembur >= 5) {
            lembur = 0.38 * gajiPokok;
        }

        // total gaji
        totalGaji = gajiPokok + lembur;

        System.out.println("----------------------------");
        System.out.printf("Gaji Pokok   : %.0f\n", gajiPokok);
        System.out.printf("Lembur       : %.0f\n", lembur);
        System.out.printf("Total Gaji   : %.0f\n", totalGaji);

        input.close();
    }
}
