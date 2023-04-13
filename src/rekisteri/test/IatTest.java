package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import fi.jyu.mit.ohj2.VertaaTiedosto;
import java.util.ArrayList;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.04.13 12:54:24 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class IatTest {



  // Generated by ComTest BEGIN
  /** testEtsiIka77 */
  @Test
  public void testEtsiIka77() {    // Iat: 77
    Iat i = new Iat(); 
    Ika i1 = new Ika(1, "10-20"); 
    assertEquals("From: Iat line: 80", 0, i.getLkm()); 
    Ika i2 = new Ika(2, "20-30"); 
    i.lisaa(i1); i.lisaa(i2); 
    assertEquals("From: Iat line: 83", i2, i.etsiIka(2)); 
    assertEquals("From: Iat line: 84", i2.getID() - 1, i.etsiIka(1).getID()); 
    assertEquals("From: Iat line: 85", 2, i.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta101 
   * @throws IOException when error
   */
  @Test
  public void testLueTiedostosta101() throws IOException {    // Iat: 101
    String tiedosto = "iatTest.dat"; 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
    VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|ika\n 1 |0-10\n 2 |10-20\n 3 |20-30\n 4 |30-40"); 
    Iat i = new Iat(tiedosto); 
    i.lueTiedostosta(); 
    assertEquals("From: Iat line: 111", 4, i.getLkm()); 
    assertEquals("From: Iat line: 112", "0-10", i.etsiIka(1).getIka()); 
    assertEquals("From: Iat line: 113", "10-20", i.etsiIka(2).getIka()); 
    assertEquals("From: Iat line: 114", "30-40", i.etsiIka(4).getIka()); 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
  } // Generated by ComTest END
}