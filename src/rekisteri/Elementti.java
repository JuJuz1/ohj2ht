package rekisteri;

/**
 * Sisältää elementin tiedot (parametrit).
 * Osaa kertoa nimen ja ID:n
 * Osaa vertailla itseä toiseen elementtiin
 * @author Juuso Piippo & Elias Lehtinen
 * @version 14.3.2023
 *
 */
public class Elementti {
    private int ID;             // Elementin yksilöivä ID
    private String nimi;        // Elementin nimi
    private int vahvuusID;      // Sen elementin ID, jota vastaan tämä elementti on erityisen vahva
    private int heikkousID;     // Sen elementin ID, jota vastaan tämä elementti on erityisen heikko
    
    
    /**
     * Muodostaja.
     * Alustaa elementin annetuilla tiedoilla.
     * @param id Elementin yksilöivä ID
     * @param nimi Elementin suomenkielinen nimi
     * @param vahvuus Sen elementin ID, jota vastaan tämä elementti on erityisen vahva
     * @param heikkous Sen elementin ID, jota vastaan tämä elementti on erityisen heikko
     * @example
     * <pre name="test">
     * Elementti e1 = new Elementti(1, "testi", 2, 3);
     * Elementti e2 = new Elementti(2, "testi2", 5, 1);
     * e1.getNimi() === "testi";
     * e1.getID() === 1;
     * e1.getVahvuusID() === 2;
     * e1.getHeikkousID() === 3;
     * e2.getNimi() === "testi2";
     * e2.getID() === 2;
     * </pre>
     */
    public Elementti(int id, String nimi, int vahvuus, int heikkous) {
        this.ID = id;
        this.nimi = nimi;
        this.vahvuusID = vahvuus;
        this.heikkousID = heikkous;
    }
    
    
    /**
     * Palauttaa elementin nimen. Testit muodostajan yhteydessä.
     * @return Elementin nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Palauttaa elementin ID:n. Testit muodostajan yhteydessä.
     * @return Elementin ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * Palauttaa vahvuusID:n. Testit muodostajan yhteydessä.
     * @return Sen elementin ID, jota vastaan tämä elementti on vahva
     */
    public int getVahvuusID() {
        return vahvuusID;
    }
    
    
    /**
     * Palauttaa heikkousID:n. Testit muodostajan yhteydessä.
     * @return Sen elementin ID, jota vastaan tämä elementti on heikko
     */
    public int getHeikkousID() {
        return heikkousID;
    }
    
    
    /**
     * Vertaa itseä toiseen elementtiin.
     * @param e2 Elementti, johon verrataan
     * @return 1, jos erityisen vahva elementtiä e2 vastaan.
     *        -1, jos erityisen heikko elementtiä e2 vastaan.
     *         0, jos ei heikko eikä vahva elementtiä e2 vastaan.
     * @example
     * <pre name="test">
     * Elementti e1 = new Elementti(1, "testi", 2, 3);
     * Elementti e2 = new Elementti(2, "testi2", 5, 1);
     * Elementti e4 = new Elementti(4, "testi4", 6, 5);
     * e1.compareTo(e2) === 1;  e2.compareTo(e1) === -1;
     * e2.compareTo(e4) === 0;  e4.compareTo(e2) === 0;
     * e4.compareTo(e1) === 0;
     * </pre>
     */
    public int compareTo(Elementti e2) {
        int id2 = e2.getID();
        if (this.vahvuusID == id2) return 1;
        if (this.heikkousID == id2) return -1;
        return 0;
    }
    
    
    /**
     * Testipääohjelma Elementti-luokalle
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Elementti vesi = new Elementti(1, "vesi", 2, 6);
        Elementti tuli = new Elementti(2, "tuli", 4, 1);
        Elementti maa = new Elementti(3, "maa", 5, 4);
        System.out.println(tuli.getID());   // Tulostaa: 2
        System.out.println(vesi.getID());  // Tulostaa: 1
        System.out.println(maa.getNimi());  // Tulostaa: maa
        if (vesi.compareTo(tuli) < 0) System.out.println("Vesi on heikko tulta vastaan.");
        else if (vesi.compareTo(tuli) > 0) System.out.println("Vesi on vahva tulta vastaan.");
        else if (vesi.compareTo(tuli) == 0) System.out.println("Vesi ei ole vahva eikä heikko tulta vastaan.");
        
        if (maa.compareTo(tuli) < 0) System.out.println("Maa on heikko tulta vastaan.");
        else if (maa.compareTo(tuli) > 0) System.out.println("Maa on vahva tulta vastaan.");
        else if (maa.compareTo(tuli) == 0) System.out.println("Maa ei ole vahva eikä heikko tulta vastaan.");
    }
}
