package finaltermP2_2023;

public class HrackyPreMicu {


    private int pocetHraciek = 4;
    private int[] moznost = new int[pocetHraciek]; // lebo 4 hracky?


    private int[][] predajca = {{15, 50, 25, -1, 10, 25},
            {20, 45, -1, 25, 20, 22},
            {30, 100, 30, 30, 30, 30}};

    private int najlepsiaCena = Integer.MAX_VALUE;
    private int[] najlepsiaMoznost = new int[pocetHraciek];
    public static void main(String[] args) {

        // 25 + 25 + 10 + 22 + 15

        // p0 macky.sk = {15, 50, 25, -1, 10, 25}
        // p1 C&M = {20, 45, -1, 25, 20, 22} - 67
        // p2 P3 = {30, 100, 30, 30, 30, 30}

        // predajca[i][0] - postovne
        // predajca[i][1] - postovne zadarmo
        // predajca[i][2 - 5] - hracky
        HrackyPreMicu h = new HrackyPreMicu();
        h.generuj(0);
        System.out.println(h.najlepsiaCena);
        h.vyprintuj(h.najlepsiaMoznost);

    }


    // 0, 0, 2, 1
    // 0, 0, 0, 0
    public void generuj(int odIdx) {
        if (odIdx == moznost.length) {
            spracuj();
            // spracuj
            return;
        }


        // generujem ktory predajcovia by mohli byt


        // predajca.length - asi pocetHraciek
        for (int cisloPredajcu = 0; cisloPredajcu < predajca.length; cisloPredajcu++) {
            moznost[odIdx] = cisloPredajcu;
            generuj(odIdx + 1);
        }
    }


    public void spracuj() {
        // indexy v matici predajcov - ceny su na pozicii 2, 3, 4, 5
        int[] cenyHraciek = {2, 3, 4, 5};

        // kazdemu predajcovi pripocitavame ceny hraciek ktore u neho kupime
        int[] nakup = new int[predajca.length];

        // postovne pre kazdeho predajcu - {15, 20, 30}
        int[] postovne = new int[predajca.length];

        // postovne zadarmo pre kazdeho predajcu - {50, 45, 100}
        boolean[] zadarmo = new boolean[predajca.length];

        // p0 macky.sk = {15, 50, 25, -1, 10, 25}
        // p1 C&M = {20, 45, -1, 25, 20, 22} - 67
        // p2 P3 = {30, 100, 30, 30, 30, 30}

        // moznost: 0, 1, 0, 2

        // nakup: 25+10, 25, 30 = 35, 25, 30

        for (int i = 0; i < pocetHraciek; i++) {
            int indexPredavajuceho = moznost[i];


            // {{15, 50, 25, -1, 10, 25}, {}, {}, ....}

            // cena hracky je cena z indexu predavajuceho - pri 0 0 1 0 je to predajca[0] a riesim prvu hracku cize cenyHraciek[i] - 2, celkovo predajca[0][2]
            int cenaHracky = predajca[indexPredavajuceho][cenyHraciek[i]];

            // ak tu hracku predava
            if (cenaHracky != -1) {
                nakup[indexPredavajuceho] += cenaHracky;
                postovne[indexPredavajuceho] = predajca[indexPredavajuceho][0];
                zadarmo[indexPredavajuceho] = (nakup[indexPredavajuceho] >= predajca[indexPredavajuceho][1]);
            }

            else {
                return;
            }
        }


        // nakup: 25+10, 25, 30 = 35, 25, 30
        int celkovaCena = 0;
        // z celeho nakupu spocitam ceny
        for (int i = 0; i < nakup.length; i++) {
            celkovaCena += nakup[i];

            // ak neni cena natolko velka aby bol shipping free tak pripocitam aj shipping
            if (nakup[i] > 0 && zadarmo[i] == false) {
                celkovaCena += postovne[i];
            }
        }

        if (celkovaCena < najlepsiaCena) {
            najlepsiaCena = celkovaCena;
            najlepsiaMoznost = moznost.clone();
        }
    }

        public void vyprintuj(int[] najlepsiaMoznost) {
            for (int num : najlepsiaMoznost) {
                System.out.print(num + " ");
            }
            System.out.println();
        }


    }


