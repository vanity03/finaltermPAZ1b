package finaltermP1_2023;

import java.util.ArrayList;
import java.util.List;

public class DovolenkaBT {

    private int M = 0;
    private int H = 2;
    private int m = 2;
    private int h = 1;

    private List<String> moznosti = new ArrayList<>();

    public void generuj(String moznost, int pocetDniMore, int pocetDniHory, int pocetDniZaSebouMore, int pocetDniZaSebouHory) {
        if (pocetDniMore == M && pocetDniHory == H) {
            moznosti.add(moznost);
            return;
        }


        if (pocetDniMore < M && pocetDniZaSebouMore < m) {
            generuj(moznost + "M", pocetDniMore + 1, pocetDniHory, pocetDniZaSebouMore + 1, 0);
        }

        if (pocetDniHory < H && pocetDniZaSebouHory < h) {
            generuj(moznost + "H", pocetDniMore, pocetDniHory + 1, 0, pocetDniZaSebouHory + 1);
        }
    }
    public static void main(String[] args) {
        DovolenkaBT d = new DovolenkaBT();
        d.generuj("", 0, 0, 0, 0);

        for (String moznost : d.moznosti)  {
            System.out.print(moznost + " ");
        }
        System.out.println();
    }
}
