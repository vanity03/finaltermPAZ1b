package finaltermP1_2024;

import java.util.ArrayList;
import java.util.List;


public class Osoba {
    /**
     * Zoznam deti osoby
     */
    private List<Osoba> deti = new ArrayList<>();

    // Boolean fields for answers to the three questions
    private boolean odpoved1;
    private boolean odpoved2;
    private boolean odpoved3;

    /**
     * Konstruktor osoby v strome potomkov
     *
     * @param odpoved1 odpoved na prvu otazku
     * @param odpoved2 odpoved na druhu otazku
     * @param odpoved3 odpoved na tretiu otazku
     */
    public Osoba(boolean odpoved1, boolean odpoved2, boolean odpoved3) {
        this.odpoved1 = odpoved1;
        this.odpoved2 = odpoved2;
        this.odpoved3 = odpoved3;
    }

    /**
     * Prida osobe dieta
     *
     * @param dieta referencia na pridavane dieta
     */
    public void pridajDieta(Osoba dieta) {
        deti.add(dieta);
    }

    /**
     * Vrati celkovy pocet potomkov osoby
     */
    public int pocetPotomkov() {
        int pocetPotomkovDeti = 0;
        for (Osoba dieta : deti)
            pocetPotomkovDeti += dieta.pocetPotomkov();

        return pocetPotomkovDeti + deti.size();
    }

    /**
     * Vypise rodostrom osoby
     */
    public void vypisRodostrom() {
        System.out.println(this);
        for (Osoba dieta : deti)
            dieta.vypisRodostrom();
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "odpoved1=" + odpoved1 +
                ", odpoved2=" + odpoved2 +
                ", odpoved3=" + odpoved3 +
                '}';
    }

    /**
     * Pocita osoby so zhodnymi odpovedami ako Styrko z lazov
     */
    public int pocetZhodnychNazory(Osoba root) {
        int pocet = 0;
        if (this.odpoved1 == root.odpoved1 && this.odpoved2 == root.odpoved2 && this.odpoved3 == root.odpoved3) {
            pocet = 1;
        }
        for (Osoba dieta : deti) {
            pocet += dieta.pocetZhodnychNazory(root);
        }
        return pocet;
    }

    /**
     * Main s vytvorenim stromu potomkov pre Styrko z lazov
     */
    public static void main(String[] args) {
        // Creating the root person (Styrko z lazov) with their answers
        Osoba styrko = new Osoba(true, false, true);

        // Creating other persons with their answers
        Osoba janko = new Osoba(true, false, true);
        Osoba jozko = new Osoba(false, false, true);
        Osoba maria = new Osoba(true, false, true);
        Osoba karol = new Osoba(true, true, false);
        Osoba lucia = new Osoba(true, false, true);
        Osoba petra = new Osoba(false, false, true);

        // Building the family tree
        styrko.pridajDieta(janko);
        styrko.pridajDieta(jozko);
        styrko.pridajDieta(maria);
        styrko.pridajDieta(karol);
        karol.pridajDieta(lucia);
        karol.pridajDieta(petra);

        // Print the family tree
        styrko.vypisRodostrom();

        // Calculate and print the number of people with the same views as Styrko z lazov
        int pocetZhodnych = styrko.pocetZhodnychNazory(styrko);
        System.out.println("Pocet osob so zhodnym nazorom ako Styrko z lazov: " + (pocetZhodnych - 1)); // Subtract 1 to exclude Styrko z lazov themselves
    }
}
