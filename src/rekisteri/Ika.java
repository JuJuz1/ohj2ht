/**
 * 
 */
package rekisteri;

/**
 * Sisältää ID:n ja ikäalueen
 * Osaa kertoa ID:n ja ikäalueen
 * @author Juuso Piippo & Elias Lehtinen
 * @version 15.3.2023
 *
 */
public class Ika {
    
    private int ID;         // Iän ID
    private String ika;     // Ikäalue
    
    
    /**
     * Muodostaja
     * @param ID ID
     * @param ika ikäalue
     * @example
     * <pre name="test">
     *      Ika ika1 = new Ika(1, "0-10");
     *      ika1.getID() === 1;
     *      ika1.getIka() === "0-10";
     *      ika1.toString() === "0-10";
     *      Ika ika2 = new Ika(2, "30-40");
     *      ika2.getID() - 1 === ika1.getID();
     * </pre>
     */
    public Ika (int ID, String ika) {
        this.ID = ID;
        this.ika = ika;
    }
    
    
    /**
     * @return ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * @return ikä-alue
     */
    public String getIka() {
        return ika;
    }
    
    
    @Override
    public String toString() {
        return this.getIka();
    }

    
    /**
     * @param args ei kayt.
     */
    public static void main(String[] args) {
        Ika ika1 = new Ika(2, "20-30");
        System.out.println(ika1.getIka());
        System.out.println(ika1);
    }

}
