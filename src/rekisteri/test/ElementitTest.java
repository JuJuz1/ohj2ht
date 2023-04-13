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
 * @version 2023.04.13 14:53:42 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ElementitTest {



  // Generated by ComTest BEGIN
  /** testEtsiElementti68 */
  @Test
  public void testEtsiElementti68() {    // Elementit: 68
    Elementit elementit = new Elementit(); 
    Elementti e1 = new Elementti(1, "testi", 2, 3); 
    assertEquals("From: Elementit line: 71", 0, elementit.getLkm()); 
    Elementti e2 = new Elementti(2, "testi2", 5, 1); 
    elementit.lisaa(e1); elementit.lisaa(e2); 
    assertEquals("From: Elementit line: 74", e2, elementit.etsiElementti(2)); 
    assertEquals("From: Elementit line: 75", e2.getID() - 1, elementit.etsiElementti(1).getID()); 
    assertEquals("From: Elementit line: 76", 2, elementit.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta109 
   * @throws IOException when error
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta109() throws IOException, SailoException {    // Elementit: 109
    String tiedosto = "elementitTest.dat"; 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
    VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|elementti    |vahvuusID  |heikkousID\n 1 |vesi         |2          |6\n 2 |tuli         |4          |1\n 3 |maa          |5          |4"); 
    Elementit el = new Elementit(tiedosto); 
    el.lueTiedostosta(); 
    assertEquals("From: Elementit line: 119", 3, el.getLkm()); 
    assertEquals("From: Elementit line: 120", "vesi", el.etsiElementti(1).getNimi()); 
    assertEquals("From: Elementit line: 121", 4, el.etsiElementti(2).getVahvuusID()); 
    assertEquals("From: Elementit line: 122", 4, el.etsiElementti(3).getHeikkousID()); 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testTallenna142 
   * @throws IOException when error
   * @throws SailoException when error
   */
  @Test
  public void testTallenna142() throws IOException, SailoException {    // Elementit: 142
    String tiedosto = "elementitTest.dat"; 
    String tiedostobak = tiedosto.replace(".dat", ".bak"); 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
    VertaaTiedosto.tuhoaTiedosto(tiedostobak); 
    VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|elementti    |vahvuusID  |heikkousID\n 1 |vesi         |2          |6\n 2 |tuli         |4          |1\n 3 |maa          |5          |4"); 
    Elementit el = new Elementit(tiedosto); 
    el.lueTiedostosta(); 
    assertEquals("From: Elementit line: 154", 3, el.getLkm()); 
    assertEquals("From: Elementit line: 155", "vesi", el.etsiElementti(1).getNimi()); 
    assertEquals("From: Elementit line: 156", 4, el.etsiElementti(2).getVahvuusID()); 
    assertEquals("From: Elementit line: 157", 4, el.etsiElementti(3).getHeikkousID()); 
    el.lisaa(new Elementti(4, "testi", 2, 3)); 
    el.tallenna(); 
    Elementit eDat = new Elementit(tiedosto); eDat.lueTiedostosta(); 
    Elementit eBak = new Elementit(tiedostobak); eBak.lueTiedostosta(); 
    assertEquals("From: Elementit line: 163", "maa", eDat.etsiElementti(3).getNimi()); assertEquals("From: Elementit line: 163", "maa", eBak.etsiElementti(3).getNimi()); 
    assertEquals("From: Elementit line: 164", "testi", eDat.etsiElementti(4).getNimi()); assertEquals("From: Elementit line: 164", null, eBak.etsiElementti(4)); 
    VertaaTiedosto.tuhoaTiedosto(tiedosto); 
    VertaaTiedosto.tuhoaTiedosto(tiedostobak); 
  } // Generated by ComTest END
}