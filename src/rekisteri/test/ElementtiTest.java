package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.04.29 21:23:56 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class ElementtiTest {



  // Generated by ComTest BEGIN
  /** testElementti31 */
  @Test
  public void testElementti31() {    // Elementti: 31
    Elementti e1 = new Elementti(1, "testi", 2, 3); 
    Elementti e2 = new Elementti(2, "testi2", 5, 1); 
    assertEquals("From: Elementti line: 34", "testi", e1.getNimi()); assertEquals("From: Elementti line: 34", 1, e1.getID()); assertEquals("From: Elementti line: 34", 2, e1.getVahvuusID()); assertEquals("From: Elementti line: 34", 3, e1.getHeikkousID()); 
    assertEquals("From: Elementti line: 35", "testi2", e2.getNimi()); assertEquals("From: Elementti line: 35", 2, e2.getID()); 
    e1.setNimi("testitesti"); e1.setID(4); e1.setVahvuusID(1); e1.setHeikkousID(6); 
    assertEquals("From: Elementti line: 37", "testitesti", e1.getNimi()); assertEquals("From: Elementti line: 37", 4, e1.getID()); assertEquals("From: Elementti line: 37", 1, e1.getVahvuusID()); assertEquals("From: Elementti line: 37", 6, e1.getHeikkousID()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testElementti55 */
  @Test
  public void testElementti55() {    // Elementti: 55
    Elementti vesi = new Elementti(" 1 |vesi         |2          |6"); 
    assertEquals("From: Elementti line: 57", "vesi", vesi.getNimi()); assertEquals("From: Elementti line: 57", 1, vesi.getID()); assertEquals("From: Elementti line: 57", 2, vesi.getVahvuusID()); assertEquals("From: Elementti line: 57", 6, vesi.getHeikkousID()); 
    Elementti tuli = new Elementti(" 2 | tuli|    32|testitesti"); 
    assertEquals("From: Elementti line: 59", "tuli", tuli.getNimi()); assertEquals("From: Elementti line: 59", 2, tuli.getID()); assertEquals("From: Elementti line: 59", 32, tuli.getVahvuusID()); assertEquals("From: Elementti line: 59", -1, tuli.getHeikkousID()); 
    Elementti maa = new Elementti("maa| 2|3"); 
    assertEquals("From: Elementti line: 61", null, maa.getNimi()); assertEquals("From: Elementti line: 61", 0, maa.getID()); assertEquals("From: Elementti line: 61", 0, maa.getVahvuusID()); assertEquals("From: Elementti line: 61", 0, maa.getHeikkousID()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testCompareTo168 */
  @Test
  public void testCompareTo168() {    // Elementti: 168
    Elementti e1 = new Elementti(1, "testi", 2, 3); 
    Elementti e2 = new Elementti(2, "testi2", 5, 1); 
    Elementti e4 = new Elementti(4, "testi4", 6, 5); 
    assertEquals("From: Elementti line: 172", 1, e1.compareTo(e2)); assertEquals("From: Elementti line: 172", -1, e2.compareTo(e1)); 
    assertEquals("From: Elementti line: 173", 0, e2.compareTo(e4)); assertEquals("From: Elementti line: 173", 0, e4.compareTo(e2)); 
    assertEquals("From: Elementti line: 174", 0, e4.compareTo(e1)); 
    assertEquals("From: Elementti line: 175", 0, e1.compareTo(null)); 
  } // Generated by ComTest END
}