package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.04.13 11:49:17 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class IkaTest {



  // Generated by ComTest BEGIN
  /** testIka26 */
  @Test
  public void testIka26() {    // Ika: 26
    Ika ika1 = new Ika(1, "0-10"); 
    assertEquals("From: Ika line: 28", 1, ika1.getID()); 
    assertEquals("From: Ika line: 29", "0-10", ika1.getIka()); 
    assertEquals("From: Ika line: 30", "0-10", ika1.toString()); 
    Ika ika2 = new Ika(2, "30-40"); 
    assertEquals("From: Ika line: 32", ika1.getID(), ika2.getID() - 1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIka48 */
  @Test
  public void testIka48() {    // Ika: 48
    Ika i1 = new Ika("1|53-95"); Ika i2 = new Ika("   2  |33-23     "); Ika i3 = new Ika(""); 
    assertEquals("From: Ika line: 50", 1, i1.getID()); assertEquals("From: Ika line: 50", "53-95", i1.getIka()); 
    assertEquals("From: Ika line: 51", 2, i2.getID()); assertEquals("From: Ika line: 51", "33-23", i2.getIka()); 
    assertEquals("From: Ika line: 52", 0, i3.getID()); assertEquals("From: Ika line: 52", null, i3.getIka()); 
  } // Generated by ComTest END
}