/**
 * 
 */
package rekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Sisältää ikäoliot
 * Osaa lisätä iän
 * Osaa lukea iät tieodstosta
 * Osaa etsiä iän ID:n perusteella
 * Osaa tallentaa iät tiedostoon
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 13.4.2023
 *
 */
public class Iat {
    
    // private int lkm;
    // private int maxLkm;
    private String tiedostoNimi = "iat.dat";
    private Collection<Ika> iat = new HashSet<Ika>(); // HashSet ikäalkioille
    private boolean muutettu = false;
    
    
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
        this.muutettu = true;
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
     * @throws SailoException Heittää, jos tiedostoa ei löydy
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "iatTest.dat";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|ika\n 1 |0-10\n 2 |10-20\n 3 |20-30\n 4 |30-40");
     *   Iat iThrows = new Iat("eiOle");
     *   iThrows.lueTiedostosta(); #THROWS SailoException
     *   Iat i = new Iat(tiedosto);
     *   i.lueTiedostosta();
     *   i.getLkm() === 4;
     *   i.etsiIka(1).getIka() === "0-10";
     *   i.etsiIka(2).getIka() === "10-20";
     *   i.etsiIka(4).getIka() === "30-40";
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     * </pre>
     */
    public void lueTiedostosta() throws SailoException {
        try (Scanner fi = new Scanner(new FileInputStream(new File(this.getTiedostoNimi())))){
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if (!rivi.startsWith(";")) iat.add(new Ika(rivi));
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa ei löydy: " + e.getMessage());
        }
    }

    
    /**
     * Tallentaa iat tiedostoon (parametrin tiedostoNimi mukaan)
     * @throws SailoException Heittää, jos tallennus ei onnistu (tiedosto ei aukea tai ei onnistuta kirjoittamaan)
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "iatTest.dat";
     *   String tiedostobak = tiedosto.replace(".dat", ".bak");
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.tuhoaTiedosto(tiedostobak);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|ika\n 1 |0-10\n 2 |10-20\n 3 |20-30\n 4 |30-40");
     *   Iat iThrows = new Iat("eiOle");
     *   iThrows.lueTiedostosta(); #THROWS SailoException
     *   Iat i = new Iat(tiedosto);
     *   i.lueTiedostosta();
     *   i.getLkm() === 4;
     *   i.etsiIka(1).getIka() === "0-10";
     *   i.etsiIka(2).getIka() === "10-20";
     *   i.etsiIka(4).getIka() === "30-40";
     *   i.etsiIka(5) === null;
     *   i.lisaa(new Ika(5, "testi"));
     *   i.tallenna();
     *   
     *   Iat iDat = new Iat(tiedosto);      iDat.lueTiedostosta();
     *   Iat iBak = new Iat(tiedostobak);   iBak.lueTiedostosta();
     *   iDat.etsiIka(4).getIka() === "30-40";  iBak.etsiIka(4).getIka() === "30-40";
     *   iDat.etsiIka(5).getIka() === "testi";  iBak.etsiIka(5) === null;
     *   
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.tuhoaTiedosto(tiedostobak);
     * </pre>
     */
    public void tallenna() throws SailoException {
            if ( !muutettu ) return; 
     
            File fBak = new File(getTiedostoNimi().replace(".dat", ".bak")); 
            File fDat = new File(getTiedostoNimi()); 
            fBak.delete();
            fDat.renameTo(fBak); 
     
            try ( PrintWriter fo = new PrintWriter(new FileWriter(fDat.getCanonicalPath())) ) { 
                fo.println(";id|ika");
                for (int i = 1; i <= this.getLkm(); i++) { 
                    Ika ika = etsiIka(i);
                    fo.println(String.format("% 3d|%s", ika.getID(), ika.getIka())); 
                }
            } catch ( FileNotFoundException ex ) { 
                throw new SailoException("Tiedosto " + fDat.getName() + " ei aukea"); 
            } catch ( IOException ex ) { 
                throw new SailoException("Tiedoston " + fDat.getName() + " kirjoittamisessa ongelmia"); 
            } 
     
            muutettu = false; 
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
