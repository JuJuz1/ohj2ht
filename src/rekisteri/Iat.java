/**
 * 
 */
package rekisteri;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sisältää ikäoliot
 * Osaa lisätä iän
 * TODO: Osaa lukea iät tieodstosta
 * Osaa etsiä iän ID:n perusteella
 * @author Juuso Piippo & Elias Lehtinen
 * @version 15.3.2023
 *
 */
public class Iat {
    
    // private int lkm;
    // private int maxLkm;
    Collection<Ika> iat = new HashSet<Ika>(); // HashSet ikäalkioille
    
    
    /**
     * Iät luetaan tiedostosta
     */
    public Iat() {
        // Ei tarvita
    }

    
    /**
     * @param ika ikä
     */
    public void lisaa(Ika ika) {
        iat.add(ika);
    }
    
    
    /**
     * @return alkioiden lkm
     */
    public int getLkm() {
        return iat.size();
    }
    
    
    /**
     * Etsii setistä iän ID:n perusteella
     * @param ID ID
     * @return ikä, jolla kyseinen ID
     * jos ei löydy -> null
     * @example
     * <pre name="test">
     *      Iat i = new Iat();
     *      Ika i1 = new Ika(1, "10-20");
     *      i.getLkm() === 0;
     *      Ika i2 = new Ika(2, "20-30");
     *      i.lisaa(i1); i.lisaa(i2);
     *      i.etsiIka(2) === i2;
     *      i.etsiIka(1).getID() === i2.getID() - 1;
     *      i.getLkm() === 2;
     * </pre>
     */
    public Ika etsiIka(int ID) {
        for (Ika ika : iat) {
            if (ika.getID() == ID) 
                return ika;
        }
        return null;
    }
    
    
    /**
     * Alustaa Iat-olion kolmella iällä kokeilua varten
     */
    public void alustaIkaLuokilla() {
        this.iat.clear();
        this.lisaa(new Ika(1, "0-10"));
        this.lisaa(new Ika(2, "20-30"));
        this.lisaa(new Ika(3, "30-40"));
    }
    
    
    /**
     * @param args ei kayt.
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
