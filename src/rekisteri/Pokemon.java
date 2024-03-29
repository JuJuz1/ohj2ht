/**
 * 
 */
package rekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Sisältää pokemonin tiedot (attribuutit). 
 * Osaa antaa pokemonille uniikin ID:n (rekisteroi). 
 * Osaa tarkistaa kenttään syötettävän tiedon oikeellisuuden. 
 * Osaa muuttaa merkkijonon pokemonin tiedoiksi. 
 * Osaa antaa pokemonin tiedot merkkijonona.
 * Osaa asettaa merkkijonon kentän sisällöksi.
 * TODO: Asettaa seuraavan evoluution id
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 29.4.2023
 *
 */
public class Pokemon implements Cloneable {
    
    private int ID;                     // ID-luku, jolla voidaan erottaa pokemon muista. (ID = 0 on oletus ja tarkoittaa virheellistä)
    private String nimi;                // Pokemonin nimi
    private int vahvuus;                // Lukuarvo, joka kuvaa pokemonin vahvuutta taistelussa
    private int ikaID;                  // Liittää pokemoniin yhden ikaryhmän erillisestä tiedostosta
    private int elementtiID1;           // Liittää pokemoniin elementin toisesta tiedostosta
    private int elementtiID2;           // -||-
    private int evoluutio;              // Kuinka mones evoluutio (1-3)
    private int evoluutioIDseuraava;    // Pokemonin seuraavan evoluution ID (0 jos ei ole)
    private String lisatiedot;          // Mitä tahansa lisätietoa pokemonista
    
    private static int seuraavaID = 1;  // Seuraava vapaa ID-luku. Käytetään kun pokemonille annetaan uusi ID.


    /**
     * Oletusmuodostaja
     */
    public Pokemon() {
        // Attribuuttien arvot asetetaan vastaa_pikachu()-metodilla ja nykyään "lomakkeista" :D
    }
    
    
    /**
     * Muodostaja tiedostosta
     * @param rivi rivi
     * @example
     * <pre name="test">
     *      Pokemon uusi = new Pokemon("3|Uusi|69|2|3|1|8|0|Olen uusi");
     *      uusi.getNimi() === "Uusi"; uusi.getElementtiID(2) === 1;
     *      uusi.getIkaID() === 2;
     * </pre>
     */
    public Pokemon(String rivi) {
        String[] tiedot = parse(rivi);
        
        this.rekisteroi();
        // Ei käytetä ID (eli indeksiä 0)
        nimi = tiedot[1];
        vahvuus = Mjonot.erotaInt(tiedot[2], -1);
        ikaID = Mjonot.erotaInt(tiedot[3], -1);
        elementtiID1 = Mjonot.erotaInt(tiedot[4], -1);
        elementtiID2 = Mjonot.erotaInt(tiedot[5], -1);
        evoluutio = Mjonot.erotaInt(tiedot[6], -1);
        evoluutioIDseuraava = Mjonot.erotaInt(tiedot[7], -1);
        if (tiedot.length < 9) lisatiedot = "";
        else lisatiedot = tiedot[8];
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
     * Testissä käyt.
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
     * @return ikaID
     */
    public int getIkaID() {
        return ikaID;
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
     * @param luku vahvuus tai ika
     * @return true, jos suurempi kuin 0. false, jos 0 tai pienempi
     * @example
     * <pre name="test">
     *      tarkistaVahvuusTaiIka(0) === false;
     *      tarkistaVahvuusTaiIka(1) === true;
     *      tarkistaVahvuusTaiIka(-1) === false;
     * </pre>
     */
    public static boolean tarkistaVahvuusTaiIka(int luku) {
        return luku > 0;
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
     * @return Vahvuus
     */
    public int getVahvuus() {
        return vahvuus;
    }
    
    
    /**
     * @return evoluutio
     */
    public int getEvoluutio() {
        return evoluutio;
    }
    
    
    /**
     * @return lisatiedot
     */
    public String getLisatiedot() {
        return lisatiedot;
    }
    
    
    /**
     * @return seuraavan evoluution id
     */
    public int getEvoluutioIDSeuraava() {
        return evoluutioIDseuraava;
    }
    
    /**
     * Laskee pokemonin elementtien lkm
     * @return elementtien lkm
     * @example
     * <pre name="test">
     *      Pokemon p = new Pokemon();
     *      p.rekisteroi(); p.vastaa_pikachu();
     *      p.getElementtienLkm() === 2;
     *      p.setElementtiID(1, 0);
     *      p.getElementtienLkm() === 1;
     * </pre>
     */
    public int getElementtienLkm() {
        int e = 0;
        if (this.getElementtiID(1) != 0) e++;
        if (this.getElementtiID(2) != 0) e++;
        return e;
    }
    
    
    /**
     * Asetetaan pokemonin nimi
     * @param nimi Uusi nimi
     * @return null, jos onnistui. Muuten virhe merkkijonona
     */
    public String setNimi(String nimi) {
        if (tarkistaNimi(nimi)) {
            this.nimi = nimi;
            return null;
        }
        return "Nimi ei ole oikean muotoinen";
    }
    
    
    /**
     * Asetetaan elementin ID pokemonille
     * @param monesko eka vai toka elementti
     * @param elementinID mikä elementin ID on
     * elementinID on 0, jos halutaan poistaa elementti pokemonilta
     * @return null jos onnistuu, muuten tyhjä
     */
    public String setElementtiID(int monesko, int elementinID) {
        if (0 <= elementinID && elementinID < 7) {
            if (monesko == 1) {
                this.elementtiID1 = elementinID;
            }
            if (monesko == 2) {
                this.elementtiID2 = elementinID;
            }
            return null;
        }
        return ""; // Virhe ehkä
    }
    
    
    /**
     * Asettaa pokemonin vahvuuden
     * @param vahvuusS Uusi vahvuus
     * @return null, jos onnistuu, muuten virhe merkkijonona
     */
    public String setVahvuus(String vahvuusS) {
        int vahvuusI = Mjonot.erotaInt(vahvuusS, -1);
        if (tarkistaVahvuusTaiIka(vahvuusI)) {
            this.vahvuus = vahvuusI;
            return null;
        }
        return "Vahvuus ei ole oikean muotoinen";
    }
    
    
    /**
     * Asetetaan ikä tietylle ikäalueelle
     * @param ikaS ika merkkijonona
     * @return null, jos onnistuu, muuten virhe merkkijonona
     */
    public String setIka(String ikaS) {
        int ikaI = Mjonot.erotaInt(ikaS, -1);
        if (tarkistaVahvuusTaiIka(ikaI)) {
            for (int i = 10; i <= 40; i+=10) {
                int id = i / 10;
                if (ikaI <= i) {
                    this.ikaID = id;
                    return null;
                }
            }
            if (40 < ikaI) this.ikaID = 5;
            return null;
        }
        return "Ikä ei ole oikeaa muotoa";
    }
    
    
    /**
     * Asettaa pokemonin evoluution
     * @param evoluutioS Evoluution numero
     * @return null, jos onnistuu. Muuten virhe merkkijonona
     */
    public String setEvoluutio(String evoluutioS) {
        int evoluutioI = Mjonot.erotaInt(evoluutioS, -1);
        if (tarkistaEvoluutio(evoluutioI)) {
            this.evoluutio = evoluutioI;
            return null;
        }
        return "Anna luku 1, 2 tai 3";
    }
    
    
    // TODO: setEvoluutioIDSeuraava(String evID)
    
    
    /**
     * Asettaa pokemonin lisätiedot
     * @param tiedot Lisätiedot merkkijonona
     * @return null, jos onnistuu
     */
    public String setLisatiedot(String tiedot) {
        this.lisatiedot = tiedot;
        return null;
    }
    
    
    /**
     * Pilkkoo pokemonin tiedot taulukkoon
     * @param jono annettu merkkijono
     * @return tiedot merkkijonossa
     * Testit pääohjelmassa
     */
    public static String[] parse(String jono) {
        String[] tiedot = jono.split(" *\\| *");
        return tiedot;
    }
    
    
    /**
     * Muutetaan pokemonin tiedot tiedostoon tallennettavaksi
     * @example
     * <pre name="test">
     *      Pokemon uusi = new Pokemon("2|Pika|12|3|1|2|3|0|valiaikainen");
     *      uusi.rekisteroi();
     *      uusi.toString().startsWith("2|Pika|12|") === true;
     *      uusi.toString().startsWith("2|Aku|12|") === false;
     * </pre>
     */
    @Override
    public String toString() {
        return getID() + "|"
                + getNimi() + "|"
                + vahvuus + "|"
                + getIkaID() + "|"
                + getElementtiID(1) + "|"
                + getElementtiID(2) + "|"
                + evoluutio + "|"
                + evoluutioIDseuraava + "|"
                + lisatiedot;
    }
    
    
    @Override
    public Pokemon clone() throws CloneNotSupportedException {
        Pokemon uusi;
        uusi = (Pokemon) super.clone();
        return uusi;
    }
    
    
    /**
     * Antaa vertailukelpoisena tietyn kentän tiedot
     * @param n jos 1 tai 2 nimi, jos 3 tai 4 vahvuus, jos 5 6 ikaID
     * @return Kenttä merkkijonona
     */
    public String getAvain(int n) {
        if (n == 1 || n == 2) return nimi;
        if (n == 3 || n == 4) return String.format("%05d", vahvuus);
        if (n == 5 || n == 6) return String.format("%05d", ikaID);
        return "";
        }
    
    
    /**
     * Vertailija, joka osaa verrata pokemonin tiettyä kenttää toiseen pokemoniin
     * @author Juuso ja Elias
     * @version 28.4.2023
     *
     */
    public static class Vertailija implements Comparator<Pokemon> {
        private int n;
        
        /**
         * Muodostaja
         * @param n Mitä kenttä vertaillaan. 1=nimi, 2=vahvuus, 3=ika
         */
        public Vertailija(int n) {
            this.n = n;
        }
        
        @Override
        public int compare(Pokemon p1, Pokemon p2) {
            return p1.getAvain(n).compareTo(p2.getAvain(n));
        }
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
        
        String rivi = "1 |Pikachu       |1337    |1     |6          "
                + "|0          |2         |5                      |           |";
        String[] tiedot = parse(rivi);
        
        for (String tieto : tiedot) {
            System.out.println(tieto);
        }
    }
}