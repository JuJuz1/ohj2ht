/**
 * 
 */
package rekisteri;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Sisältää pokemonin tiedot (attribuutit). 
 * Osaa antaa pokemonille uniikin ID:n (rekisteroi). 
 * Osaa tarkistaa kenttään syötettävän tiedon oikeellisuuden. 
 * TODO: Osaa muuttaa merkkijonon pokemonin tiedoiksi. 
 * Osaa antaa pokemonin tiedot merkkijonona.
 * TODO: Osaa antaa i:nnen kentän tiedon.
 * TODO: Osaa asettaa merkkijonon kentän sisällöksi.
 * @author Juuso Piippo & Elias Lehtinen
 * @version 14.3.2023
 *
 */
public class Pokemon {
    
    private int ID;                     // ID-luku, jolla voidaan erottaa pokemon muista. (ID = 0 on oletus ja tarkoittaa virheellistä)
    private String nimi;                // Pokemonin nimi
    private int vahvuus;                // Lukuarvo, joka kuvaa pokemonin vahvuutta taistelussa
    private int ikaID;                  // Liittää pokemoniin yhden ikaryhmän erillisestä tiedostosta
    private int elementtiID1;           // Liittää pokemoniin elementin toisesta tiedostosta
    private int elementtiID2;           // -//-
    private int evoluutio;              // Kuinka mones evoluutio (1-3)
    private int evoluutioIDseuraava;    // Pokemonin seuraavan evoluution ID (0 jos ei ole)
    private String lisatiedot;          // Mitä tahansa lisätietoa pokemonista
    
    private static int seuraavaID = 1;  // Seuraava vapaa ID-luku. Käytetään kun pokemonille annetaan uusi ID.


    /**
     * Oletusmuodostaja
     */
    public Pokemon() {
        // Attribuuttien arvot asetetaan vastaa_pikachu()-metodilla ja myöhemmin käyttäjän syötteestä
    }
    
    
    /**
     * Tulostaa pokemonin tiedot.
     * @param out tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        // TODO: Muuta: Tällä hetkellä tulostetaan IkaID ja elementtiID:t iän ja elementtien sijasta
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
     * Apumetodi elementtien tulostamisen testaamista vartern
     * TODO: poista kun ei tarvita
     */
    public void asetaTyhjaElementti() {
        elementtiID2 = 0;
    }

    
    /**
     * Rekisteroi pokemonin (Antaa seuraavan vapaan ID-luvun)
     * @return ID, joka asetettiin pokemonille
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
     * @return pokemonin ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * Palauttaa pokemonin elementin ID:n
     * @param nro Kumpi elementti palautetaan (1 tai 2)
     * @return Elementin ID:n
     * @example
     * <pre name="test">
     *  Pokemon pikachu = new Pokemon();
     *  pikachu.vastaa_pikachu();
     *  pikachu.getElementtiID(1) === 2;
     *  pikachu.getElementtiID(2) === 3;
     *  pikachu.getElementtiID(3) === 0;
     *  pikachu.getElementtiID(0) === 0;
     * </pre>
     */
    public int getElementtiID(int nro) {
        if (nro > 2 || nro < 1) return 0;
        if (nro == 1) return elementtiID1;
        return elementtiID2;
    }
    
    
    /**
     * Tarkistaa pokemonille annetun nimen oikeellisuuden.
     * Oikean muotoinen nimi alkaa isolla kirjaimella ja sisältää pelkkiä kirjaimia.
     * @param nimi jota tarkistetaan
     * @return true, jos nimi on oikein. false, jos nimi on vääränlainen
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
     * Tarkistaa, että vahvuus on suurempi kuin 0.
     * @param vahvuus vahvuus
     * @return true, jos suurempi kuin 0. false, jos 0 tai pienempi
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
     * Tarkistaa, että evoluution arvo on välillä [1,3]
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
     * Tarkistaa, että seuraavan evoluution ID on oikean muotoinen.
     * Seuraavan evoluution ID:n täytyy olla käytössä oleva ID.
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
     * Testipääohjelma Pokemon-luokalle
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
