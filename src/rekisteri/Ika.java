/**
 * 
 */
package rekisteri;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Sisältää ID:n ja ikäalueen
 * Osaa kertoa ID:n ja ikäalueen
 * @author Juuso Piippo & Elias Lehtinen
 * @version 14.3.2023
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
     * TODO: tee parempi käsittely jos jono väärän muotoinen, tällä hetkellä ika null
     * Muodostaja.
     * Luo uuden ikäryhmän merkkijonosta kutsumalla parse-metodia.
     * Jos jono on väärän muotoinen (liikaa tai liian vähän kenttiä) alustetaan oletusarvoilla (tyhjillä)
     * @param jono Merkkijono, joka sisältää iän tiedot muodossa  "id |ika"
     * @example
     * <pre name="test">
     *   Ika i1 = new Ika("1|53-95");  Ika i2 = new Ika("   2  |33-23     ");  Ika i3 = new Ika("");
     *   i1.getID() === 1;   i1.getIka() === "53-95";
     *   i2.getID() === 2;   i2.getIka() === "33-23";
     *   i3.getID() === 0;   i3.getIka() === null;
     * </pre>
     */
    public Ika(String jono) {
        this.parse(jono); // Palauttaa false ja ei tee muutoksia attribuuttien arvoihin, jos jono väärän muotoinen
    }
    
    
    /**
     * Alustaa ikäryhmän attribuutit merkkijonosta luetuilla arvoilla.
     * Jos kokonaislukua ei voida lukea, oletusarvo on -1.
     * 
     * @param jono Merkkijono, josta attribuuttien arvot luetaan.
     * @return true, jos onnistui, false jos merkkijono on väärän muotoinen (liian lyhyt tai pitkä)
     */
    public boolean parse(String jono) {
        String[] tiedot = jono.split(" *\\| *");
        if (tiedot.length != 2) return false;
        this.setID(Mjonot.erotaInt(tiedot[0], -1));
        this.setIka(tiedot[1].trim());
        return true;
    }
    
    
    /**
     * @return ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * Asettaa ikäryhmän ID:n
     * @param id Uusi ID:n arvo
     */
    public void setID(int id) {
        this.ID = id;
    }
    
    
    /**
     * @return ikä-alue
     */
    public String getIka() {
        return ika;
    }
    
    
    /**
     * Asettaa ika-parametrille uuden arvon
     * @param ika Uusi ikä
     */
    public void setIka(String ika) {
        this.ika = ika;
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
