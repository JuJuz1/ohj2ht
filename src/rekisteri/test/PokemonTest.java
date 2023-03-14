package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import static rekisteri.Pokemon.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.03.14 16:17:43 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PokemonTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi90 */
  @Test
  public void testRekisteroi90() {    // Pokemon: 90
    Pokemon pikachu = new Pokemon(); 
    Pokemon charizard = new Pokemon(); 
    assertEquals("From: Pokemon line: 93", 0, pikachu.getID()); 
    int n1 = pikachu.rekisteroi(); 
    assertEquals("From: Pokemon line: 95", 0, charizard.getID()); 
    int n2 = charizard.rekisteroi(); 
    assertEquals("From: Pokemon line: 97", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkistaNimi121 */
  @Test
  public void testTarkistaNimi121() {    // Pokemon: 121
    assertEquals("From: Pokemon line: 122", true, tarkistaNimi("Pikachu")); 
    assertEquals("From: Pokemon line: 123", false, tarkistaNimi("pikachu")); 
    assertEquals("From: Pokemon line: 124", false, tarkistaNimi("Pikachu ")); 
    assertEquals("From: Pokemon line: 125", false, tarkistaNimi(" Pikachu")); 
    assertEquals("From: Pokemon line: 126", false, tarkistaNimi("Pikachu1")); 
    assertEquals("From: Pokemon line: 127", true, tarkistaNimi("P")); 
    assertEquals("From: Pokemon line: 128", false, tarkistaNimi("")); 
    assertEquals("From: Pokemon line: 129", false, tarkistaNimi("€")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkistaVahvuus142 */
  @Test
  public void testTarkistaVahvuus142() {    // Pokemon: 142
    assertEquals("From: Pokemon line: 143", false, tarkistaVahvuus(0)); 
    assertEquals("From: Pokemon line: 144", true, tarkistaVahvuus(1)); 
    assertEquals("From: Pokemon line: 145", false, tarkistaVahvuus(-1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkistaEvoluutio159 */
  @Test
  public void testTarkistaEvoluutio159() {    // Pokemon: 159
    assertEquals("From: Pokemon line: 160", true, tarkistaEvoluutio(2)); 
    assertEquals("From: Pokemon line: 161", false, tarkistaEvoluutio(0)); 
    assertEquals("From: Pokemon line: 162", false, tarkistaEvoluutio(5)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTarkistaSeuraavaEvoluutio177 */
  @Test
  public void testTarkistaSeuraavaEvoluutio177() {    // Pokemon: 177
    Pokemon uusi = new Pokemon(); 
    int temp = uusi.rekisteroi(); 
    assertEquals("From: Pokemon line: 180", true, tarkistaSeuraavaEvoluutio(temp)); 
    assertEquals("From: Pokemon line: 181", false, tarkistaSeuraavaEvoluutio(temp+1)); 
    assertEquals("From: Pokemon line: 182", false, tarkistaSeuraavaEvoluutio(0)); 
    assertEquals("From: Pokemon line: 183", false, tarkistaSeuraavaEvoluutio(-1)); 
  } // Generated by ComTest END
}