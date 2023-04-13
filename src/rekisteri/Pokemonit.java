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

/**
 * Sisältää taulukon pokemoneja (ja pokemonien lukumäärän sekä taulukon pituuden).
 * Taulukon koko kasvaa automaattisesti tarpeen mukaan.
 * Osaa lisätä ja TODO: poistaa pokemonin.
 * TODO: Osaa lajitella pokemonit järjestykseen nimen, iän tai vahvuuden mukaan.
 * TODO: Osaa etsiä pokemoneja taulukosta nimen perusteella.
 * TODO: Lukee ja kirjoittaa pokemonit tiedostoon
 * TODO: Osaa vertailla kahta pokemonia kaksintaistelussa
 * @author Juuso Piippo & Elias Lehtinen
 * @version 14.3.2023
 *
 */
public class Pokemonit {

    private final String tiedostoNimi = "pokemonit.dat";
    private int lkm;
    private int maxLkm = 8;
    private Pokemon[] taulukko = new Pokemon[maxLkm];
    private boolean muutettu = false;
    private final String baknimi = "pokemonit.bak";

    /**
     * Oletusmuodostaja
     */
    public Pokemonit() {
        //
    }


    /**
     * Lisaa pokemonin taulukkoon
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

        }
        taulukko[lkm] = pokemon;
        lkm++;
        
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
     * Lukee jäsenistön tiedostosta.  Kesken. TODO: Tiedostosta luku
     * @throws SailoException jos lukeminen epäonnistuu
    */
    public void lueTiedostosta() throws SailoException {
        try (BufferedReader fi = new BufferedReader(
                new FileReader(getTiedostonNimi()))) {
            String rivi = fi.readLine();
            if (rivi == null)
                throw new SailoException("Riviä ei saatu luettua");
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi) || rivi.charAt(0) == ';') continue;
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
     * Tallentaa jäsenistön tiedostoon.  Kesken. TODO: Tiedostoon tallennus
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
            if ( !muutettu ) return; 
     
            File fbak = new File(getBakNimi()); 
            File ftied = new File(getTiedostonNimi()); 
            fbak.delete(); // if .. System.err.println("Ei voi tuhota"); 
            ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä"); 
     
            try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) { 
               // fo.println(getKokoNimi()); 
               // fo.println(pokemonit.length); 
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


    public String getTiedostonNimi() {
        return tiedostoNimi;
    }
    
    
    public String getBakNimi() {
        return baknimi;
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