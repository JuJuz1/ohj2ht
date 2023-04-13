/**
 * 
 */
package rekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

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
    private String tiedostoNimi = "iat.dat";
    Collection<Ika> iat = new HashSet<Ika>(); // HashSet ikäalkioille
    
    
    /**
     * Oletusmuodostaja
     */
    public Iat() {
        // Oletuksena tiedostoNimi = iat.dat
    }
    
    
    /**
     * Alustaa iat-olion tiedostonimellä.
     * Testaamista varten.
     * @param tiedNimi Tiedoston nimi
     */
    public Iat(String tiedNimi) {
        this.tiedostoNimi = tiedNimi;
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
     * @return tiedostoNimi
     */
    private String getTiedostoNimi() {
        return this.tiedostoNimi;
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
     * Lukee iät tiedostosta ja lisää ne iät-collectioniin.
     * Jos tiedosto ei aukea tulostaa System.err-tietovirtaan "Tiedosto ei aukea." + virheen message.
     * @example
     * <pre name="test">
     *   #THROWS IOException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "iatTest.dat";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|ika\n 1 |0-10\n 2 |10-20\n 3 |20-30\n 4 |30-40");
     *   Iat i = new Iat(tiedosto);
     *   i.lueTiedostosta();
     *   i.getLkm() === 4;
     *   i.etsiIka(1).getIka() === "0-10";
     *   i.etsiIka(2).getIka() === "10-20";
     *   i.etsiIka(4).getIka() === "30-40";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     * </pre>
     */
    public void lueTiedostosta() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(this.getTiedostoNimi())))){
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if (!rivi.startsWith(";")) iat.add(new Ika(rivi));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto ei aukea." + e.getMessage());
        }
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
