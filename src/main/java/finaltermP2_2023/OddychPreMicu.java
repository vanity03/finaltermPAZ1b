package finaltermP2_2023;

import java.util.Arrays;
import java.util.Map;

public class OddychPreMicu {

    public static void main(String[] args) {
        int[][] mapa = {{1, 1, 1, 1},
                        {1, 2, 1, 1},
                        {1, 3, 5, 1},
                        {1, 1, 1, 1}};

        // | 1 1 1 1 |
        // | 1 2 1 1 |
        // | 1 3 5 1 |
        // | 1 1 1 1 |
    }


    ////////// NEFUNGUJE
    private static int dijkstra(int[][] mapa, int startX, int startY, int endX, int endY) {
        int riadky = mapa.length;
        int stlpce = mapa[0].length;

        int[][] energia = new int[riadky][stlpce];
        boolean[][] navstivene = new boolean[riadky][stlpce];

        for (int[] riadok : energia) {
            Arrays.fill(riadok, Integer.MAX_VALUE);
        }

        energia[startX][startY] = 0;

        while (true) {
            int minEnergia = Integer.MAX_VALUE;
            int[] aktualny = null;

            // prechadzam stlpec po stlpci
            for (int i = 0; i < riadky; i++) {
                for (int j = 0; j < stlpce; j++) {
                    // ak este dane pole nebolo navstivene a energia na
                    // tomto poli je mensia ako minEnergia
                    // tak je minEnergia hodnota toho pola
                    // a aktualne som na tomto poli
                    if (!navstivene[i][j] && energia[i][j] < minEnergia) {
                        minEnergia = energia[i][j];
                        aktualny = new int[]{i, j};
                    }
                }
            }

            if (aktualny == null) {
                break;
            }

            int x = aktualny[0];
            int y = aktualny[1];

            if (x == endX && y == endY) {
                return minEnergia;
            }

            navstivene[x][y] = true;

            int[][] smery = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] smer : smery) {
                int noveX = x + smer[0];
                int noveY = y + smer[1];

                if (noveX >= 0 && noveX < riadky && noveY >= 0 && noveY < stlpce && !navstivene[noveX][noveY]) {
                    int rozdiel = mapa[noveX][noveY] - mapa[x][y];
                    int novaEnergia = minEnergia + 1;

                    if (rozdiel > 0) {
                        novaEnergia += rozdiel * rozdiel;
                    }

                    if (novaEnergia < energia[noveX][noveY]) {
                        energia[noveX][noveY] = novaEnergia;
                    }
                }
            }
        }

        return -1;

    }
}
