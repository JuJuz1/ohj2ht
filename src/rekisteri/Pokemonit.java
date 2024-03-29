/**
 * 
 */
package rekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Sisältää taulukon pokemoneja (ja pokemonien lukumäärän sekä taulukon pituuden).
 * Taulukon koko kasvaa automaattisesti tarpeen mukaan.
 * Osaa lisätä ja poistaa pokemonin.
 * Osaa lajitella pokemonit järjestykseen nimen, iän tai vahvuuden mukaan.
 * Osaa etsiä pokemoneja taulukosta nimen perusteella.
 * Lukee ja kirjoittaa pokemonit tiedostoon
 * Osaa vertailla kahta pokemonia kaksintaistelussa
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 29.4.2023
 *
 */
public class Pokemonit {

    private String tiedostoNimi = "pokemonit.dat";
    private int lkm;
    private int maxLkm = 8;
    private Pokemon[] taulukko = new Pokemon[maxLkm];
    private boolean muutettu = false;

    /**
     * Oletusmuodostaja
     */
    public Pokemonit() {
        //
    }
    
    
    /**
     * Melkeinpä vain luetiedostosta() testaamista varten
     * @param tiednimi tiedostonimi
     */
    public Pokemonit(String tiednimi) {
        tiedostoNimi = tiednimi;
    }


    /**
     * Lisaa pokemonin taulukkoon. Jos samalla ID:llä löytyy pokemon, se korvataan
     * @param pokemon pokemon joka lisataan
     * @example
     * <pre name="test">
     *      #THROWS SailoException 
     *      Pokemonit pokemonit = new Pokemonit();
     *      Pokemon pikachu = new Pokemon(), charizard = new Pokemon();
     *      pokemonit.getLkm() === 0;
     *      pokemonit.lisaa(pikachu); pokemonit.getLkm() === 1;
     *      pokemonit.lisaa(charizard); pokemonit.getLkm() === 2;
     *      pokemonit.lisaa(pikachu); pokemonit.getLkm() === 3;
     *      pokemonit.getPokemon(0) === pikachu;
     *      pokemonit.getPokemon(1) === charizard;
     *      pokemonit.getPokemon(2) === pikachu;
     *      pokemonit.getPokemon(1) == pikachu === false;
     *      pokemonit.getPokemon(1) == charizard === true;
     *      pokemonit.getPokemon(3) === pikachu; #THROWS IndexOutOfBoundsException 
     *      pokemonit.lisaa(pikachu); pokemonit.getLkm() === 4;
     *      pokemonit.lisaa(pikachu); pokemonit.getLkm() === 5;
     *      pokemonit.lisaa(pikachu); pokemonit.lisaa(pikachu);
     *      pokemonit.lisaa(pikachu); 
     *      pokemonit.lisaa(pikachu);
     * </pre>
     */
    public void lisaa(Pokemon pokemon) {
        if (taulukko.length <= lkm) {
            int uusikoko = maxLkm * 2;
            Pokemon[] uusitaulukko = new Pokemon[uusikoko];

            for (int i = 0; i < taulukko.length; i++) {
                uusitaulukko[i] = taulukko[i];
            }

            taulukko = uusitaulukko;
            maxLkm = uusikoko;

        }
        taulukko[lkm] = pokemon;
        lkm++;
        
        muutettu = true;
    }
    
    
    /**
     * Korvataan tai lisätään pokemon, riippuen löytyykö 
     * samalla id:llä toista pokemonia. Jos ei, niin lisätään
     * @param pokemon pokemon
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #PACKAGEIMPORT
     * Pokemonit pokemonit = new Pokemonit();
     * Pokemon p1 = new Pokemon(), p2 = new Pokemon();
     * p1.rekisteroi(); p2.rekisteroi();
     * pokemonit.getLkm() === 0;
     * pokemonit.korvaaTaiLisaa(p1); pokemonit.getLkm() === 1;
     * pokemonit.korvaaTaiLisaa(p2); pokemonit.getLkm() === 2;
     * Pokemon p3 = p1.clone();
     * p3.setNimi("Pika");
     * pokemonit.korvaaTaiLisaa(p3); pokemonit.getLkm() === 2;
     * pokemonit.korvaaTaiLisaa(new Pokemon(
     * "2|Pikachu|50|3|2|3|2|0|Suuri jannite"
     * ));
     * pokemonit.getLkm() === 3;
     */
    public void korvaaTaiLisaa(Pokemon pokemon) {
        int id = pokemon.getID();
        for (int i = 0; i < lkm; i++) {
            if ( taulukko[i].getID() == id ) {
                taulukko[i] = pokemon;
                muutettu = true;
                return;
            }
        }
        lisaa(pokemon);
    }

    
    /**
     * Poistaa pokemonin taulukosta id:n perusteella
     * @param id Poistettavan ID
     * @example
     * <pre name="test">
     *      #THROWS SailoException 
     *      Pokemonit pokemonit = new Pokemonit();
     *      Pokemon pikachu = new Pokemon();
     *      Pokemon charizard = new Pokemon();
     *      Pokemon pikachu2 = new Pokemon();
     *      charizard.vastaa_pikachu();
     *      pokemonit.getLkm() === 0;
     *      pokemonit.lisaa(pikachu); 
     *      pokemonit.getLkm() === 1;
     *      pokemonit.lisaa(charizard);
     *      pokemonit.getLkm() === 2;
     *      pokemonit.lisaa(pikachu2);
     *      pokemonit.getLkm() === 3;
     *      pokemonit.getPokemon(0) === pikachu;
     *      pokemonit.getPokemon(1) === charizard;
     *      pokemonit.getPokemon(2) === pikachu2;
     *      pokemonit.poista(charizard.getID());
     *      pokemonit.getLkm() === 2;
     *      pokemonit.getPokemon(1) === pikachu2;
     * </pre>
     */
    public void poista(int id) {
        int i, i2 = -1;
        for (i = 0; i < lkm; i++) {
            if (taulukko[i].getID() == id && i2 == -1) {
                i2 = i;
            } else if (i2 > -1) {
                taulukko[i2] = taulukko[i];
                i2++;
            }
        }
        if (i2 > -1) lkm--;
        muutettu = true;
    }
    

    /**
     * @return Olion sisältämien pokemonien lkm
     */
    public int getLkm() {
        return lkm;
    }


    /**
     * Palauttaa pokemonin taulukon indeksistä i.
     * HUOM: Ei liity pokemonin ID-lukuun.
     * @param i indeksi
     * @return pokemon paikassa i
     * @throws IndexOutOfBoundsException heitetään jos ei ole indeksissä pokemonia
     * @example
     */
    public Pokemon getPokemon(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return taulukko[i];
    }


    /**
     * Lukee jäsenistön tiedostosta.
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "pokemonitTest.dat";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";ID|Pokemonin nimi|vahvuus |ikä ID|elementti1 |elementti2 |evoluutio |seuraavan evoluution ID|lisatiedot |\n"
     *   + "1 |Pikachu       |1337    |1     |6          |0          |2         |5                      |    lisätiedot      |\n"
     *   + "2 |Charizard     |20      |4     |1          |4          |3         |0                      | |");
     *   Pokemonit p = new Pokemonit(tiedosto);
     *   p.lueTiedostosta();
     *   p.getLkm() === 2;
     *   p.getPokemon(0).getNimi() === "Pikachu";
     *   p.getPokemon(1).getNimi() === "Charizard";
     *   p.getPokemon(0).getElementtiID(1) === 6;
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     * </pre>
    */
    public void lueTiedostosta() throws SailoException {
        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {
            String rivi;
            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.startsWith(";")) continue;
                Pokemon pokemon = new Pokemon(rivi);
                lisaa(pokemon);
            }
            muutettu = false;
        } catch (FileNotFoundException e) {
            throw new SailoException(
                    "Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch (IOException e) {
            throw new SailoException(
                    "Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }


    /**
     * Tallentaa pokemonit tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "pokemonitTest.dat";
     *   String tiedostobak = "pokemonitTest.bak";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.tuhoaTiedosto(tiedostobak);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";ID|Pokemonin nimi|vahvuus |ikä ID|elementti1 |elementti2 |evoluutio |seuraavan evoluution ID|lisatiedot |\n"
     *   + "1 |Pikachu       |1337    |1     |6          |0          |2         |5                      |   testi        |\n"
     *   + "2 |Charizard     |20      |4     |1          |4          |3         |0                      |     tesi60     |");
     *   Pokemonit pThrows = new Pokemonit("eiOle");
     *   pThrows.lueTiedostosta(); #THROWS SailoException
     *   Pokemonit p = new Pokemonit(tiedosto);
     *   p.lueTiedostosta();
     *   p.getLkm() === 2;
     *   p.getPokemon(0).getNimi() === "Pikachu";
     *   p.getPokemon(1).getNimi() === "Charizard";
     *   Pokemon t = new Pokemon();
     *   t.rekisteroi(); t.vastaa_pikachu();
     *   p.lisaa(t);
     *   p.tallenna();
     *   
     *   Pokemonit pDat = new Pokemonit(tiedosto);      pDat.lueTiedostosta();
     *   Pokemonit pBak = new Pokemonit(tiedostobak);   pBak.lueTiedostosta();
     *   pDat.getPokemon(0).getNimi() === "Pikachu";  pBak.getPokemon(0).getNimi() === "Pikachu";
     *   
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.tuhoaTiedosto(tiedostobak);
     * </pre>
     */
    public void tallenna() throws SailoException {
            if ( !muutettu ) return; 
            File fbak = new File(getTiedostonNimi().replace(".dat", ".bak")); 
            File ftied = new File(getTiedostonNimi()); 
            fbak.delete(); // if .. System.err.println("Ei voi tuhota"); 
            ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä"); 
     
            try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) { 
                fo.println(";ID|Pokemonin nimi|vahvuus |ikä ID|elementti1 |elementti2"
                +" |evoluutio |seuraavan evoluution ID|lisatiedot |"); 
                for (int i = 0; i < lkm; i++) { 
                    fo.println(this.getPokemon(i).toString()); 
                } 
                //} catch ( IOException e ) { // ei heitä poikkeusta 
                //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage()); 
            } catch ( FileNotFoundException ex ) { 
                throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea"); 
            } catch ( IOException ex ) { 
                throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia"); 
            } 
     
            muutettu = false; 
    }


    /**
     * @return tiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostoNimi;
    }
    
    
    /**
     * Etsitään pokemoni taulukosta (suuret ja pienet kirjaimet samoja)
     * @param nimi jolla etsitään
     * @return pokemon, jolla kyseinen nimi
     * muuten null
     * @example
     * <pre name="test">
     *      Pokemonit p = new Pokemonit();
     *      Pokemon t = new Pokemon();
     *      t.rekisteroi();
     *      t.vastaa_pikachu(); p.lisaa(t);
     *      p.etsiTaulukosta("pikachu") === t;
     *      p.etsiTaulukosta("pokemon") === null;
     * </pre>
     */
    public Pokemon etsiTaulukosta(String nimi) {
        for (int i = 0; i < lkm; i++) {
            Pokemon temp = this.getPokemon(i);
            if (nimi.toLowerCase().equals(
                    temp.getNimi().toLowerCase())) return temp;
        }
        return null;
    }
    
    
    /**
     * Etsii pokemonin id:n perusteella
     * @param id Etsittävän pokemonin id
     * @return Pokemon, null jos ei löydy
     */
    public Pokemon etsiPokemon(int id) {
        for (int i = 0; i < lkm; i++) {
            Pokemon p = taulukko[i];
            if (p.getID() == id) return p;
        }
        return null;
    }
    
    
    /**
     * Etsii pokemonia nimen perusteella ja tietyllä ehdolla lajiteltuna.
     * @param hakuEhto Maski merkkijono
     * @param n 1, 2 jos nimi, 3, 4 jos vahvuus, 5, 6 jos ikä
     * @param kaanteinen Halutaanko lista käänteisenä
     * @param lajitteluEhdot Taulukko, joka sisältää tiedon tarkemman haun hakuehdoista. 1=true, 0=false
     * @return Ehtoihin sopivat pokemonit listassa
     */
    public Collection<Pokemon> etsiHakuehdolla(String hakuEhto, int n, boolean kaanteinen, int[] lajitteluEhdot) {
        String ehto = "*";
        if (hakuEhto != null && hakuEhto.length() > 0) ehto = hakuEhto;
        List<Pokemon> sopivat = new ArrayList<Pokemon>();
        for (int i = 0; i < lkm; i++) {
            Pokemon p = taulukko[i];
            if (WildChars.onkoSamat(p.getNimi(), ehto)) {
                int vahv = p.getVahvuus();
                // Tarkistetaan vahvuus:
                // lajitteluEhdot[12] = vahvuuden minimiarvo,  lajitteluEhdot[13] = vahvuuden maksimiarvo
                if (lajitteluEhdot[12] <= vahv && vahv <= lajitteluEhdot[13]) {
                    int ikaI = p.getIkaID();
                    // Tarkistetaan ikä:
                    // lajitteluEhdot[7 - 11] = ikaryhmät (1 jos valittu, 0 jos ei)
                    if ((ikaI == 1 && lajitteluEhdot[7] == 1) || (ikaI == 2 && lajitteluEhdot[8] == 1) || (ikaI == 3 && lajitteluEhdot[9] == 1) || 
                            (ikaI == 4 && lajitteluEhdot[10] == 1) || (ikaI == 5 && lajitteluEhdot[11] == 1)) {
                        int eID1 = p.getElementtiID(1);
                        int eID2 = p.getElementtiID(2);
                        if (lajitteluEhdot[0] == 1 || ((eID1 == 1 || eID2 == 1) && lajitteluEhdot[1] == 1) || ((eID1 == 2 || eID2 == 2) && lajitteluEhdot[2] == 1)
                                || ((eID1 == 3 || eID2 == 3) && lajitteluEhdot[3] == 1) || ((eID1 == 4 || eID2 == 4) && lajitteluEhdot[4] == 1) || 
                                ((eID1 == 5 || eID2 == 5) && lajitteluEhdot[5] == 1) || ((eID1 == 6 || eID2 == 6) && lajitteluEhdot[6] == 1) ) {
                            sopivat.add(p);
                        }
                    }
                }
            }           
        }
        Collections.sort(sopivat, new Pokemon.Vertailija(n));
        if (kaanteinen) Collections.reverse(sopivat);
        return sopivat;
    }


    /**
     * Testipääohjelma Pokemonit-luokalle
     * @param args ei kayt.
     */
    public static void main(String[] args) {
        Pokemonit pokemonit = new Pokemonit();

        Pokemon pikachu = new Pokemon(), charizard = new Pokemon();
        pikachu.rekisteroi();
        pikachu.vastaa_pikachu();
        charizard.rekisteroi();
        charizard.vastaa_pikachu();

        try {
            pokemonit.lisaa(pikachu);
            pokemonit.lisaa(charizard);

            System.out
                    .println("============= Pokemonit testi =================");

            for (int i = 0; i < pokemonit.getLkm(); i++) {
                Pokemon Pokemon = pokemonit.getPokemon(i);
                System.out.println("Pokemonin nro: " + i);
                Pokemon.tulosta(System.out);
                System.out.println();
            }

            pokemonit.tallenna();

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            pokemonit.lueTiedostosta();

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}