package finaltermP1_2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class ViazaneZivnosti {


    private int n;
    private int m;
    private int[] mesacneNaklady;
    private boolean[][] opravnenia;

    private int[] moznosti;

    private boolean[] uzBolo;
    private int najmensieNaklady = Integer.MAX_VALUE;
    private int[] najlepsiaMoznost = new int[n];

    public ViazaneZivnosti() {

        File f = new File("src/main/java/finaltermP1_2019/file.txt");

        try (Scanner sc = new Scanner(f)) {
            n = sc.nextInt();
            m = sc.nextInt();
            sc.nextLine();

            mesacneNaklady = new int[n];
            opravnenia = new boolean[n][m];
            moznosti = new int[n];
            uzBolo = new boolean[n];
            Arrays.fill(moznosti, -1);

            for (int i = 0; i < n; i++) {
                mesacneNaklady[i] = sc.nextInt();
                for (int j = 0; j < m; j++) {
                    opravnenia[i][j] = sc.nextBoolean();
                }
            }

            System.out.println("Mesacne Naklady:");
            for (int cost : mesacneNaklady) {
                System.out.print(cost + " ");
            }
            System.out.println();
            System.out.println("Opravnenia:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(opravnenia[i][j] + " ");
                }
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int[] getMesacneNaklady() {
        return mesacneNaklady;
    }

    public boolean[][] getOpravnenia() {
        return opravnenia;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ViazaneZivnosti vz = new ViazaneZivnosti();


        int n = vz.getN();
        int m = vz.getM();
        int[] mesacneNaklady = vz.getMesacneNaklady();
        boolean[][] opravnenia = vz.getOpravnenia();


        System.out.println("Mesacne Naklady:");
        for (int cost : mesacneNaklady) {
            System.out.print(cost + " ");
        }
        System.out.println();
        System.out.println("Opravnenia:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(opravnenia[i][j] + " ");
            }
            System.out.println();
        }

        vz.generuj(0);
        System.out.println(Arrays.toString(vz.najlepsiaMoznost));
        System.out.println(vz.najmensieNaklady);

    }



    public void generuj(int odIdx) {
        if (odIdx == n) {
            spracuj();
            return;
        }

        moznosti[odIdx] = -1;
        generuj(odIdx + 1);

        for (int i = 0; i < n; i++) {

            if (!uzBolo[i]) {
                uzBolo[i] = true;
                moznosti[odIdx] = i;
                generuj(odIdx + 1);
                uzBolo[i] = false;
            }
        }
    }




    private void vypis() {
        System.out.println(Arrays.toString(moznosti));
    }


    public void spracuj() {

        boolean[] majuDacoTrue = new boolean[m];
        int mesacneNakladyDokopy = 0;
        boolean vyhovuje = true;


        // ak jeden nema nieco true ale iny ma, tak je to ok
        // spocitam ich sumy

        for (int i = 0; i < moznosti.length; i++) {
            if (moznosti[i] != -1) {
                int indexTypka = moznosti[i];
                for (int j = 0; j < m; j++) {
                    if (opravnenia[indexTypka][j]) {
                        majuDacoTrue[j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < majuDacoTrue.length; i++) {
            if (majuDacoTrue[i] == false) {
                vyhovuje = false;
            }
        }


        if (vyhovuje) {
            for (int i = 0; i < moznosti.length; i++) {
                if (moznosti[i] != -1) {
                    mesacneNakladyDokopy += mesacneNaklady[moznosti[i]];
                }
            }

            if (mesacneNakladyDokopy < najmensieNaklady) {
                najmensieNaklady = mesacneNakladyDokopy;
                najlepsiaMoznost = moznosti.clone();

            }

        }


    }
}


