/**
 * 
 */
package rekisteri;

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 2.3.2023
 *
 */
public class Rekisteri {
    Pokemonit pokemonit = new Pokemonit();
    // TODO: Elementit
    // TODO: Iat

    /**
     * @return pokemonit taulukon alkioiden lkm
     */
    public int getLkm() {
        return pokemonit.getLkm();
    }


    /**
     * Kutsuu pokemonit luokan metodia
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
     * Kutsuu pokemonit luokan metodia
     * @param i indeksi
     * @return pokemonit taulukon indeksissa i olevan pokemonin
     */
    public Pokemon getPokemon(int i) {
        return pokemonit.getPokemon(i);
    }
    
    
    /**
     * @throws SailoException heittää
     */
    public void tallenna() throws SailoException{
        pokemonit.tallenna();
    }
    
    
    /**
     * @throws SailoException heittää
     */
    public void lueTiedostosta() throws SailoException {
        pokemonit.lueTiedostosta();
    }


    /**
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

}
