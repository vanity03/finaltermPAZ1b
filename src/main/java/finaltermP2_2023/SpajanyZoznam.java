package finaltermP2_2023;

/**
 * Trieda zapuzdrujuca spajany zoznam a manipulaciu s nim
 */
public class SpajanyZoznam {

    /**
     * Vnorena trieda (nested static class) reprezentujuca jeden uzol spajaneho zoznamu
     */
    private static class Uzol {
        int hodnota;
        Uzol dalsi;
    }

    /**
     * Referencia na prvy uzol spajaneho zoznamu
     */
    private Uzol prvy = null;

    /**
     * Prida novu hodnotu na zaciatok spajaneho zoznamu
     * @param hodnota pridavana hodnota
     */
    public void pridajNaZaciatok(int hodnota) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = hodnota;
        pridavany.dalsi = prvy;
        prvy = pridavany;
    }

    @Override
    public String toString() {
        String vysledok = "[";
        Uzol aktualny = prvy;
        while (aktualny != null) {
            if (aktualny != prvy)
                vysledok += ", ";

            vysledok += aktualny.hodnota;
            aktualny = aktualny.dalsi;
        }

        return vysledok + "]";
    }

    /**
     * Vrati sucet hodnot ulozenych v spajanom zozname
     */
    public int sucet() {
        // Referencia na uzol zoznamu, na ktorom sa prave nachadzame
        Uzol aktualny = prvy;
        // Premenna, v ktorej akumulujeme sucet
        int vysledok = 0;
        // Kym sme na nejakom uzle ...
        while (aktualny != null) {
            // Priratame hodnotu uzla
            vysledok += aktualny.hodnota;
            // Presunieme sa na dalsi uzol v zozname
            aktualny = aktualny.dalsi;
        }

        return vysledok;
    }

    // sucet cisel ktore ostanu v zozname ma byt mensi
    // ako hodnota zadana parametrom dlzka
    public void orezPodDlzku(int dlzka) {
        Uzol aktualny = prvy;
        int sucet = 0;

        if (prvy.hodnota >= dlzka) {
            prvy = null;
            aktualny.dalsi = null;
        }


        while (aktualny != null) {
            if (sucet + aktualny.hodnota >= dlzka) {
                break;
            }
            // zoberiem do suctu hodnotu aktualneho
            sucet += aktualny.hodnota;
            if (aktualny.dalsi != null && sucet + aktualny.dalsi.hodnota >= dlzka) {
                aktualny.dalsi = null;
                break;
            }

            // idem na dalsi
            aktualny = aktualny.dalsi;
        }
    }


    public static void main(String[] args) {
        SpajanyZoznam s = new SpajanyZoznam();
        s.pridajNaZaciatok(14);
        s.pridajNaZaciatok(6);
        s.pridajNaZaciatok(2);
        s.pridajNaZaciatok(10);
        s.pridajNaZaciatok(5);
        s.pridajNaZaciatok(5);
        s.pridajNaZaciatok(8);

        System.out.println(s.sucet());
        s.orezPodDlzku(142);
        System.out.println(s);


    }
}

