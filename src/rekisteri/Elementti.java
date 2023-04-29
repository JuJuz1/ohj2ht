package rekisteri;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Sisältää elementin tiedot (parametrit).
 * Osaa kertoa nimen ja ID:n
 * Osaa vertailla itseä toiseen elementtiin
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 29.4.2023
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
     * e1.getNimi() === "testi";   e1.getID() === 1;   e1.getVahvuusID() === 2;   e1.getHeikkousID() === 3;
     * e2.getNimi() === "testi2";   e2.getID() === 2;
     * e1.setNimi("testitesti");   e1.setID(4);        e1.setVahvuusID(1);        e1.setHeikkousID(6);
     * e1.getNimi() === "testitesti";  e1.getID() === 4;   e1.getVahvuusID() === 1;   e1.getHeikkousID() === 6;
     * </pre>
     */
    public Elementti(int id, String nimi, int vahvuus, int heikkous) {
        this.ID = id;
        this.nimi = nimi;
        this.vahvuusID = vahvuus;
        this.heikkousID = heikkous;
    }
    
    
    /**
     * Muodostaja.
     * Luo uuden elementin merkkijonosta kutsumalla parse-metodia.
     * Jos jono on väärän muotoinen (liikaa tai liian vähän kenttiä) alustetaan oletusarvoilla (tyhjillä)
     * @param jono Merkkijono, joka sisältää elementin tiedot muodossa  "id |elementti |vahvuusid |heikkousid"
     * @example
     * <pre name="test">
     *   Elementti vesi = new Elementti(" 1 |vesi         |2          |6");
     *   vesi.getNimi() === "vesi";   vesi.getID() === 1;   vesi.getVahvuusID() === 2;   vesi.getHeikkousID() === 6;
     *   Elementti tuli = new Elementti(" 2 | tuli|    32|testitesti");
     *   tuli.getNimi() === "tuli";   tuli.getID() === 2;   tuli.getVahvuusID() === 32;  tuli.getHeikkousID() === -1;
     *   Elementti maa = new Elementti("maa| 2|3");
     *   maa.getNimi() === null;        maa.getID() === 0;    maa.getVahvuusID() === 0;    maa.getHeikkousID() === 0;
     * </pre>
     */
    public Elementti(String jono) {
        this.parse(jono); // Palauttaa false ja ei tee muutoksia attribuuttien arvoihin, jos jono väärän muotoinen
    }
    
    
    /**
     * Alustaa elementin attribuutit merkkijonosta luetuilla arvoilla.
     * Jos kokonaislukua ei voida lukea, oletusarvo on -1.
     * @param jono Merkkijono, josta attribuuttien arvot luetaan.
     * @return true, jos onnistui, false jos merkkijono on väärän muotoinen (liian lyhyt tai pitkä)
     */
    public boolean parse(String jono) {
        String[] tiedot = jono.split(" *\\| *");
        if (tiedot.length != 4) return false;
        this.setID(Mjonot.erotaInt(tiedot[0], -1));
        this.setNimi(tiedot[1]);
        this.setVahvuusID(Mjonot.erotaInt(tiedot[2], -1));
        this.setHeikkousID(Mjonot.erotaInt(tiedot[3], -1));
        return true;
    }
    
    
    /**
     * Palauttaa elementin nimen. Testit muodostajan yhteydessä.
     * @return Elementin nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Asettaa elementin nimi-attribuutin arvon
     * @param nimi Elementin uusi nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     * Palauttaa elementin ID:n. Testit muodostajan yhteydessä.
     * @return Elementin ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * Asettaa elementin ID-attribuutin arvon
     * @param id ID:n uusi arvo
     */
    public void setID(int id) {
        this.ID = id;
    }
    
    
    /**
     * Palauttaa vahvuusID:n. Testit muodostajan yhteydessä.
     * @return Sen elementin ID, jota vastaan tämä elementti on vahva
     */
    public int getVahvuusID() {
        return vahvuusID;
    }
    
    
    /**
     * Asettaa elementin vahvuusID-attribuutin uuden arvon
     * @param vid VahvuusID:n uusi arvo
     */
    public void setVahvuusID(int vid) {
        this.vahvuusID = vid;
    }
    
    
    /**
     * Palauttaa heikkousID:n. Testit muodostajan yhteydessä.
     * @return Sen elementin ID, jota vastaan tämä elementti on heikko
     */
    public int getHeikkousID() {
        return heikkousID;
    }
    
    
    /**
     * Asettaa elementin heikkousID-attribuutin uuden arvon
     * @param hid heikkousID:n uusi arvo
     */
    public void setHeikkousID(int hid) {
        this.heikkousID = hid;
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
     * e1.compareTo(null) === 0;
     * </pre>
     */
    public int compareTo(Elementti e2) {
        if (e2 == null) return 0;
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
