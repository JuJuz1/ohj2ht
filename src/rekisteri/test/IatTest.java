package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.03.15 12:42:54 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class IatTest {



  // Generated by ComTest BEGIN
  /** testEtsiIka56 */
  @Test
  public void testEtsiIka56() {    // Iat: 56
    Iat i = new Iat(); 
    Ika i1 = new Ika(1, "10-20"); 
    assertEquals("From: Iat line: 59", 0, i.getLkm()); 
    Ika i2 = new Ika(2, "20-30"); 
    i.lisaa(i1); i.lisaa(i2); 
    assertEquals("From: Iat line: 62", i2, i.etsiIka(2)); 
    assertEquals("From: Iat line: 63", i2.getID() - 1, i.etsiIka(1).getID()); 
    assertEquals("From: Iat line: 64", 2, i.getLkm()); 
  } // Generated by ComTest END
}