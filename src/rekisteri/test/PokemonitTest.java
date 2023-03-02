package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.03.02 15:25:06 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PokemonitTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa31 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa31() throws SailoException {    // Pokemonit: 31
    Pokemonit pokemonit = new Pokemonit(); 
    Pokemon pikachu = new Pokemon(), charizard = new Pokemon(); 
    assertEquals("From: Pokemonit line: 35", 0, pokemonit.getLkm()); 
    pokemonit.lisaa(pikachu); assertEquals("From: Pokemonit line: 36", 1, pokemonit.getLkm()); 
    pokemonit.lisaa(charizard); assertEquals("From: Pokemonit line: 37", 2, pokemonit.getLkm()); 
    pokemonit.lisaa(pikachu); assertEquals("From: Pokemonit line: 38", 3, pokemonit.getLkm()); 
    assertEquals("From: Pokemonit line: 39", pikachu, pokemonit.getPokemon(0)); 
    assertEquals("From: Pokemonit line: 40", charizard, pokemonit.getPokemon(1)); 
    assertEquals("From: Pokemonit line: 41", pikachu, pokemonit.getPokemon(2)); 
    assertEquals("From: Pokemonit line: 42", false, pokemonit.getPokemon(1) == pikachu); 
    assertEquals("From: Pokemonit line: 43", true, pokemonit.getPokemon(1) == charizard); 
    try {
    assertEquals("From: Pokemonit line: 44", pikachu, pokemonit.getPokemon(3)); 
    fail("Pokemonit: 44 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    pokemonit.lisaa(pikachu); assertEquals("From: Pokemonit line: 45", 4, pokemonit.getLkm()); 
    pokemonit.lisaa(pikachu); assertEquals("From: Pokemonit line: 46", 5, pokemonit.getLkm()); 
    pokemonit.lisaa(pikachu); pokemonit.lisaa(pikachu); 
    pokemonit.lisaa(pikachu); 
    try {
    pokemonit.lisaa(pikachu); 
    fail("Pokemonit: 49 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}