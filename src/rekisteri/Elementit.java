/**
 * 
 */
package rekisteri;

import java.util.ArrayList;
import java.util.Collection;

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
    private String tiedostoNimi;
    private final Collection<Elementti> alkiot = new ArrayList<Elementti>();  // Lista elementeille
    
    
    /**
     * Myöhemmin alkiot luetaan tiedostosta
     */
    public Elementit() {
        // Ei tarvita vielä
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
