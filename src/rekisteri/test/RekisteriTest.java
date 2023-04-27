package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.04.27 14:08:48 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RekisteriTest {



  // Generated by ComTest BEGIN
  /** testLisaa61 */
  @Test
  public void testLisaa61() {    // Rekisteri: 61
    Rekisteri rekisteri = new Rekisteri(); 
    Pokemon pikachu = new Pokemon(), charizard = new Pokemon(); 
    assertEquals("From: Rekisteri line: 64", 0, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 65", 1, rekisteri.getLkm()); 
    rekisteri.lisaa(charizard); assertEquals("From: Rekisteri line: 66", 2, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 67", 3, rekisteri.getLkm()); 
    assertEquals("From: Rekisteri line: 68", pikachu, rekisteri.getPokemon(0)); 
    assertEquals("From: Rekisteri line: 69", charizard, rekisteri.getPokemon(1)); 
    assertEquals("From: Rekisteri line: 70", pikachu, rekisteri.getPokemon(2)); 
    assertEquals("From: Rekisteri line: 71", false, rekisteri.getPokemon(1) == pikachu); 
    assertEquals("From: Rekisteri line: 72", true, rekisteri.getPokemon(1) == charizard); 
    assertEquals("From: Rekisteri line: 73", pikachu, rekisteri.getPokemon(2)); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 74", 4, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); assertEquals("From: Rekisteri line: 75", 5, rekisteri.getLkm()); 
    rekisteri.lisaa(pikachu); rekisteri.lisaa(pikachu); 
    rekisteri.lisaa(pikachu); 
    rekisteri.lisaa(pikachu); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testElementitJonona181 */
  @Test
  public void testElementitJonona181() {    // Rekisteri: 181
    Rekisteri r = new Rekisteri(); 
    r.getElementit().alustaElementeilla(); 
    Pokemon p = new Pokemon(); 
    p.vastaa_pikachu(); 
    assertEquals("From: Rekisteri line: 186", "Elementit: tuli maa", r.elementitJonona(p)); 
    p.asetaTyhjaElementti(); 
    assertEquals("From: Rekisteri line: 188", "Elementit: tuli ", r.elementitJonona(p)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaElementti211 */
  @Test
  public void testAnnaElementti211() {    // Rekisteri: 211
    Rekisteri r = new Rekisteri(); 
    r.getElementit().alustaElementeilla(); 
    Pokemon p = new Pokemon(); 
    p.vastaa_pikachu(); 
    assertEquals("From: Rekisteri line: 216", "tuli", r.annaElementti(p, 1)); 
    assertEquals("From: Rekisteri line: 217", "maa", r.annaElementti(p, 2)); 
    assertEquals("From: Rekisteri line: 218", "", r.annaElementti(p, 5)); 
  } // Generated by ComTest END
}