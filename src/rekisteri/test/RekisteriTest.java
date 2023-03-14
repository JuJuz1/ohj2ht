package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.03.14 16:23:32 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RekisteriTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa33 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa33() throws SailoException {    // Rekisteri: 33
    Rekisteri rekisteri = new Rekisteri(); 
    Pokemon pikachu = new Pokemon(), charizard = new Pokemon(); 
    assertEquals("From: Rekisteri line: 37", 0, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 38", 1, rekisteri.getLkm()); 
    rekisteri.lisaa(charizard); assertEquals("From: Rekisteri line: 39", 2, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 40", 3, rekisteri.getLkm()); 
    assertEquals("From: Rekisteri line: 41", pikachu, rekisteri.getPokemon(0)); 
    assertEquals("From: Rekisteri line: 42", charizard, rekisteri.getPokemon(1)); 
    assertEquals("From: Rekisteri line: 43", pikachu, rekisteri.getPokemon(2)); 
    assertEquals("From: Rekisteri line: 44", false, rekisteri.getPokemon(1) == pikachu); 
    assertEquals("From: Rekisteri line: 45", true, rekisteri.getPokemon(1) == charizard); 
    try {
    assertEquals("From: Rekisteri line: 46", pikachu, rekisteri.getPokemon(3)); 
    fail("Rekisteri: 46 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 47", 4, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 48", 5, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); rekisteri.lisaa(pikachu); 
    rekisteri.lisaa(pikachu); 
    try {
    rekisteri.lisaa(pikachu); 
    fail("Rekisteri: 51 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}