/**
 * 
 */
package rekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Sisältää listan elementeistä
 * Osaa lisätä elementin
 * TODO: Lukee elementit tiedostosta
 * Osaa etsiä elementin ID:n perusteella
 * @author Juuso Piippo & Elias Lehtinen
 * @version 15.3.2023
 *
 */
public class Elementit {
    
    // private int lkm;
    // private int maxLkm;
    private String tiedostoNimi = "elementit.dat";
    private final Collection<Elementti> alkiot = new ArrayList<Elementti>();  // Lista elementeille
    
    
    /**
     * Alustetaan elementit-olio oletusarvoilla.
     */
    public Elementit() {
        // tiedostoNimi oletusarvo = "elementit.dat"
        // alkiot oletusarvo = new ArrayList<Elementti>()
    }
    
    
    /**
     * Asettaa uuden tiedoston nimen
     * @param tiedNimi Uusi tiedoston nimi
     */
    public Elementit(String tiedNimi) {
        this.tiedostoNimi = tiedNimi;
    }
    
    
    /**
     * Lisää elementin listaan
     * @param elementti lisättävä elementti
     */
    public void lisaa(Elementti elementti) {
        alkiot.add(elementti);
    }
    
    
    /**
     * Etsii elementin ID:n perusteella
     * @param ID etsittävä ID
     * @return se elementti, jolla on etsittävä ID
     * jos ei löydy -> null
     * @example
     * <pre name="test">
     *      Elementit elementit = new Elementit();
     *      Elementti e1 = new Elementti(1, "testi", 2, 3);
     *      elementit.getLkm() === 0;
     *      Elementti e2 = new Elementti(2, "testi2", 5, 1);
     *      elementit.lisaa(e1); elementit.lisaa(e2);
     *      elementit.etsiElementti(2) === e2;
     *      elementit.etsiElementti(1).getID() === e2.getID() - 1;
     *      elementit.getLkm() === 2;
     * </pre>
     */
    public Elementti etsiElementti(int ID) {
        for (Elementti elementti : alkiot) {
            if (elementti.getID() == ID) 
                return elementti;
        }
        return null;
    }
    
    
    /**
     * @return alkioiden lkm
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * @return tiedostoNimi
     */
    public String getTiedostoNimi() {
        return this.tiedostoNimi;
    }
    
    
    /**
     * Lukee elementit tiedostosta ja lisää ne alkiot-listaan.
     * Jos tiedosto ei aukea tulostaa System.err-tietovirtaan "Tiedosto ei aukea." + virheen message.
     * @example
     * <pre name="test">
     *   #THROWS IOException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "elementitTest.dat";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|elementti    |vahvuusID  |heikkousID\n 1 |vesi         |2          |6\n 2 |tuli         |4          |1\n 3 |maa          |5          |4");
     *   Elementit el = new Elementit(tiedosto);
     *   el.lueTiedostosta();
     *   el.getLkm() === 3;
     *   el.etsiElementti(1).getNimi() === "vesi";
     *   el.etsiElementti(2).getVahvuusID() === 4;
     *   el.etsiElementti(3).getHeikkousID() === 4;
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     * </pre>
     */
    public void lueTiedostosta() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(this.getTiedostoNimi())))){
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if (!rivi.startsWith(";")) alkiot.add(new Elementti(rivi));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea." + e.getMessage());
        }
    }
    
    
    /**
     * Alustaa Elementit-olion kolmella elementillä kokeilua varten
     */
    public void alustaElementeilla() {
        this.alkiot.clear();
        this.lisaa(new Elementti(1, "vesi", 2, 6));
        this.lisaa(new Elementti(2, "tuli", 4, 1));
        this.lisaa(new Elementti(3, "maa", 5, 4));
    }
    
    
    /**
     * @param args ei kayt.
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
