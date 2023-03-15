/**
 * 
 */
package rekisteri;

import java.io.PrintStream;

/**
 * TODO: Yhdistää iät ja elementit pokemoneihin.
 * Välittää alempien luokkien välillä tietoa.
 * Ohjaa muita luokkia.
 * TODO: Lukee ja kirjoittaa rekisterin sisällön tiedostoihin muiden luokkien avulla.
 * @author Juuso Piippo & Elias Lehtinen
 * @version 14.3.2023
 *
 */
public class Rekisteri {
    private Pokemonit pokemonit = new Pokemonit();
    private Elementit elementit = new Elementit();
    // TODO: Iat

    /**
     * @return pokemonit taulukon alkioiden lkm
     */
    public int getLkm() {
        return pokemonit.getLkm();
    }
    
    
    /**
     * @return Elementit-olio
     */
    public Elementit getElementit() {
        return elementit;
    }


    /**
     * Kutsuu pokemonit luokan metodia, joka lisää pokemonin taulukkoon
     * @param pokemon pokemon joka lisataan
     * @throws SailoException heittää jos liikaa alkioita
     * @example
     * <pre name="test">
     *      #THROWS SailoException 
     *      Rekisteri rekisteri = new Rekisteri();
     *      Pokemon pikachu = new Pokemon(), charizard = new Pokemon();
     *      rekisteri.getLkm() === 0;
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 1;
     *      rekisteri.lisaa(charizard); rekisteri.getLkm() === 2;
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 3;
     *      rekisteri.getPokemon(0) === pikachu;
     *      rekisteri.getPokemon(1) === charizard;
     *      rekisteri.getPokemon(2) === pikachu;
     *      rekisteri.getPokemon(1) == pikachu === false;
     *      rekisteri.getPokemon(1) == charizard === true;
     *      rekisteri.getPokemon(3) === pikachu; #THROWS IndexOutOfBoundsException 
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 4;
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 5;
     *      rekisteri.lisaa(pikachu); rekisteri.lisaa(pikachu);
     *      rekisteri.lisaa(pikachu); 
     *      rekisteri.lisaa(pikachu); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Pokemon pokemon) throws SailoException {
        pokemonit.lisaa(pokemon);

    }
    
    
    /**
     * Kutsuu pokemonit luokan metodia, joka hakee pokemonin taulukon indeksistä i
     * @param i indeksi
     * @return pokemonit taulukon indeksissa i oleva pokemon
     */
    public Pokemon getPokemon(int i) {
        return pokemonit.getPokemon(i);
    }
    
    
    /**
     * TODO: Tallentaa rekisterin sisällön tiedostoihin
     * @throws SailoException heittää
     */
    public void tallenna() throws SailoException{
        pokemonit.tallenna();
    }
    
    
    /**
     * TODO: Lukee tiedostosta rekisterin sisällön
     * @throws SailoException heittää
     */
    public void lueTiedostosta() throws SailoException {
        pokemonit.lueTiedostosta();
    }


    /**
     * Testipääohjelma Rekisteri-luokalle
     * @param args ei kayt.
     */
    public static void main(String[] args) {
        Rekisteri rekisteri = new Rekisteri();

        Pokemon pikachu = new Pokemon(), charizard = new Pokemon();
        pikachu.rekisteroi();
        pikachu.vastaa_pikachu();
        charizard.rekisteroi();
        charizard.vastaa_pikachu();

        try {
            rekisteri.lisaa(pikachu);
            rekisteri.lisaa(charizard);

            System.out
                    .println("============= Pokemonit testi =================");

            for (int i = 0; i < rekisteri.getLkm(); i++) {
                Pokemon Pokemon = rekisteri.getPokemon(i);
                System.out.println("Pokemonin nro: " + i);
                Pokemon.tulosta(System.out);
                System.out.println();
            }

            rekisteri.tallenna();

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            rekisteri.lueTiedostosta();

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Tulostaa pokemonin elementtien nimet.
     * Käytetään elementtien ja pokemonien linkittämisen testaamiseen.
     * @param pokemon Pokemon, jonka elementit tulostetaan
     * @param os Tietovirta johon tulostetaan
     */
    public void tulostaElementit(Pokemon pokemon, PrintStream os) {
        os.print(elementitJonona(pokemon));
    }
    

    /**
     * Palauttaa pokemonin elementit merkkijonona
     * @param pokemon Pokemon
     * @return Elementit merkkijonona
     * @example
     * <pre name="test">
     *  Rekisteri r = new Rekisteri();
     *  r.getElementit().alustaElementeilla();
     *  Pokemon p = new Pokemon();
     *  p.vastaa_pikachu();
     *  r.elementitJonona(p) === "Elementit: tuli maa";
     *  p.asetaTyhjaElementti();
     *  r.elementitJonona(p) === "Elementit: tuli ";
     * </pre>
     */
    public String elementitJonona(Pokemon pokemon) {
        int id1 = pokemon.getElementtiID(1);
        int id2 = pokemon.getElementtiID(2);
        Elementti e1 = elementit.etsiElementti(id1);
        Elementti e2 = elementit.etsiElementti(id2);
        String e1Jono = "";
        String e2Jono = "";
        if (e1 != null) e1Jono = e1.getNimi() + ' ';
        if (e2 != null) e2Jono = e2.getNimi();
        String jono = "Elementit: " + e1Jono + e2Jono;
        return jono;
    }
}
