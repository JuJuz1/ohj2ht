/**
 * 
 */
package rekisteri;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 2.3.2023
 *
 */
public class Pokemon {
    
    private int ID;
    private String nimi;
    private int vahvuus;
    private int ikaID;
    private int elementtiID1;
    private int elementtiID2;
    private int evoluutio;
    private int evoluutioIDseuraava;
    private String lisatiedot;
    
    private static int seuraavaID = 1;

    
    
    /**
     * Tulostaa pokemonin tiedot.
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        // TODO: elementit ja ikaid ?
        out.println("ID: " + ID);
        out.println("Nimi: " + nimi);
        out.println("Vahvuus: " + vahvuus);
        out.println("IkaID: " + ikaID);
        out.println("Elementti1: " + elementtiID1);
        out.println("Elementti2: " + elementtiID2);
        out.println("Evoluutio: " + evoluutio);
        out.println("EvoluutioIDseuraava: " + evoluutioIDseuraava);
        out.println("Lisatiedot: " + lisatiedot);
    }
    
    
    /**
     * Apumetodi printstream tulostukselle
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pokemonille.
     */
    public void vastaa_pikachu() {
        nimi = "Pikachu";
        vahvuus = 50;
        ikaID = 3;
        elementtiID1 = 2;
        elementtiID2 = 3;
        evoluutio = 2;
        evoluutioIDseuraava = 0;
        lisatiedot = "Suuri jannite";
    }

    
    /**
     * Rekisteroi pokemonin
     * @return ID
     * @example
     * <pre name="test">
     *      Pokemon pikachu = new Pokemon();
     *      Pokemon charizard = new Pokemon();
     *      pikachu.getID() === 0;
     *      int n1 = pikachu.rekisteroi();
     *      charizard.getID() === 0;
     *      int n2 = charizard.rekisteroi();
     *      n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        ID = seuraavaID;
        seuraavaID++;
        return ID;
    }
    
    
    /**
     * @return ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * @param nimi jota tarkistetaan
     * @return true, jos nimi on oikein
     * muuten false
     * @example
     * <pre name="test">
     *      tarkistaNimi("Pikachu") === true;
     *      tarkistaNimi("pikachu") === false;
     *      tarkistaNimi("Pikachu ") === false;
     *      tarkistaNimi(" Pikachu") === false;
     *      tarkistaNimi("Pikachu1") === false;
     *      tarkistaNimi("P") === true;
     *      tarkistaNimi("") === false;
     *      tarkistaNimi("€") === false;
     * </pre>
     */
    public static boolean tarkistaNimi(String nimi) {
        return nimi.matches("[A-Z][a-z]*");
    }
    
    
    /**
     * Tarkistaa vahvuuden
     * @param vahvuus vahvuus
     * @return true jos suurempi kuin 0
     * muuten false
     * @example
     * <pre name="test">
     *      tarkistaVahvuus(0) === false;
     *      tarkistaVahvuus(1) === true;
     *      tarkistaVahvuus(-1) === false;
     * </pre>
     */
    public static boolean tarkistaVahvuus(int vahvuus) {
        return vahvuus > 0;
    }
    
   
    /**
     * @param evoluutio evoluutio väliltä 1-3
     * @return true jos välillä [1,3]
     * muuten false
     * @example
     * <pre name="test">
     *      tarkistaEvoluutio(2) === true;
     *      tarkistaEvoluutio(0) === false;
     *      tarkistaEvoluutio(5) === false;
     * </pre>
     */
    public static boolean tarkistaEvoluutio(int evoluutio) {
        return (1 <= evoluutio && evoluutio <= 3);
    }
    
    
    /**
     * @param seuraava seuraavan evoluution ID
     * @return true jos hyväksyttävä ID
     * muuten false
     * @example
     * <pre name="test">
     *      Pokemon uusi = new Pokemon();
     *      int temp = uusi.rekisteroi();
     *      tarkistaSeuraavaEvoluutio(temp) === true;
     *      tarkistaSeuraavaEvoluutio(temp+1) === false;
     *      tarkistaSeuraavaEvoluutio(0) === false;
     *      tarkistaSeuraavaEvoluutio(-1) === false;
     * </pre>
     */
    public static boolean tarkistaSeuraavaEvoluutio(int seuraava) {
        return (0 < seuraava && seuraava < seuraavaID);
    }
    
    
    /**
     * @return pokemonin nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * @param args ei kayt.
     */
    public static void main(String[] args) {
        Pokemon pikachu = new Pokemon();
        Pokemon charizard = new Pokemon();
        
        pikachu.tulosta(System.out);
        
        pikachu.rekisteroi();
        
        pikachu.vastaa_pikachu();
        charizard.vastaa_pikachu();
        pikachu.tulosta(System.out);
        charizard.tulosta(System.out);
    
    }

}
