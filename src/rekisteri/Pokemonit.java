/**
 * 
 */
package rekisteri;

/**
 * Sisältää taulukon pokemoneja (ja pokemonien lukumäärän sekä taulukon pituuden).
 * TODO: Taulukon koko kasvaa automaattisesti tarpeen mukaan.
 * Osaa lisätä ja TODO: poistaa pokemonin.
 * TODO: Osaa lajitella pokemonit järjestykseen nimen, iän tai vahvuuden mukaan.
 * TODO: Osaa etsiä pokemoneja listasta nimen perusteella.
 * TODO: Lukee ja kirjoittaa pokemonit tiedostoon
 * TODO: Osaa vertailla kahta pokemonia kaksintaistelussa
 * @author Juuso Piippo & Elias Lehtinen
 * @version 2.3.2023
 *
 */
public class Pokemonit {

    private String tiedostoNimi;
    private int lkm;
    private int maxLkm = 8;
    private Pokemon[] taulukko = new Pokemon[maxLkm];

    /**
     * Oletusmuodostaja
     */
    public Pokemonit() {
        //
    }


    /**
     * Lisaa pokemonin taulukkoon
     * @param pokemon pokemon joka lisataan
     * @throws SailoException poikkeus, joka heitetään jos taulukko täynnä
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
     *      pokemonit.lisaa(pikachu); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Pokemon pokemon) throws SailoException {
        if (taulukko.length <= lkm)
            throw new SailoException("Alkio ei mahdu taulukkoon");
        taulukko[lkm] = pokemon;
        lkm++;
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
        throw new SailoException(
                "Ei osata viela lukea tiedostoa " + tiedostoNimi);
    }


    /**
     * Tallentaa jäsenistön tiedostoon.  Kesken. TODO: Tiedostoon tallennus
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        throw new SailoException(
                "Ei osata viela tallettaa tiedostoa " + tiedostoNimi);
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