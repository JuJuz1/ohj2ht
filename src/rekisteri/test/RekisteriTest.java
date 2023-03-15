package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.03.15 12:06:33 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RekisteriTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa44 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa44() throws SailoException {    // Rekisteri: 44
    Rekisteri rekisteri = new Rekisteri(); 
    Pokemon pikachu = new Pokemon(), charizard = new Pokemon(); 
    assertEquals("From: Rekisteri line: 48", 0, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 49", 1, rekisteri.getLkm()); 
    rekisteri.lisaa(charizard); assertEquals("From: Rekisteri line: 50", 2, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 51", 3, rekisteri.getLkm()); 
    assertEquals("From: Rekisteri line: 52", pikachu, rekisteri.getPokemon(0)); 
    assertEquals("From: Rekisteri line: 53", charizard, rekisteri.getPokemon(1)); 
    assertEquals("From: Rekisteri line: 54", pikachu, rekisteri.getPokemon(2)); 
    assertEquals("From: Rekisteri line: 55", false, rekisteri.getPokemon(1) == pikachu); 
    assertEquals("From: Rekisteri line: 56", true, rekisteri.getPokemon(1) == charizard); 
    try {
    assertEquals("From: Rekisteri line: 57", pikachu, rekisteri.getPokemon(3)); 
    fail("Rekisteri: 57 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 58", 4, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 59", 5, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); rekisteri.lisaa(pikachu); 
    rekisteri.lisaa(pikachu); 
    rekisteri.lisaa(pikachu); 
    fail("Rekisteri: 62 Did not throw SailoException");
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testElementitJonona157 */
  @Test
  public void testElementitJonona157() {    // Rekisteri: 157
    Rekisteri r = new Rekisteri(); 
    r.getElementit().alustaElementeilla(); 
    Pokemon p = new Pokemon(); 
    p.vastaa_pikachu(); 
    assertEquals("From: Rekisteri line: 162", "Elementit: tuli maa", r.elementitJonona(p)); 
    p.asetaTyhjaElementti(); 
    assertEquals("From: Rekisteri line: 164", "Elementit: tuli ", r.elementitJonona(p)); 
  } // Generated by ComTest END
}