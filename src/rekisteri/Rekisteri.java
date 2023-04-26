/**
 * 
 */
package rekisteri;

import java.io.PrintStream;

/**
 * Yhdistää iät ja elementit pokemoneihin.
 * Välittää alempien luokkien välillä tietoa.
 * Ohjaa muita luokkia.
 * TODO: Lukee ja kirjoittaa rekisterin sisällön tiedostoihin muiden luokkien avulla.
 * @author Juuso Piippo & Elias Lehtinen
 * @version 13.4.2023
 *
 */
public class Rekisteri {
    private Pokemonit pokemonit = new Pokemonit();
    private Elementit elementit = new Elementit();
    private Iat iat = new Iat();

    
    /**
     * @return pokemonit taulukon alkioiden lkm
     */
    public int getLkm() {
        return pokemonit.getLkm();
    }
    
    
    /**
     * Etsii pokemonin id:n perusteella
     * @param id Pokemonin id
     * @return Pokemon, null jos ei löydy
     */
    public Pokemon etsiPokemon(int id) {
        return pokemonit.etsiPokemon(id);
    }
    
    
    /**
     * @return Elementit-olio
     */
    public Elementit getElementit() {
        return elementit;
    }
    
    
    /**
     * @return Iat-olio
     */
    public Iat getIat() {
        return iat;
    }


    /**
     * Kutsuu pokemonit luokan metodia, joka lisää pokemonin taulukkoon
     * @param pokemon pokemon joka lisataan
     * @example
     * <pre name="test">
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
     *      rekisteri.getPokemon(2) === pikachu;
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 4;
     *      rekisteri.lisaa(pikachu); rekisteri.getLkm() === 5;
     *      rekisteri.lisaa(pikachu); rekisteri.lisaa(pikachu);
     *      rekisteri.lisaa(pikachu); 
     *      rekisteri.lisaa(pikachu);
     * </pre>
     */
    public void lisaa(Pokemon pokemon) {
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
     * Tallentaa rekisterin sisällön tiedostoihin
     * @throws SailoException heittää
     */
    public void tallenna() throws SailoException{
        pokemonit.tallenna();
        elementit.tallenna();
        iat.tallenna();
    }
    
    
    /**
     * Lukee tiedostosta rekisterin sisällön
     * @throws SailoException heittää
     */
    public void lueTiedostosta() throws SailoException {
        pokemonit.lueTiedostosta();
        elementit.lueTiedostosta();
        iat.lueTiedostosta();
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
        rekisteri.getElementit().alustaElementeilla();
        rekisteri.getIat().alustaIkaLuokilla();

        try {
            rekisteri.lisaa(pikachu);
            rekisteri.lisaa(charizard);

            System.out
                    .println("============= Pokemonit testi =================");

            for (int i = 0; i < rekisteri.getLkm(); i++) {
                Pokemon pokemon = rekisteri.getPokemon(i);
                System.out.println("Pokemonin nro: " + i);
                pokemon.tulosta(System.out);
                rekisteri.tulostaElementit(pokemon, System.out);
                rekisteri.tulostaIka(pokemon, System.out);
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
        os.println(elementitJonona(pokemon));
    }
    

    /**
     * Palauttaa pokemonin elementit merkkijonona
     * TODO: poista kun ei tarvita
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
    
    
    /**
     * Palauttaa pokemonin halutun elementin merkkijonona
     * @param pokemon Pokemon
     * @param elementtiNro 1 jos halutaan 1. elementti ja 2 jos halutaan 2.
     * @return Elementti merkkijonona. Jos elementtiNro ei ole 1 tai 2 tai jos elementtiä ei ole, palautetaan "".
     * @example
     * <pre name="test">
     *  Rekisteri r = new Rekisteri();
     *  r.getElementit().alustaElementeilla();
     *  Pokemon p = new Pokemon();
     *  p.vastaa_pikachu();
     *  r.annaElementti(p, 1) === "tuli";
     *  r.annaElementti(p, 2) === "maa";
     *  r.annaElementti(p, 5) === "";
     * </pre>
     */
    public String annaElementti(Pokemon pokemon, int elementtiNro) {
        int id = pokemon.getElementtiID(elementtiNro);
        Elementti e = elementit.etsiElementti(id);
        String eJono = "";
        if (e != null) eJono = e.getNimi();
        return eJono;
    }
    
    
    /**
     * Palauttaa pokemonin ikäryhmän
     * @param pokemon Pokemon, jonka ika palautetaan
     * @return Pokemonin ikaryhmä merkkijonona
     */
    public String annaIka(Pokemon pokemon) {
        Ika ika = iat.etsiIka(pokemon.getIkaID());
        if (ika == null) return "";
        return ika.getIka();
    }
    
    
    /**
     * Tulostaa pokemonin iän.
     * Käytetään Ika-olion ja pokemonin linkittämisen testaamiseen.
     * TODO: poista kun ei tarvita
     * @param pokemon Pokemon, jonka ikä tulostetaan
     * @param ps Tietovirta, johon tulostetaan
     */
    public void tulostaIka(Pokemon pokemon, PrintStream ps) {
        int ikaID = pokemon.getIkaID();
        ps.println("Ikä: " + iat.etsiIka(ikaID));
    }
}
