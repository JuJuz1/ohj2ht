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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Sisältää listan elementeistä
 * Osaa lisätä elementin
 * Lukee elementit tiedostosta
 * Tallentaa elementin tiedostoon
 * Osaa etsiä elementin ID:n perusteella
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 13.4.2023
 *
 */
public class Elementit {
    
    // private int lkm;
    // private int maxLkm;
    private String tiedostoNimi = "elementit.dat";
    private final Collection<Elementti> alkiot = new ArrayList<Elementti>();  // Lista elementeille
    private boolean muutettu = false;
    
    
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
        muutettu = true;
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
     * @throws SailoException Heittää, jos tiedostoa ei löydy
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
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
    public void lueTiedostosta() throws SailoException {
        try (Scanner fi = new Scanner(new FileInputStream(new File(this.getTiedostoNimi())))){
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if (!rivi.startsWith(";")) alkiot.add(new Elementti(rivi));
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa ei löydy: " + e.getMessage());
        }
    }
    
    
    /**
     * Tallentaa elementit tiedostoon (parametrin tiedostoNimi mukaan)
     * @throws SailoException Heittää, jos tallennus ei onnistu (tiedosto ei aukea tai ei onnistuta kirjoittamaan)
     * @example
     * <pre name="test">
     *   #THROWS IOException, SailoException
     *   #import java.io.IOException;
     *   #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *   #import java.util.ArrayList;
     *   String tiedosto = "elementitTest.dat";
     *   String tiedostobak = tiedosto.replace(".dat", ".bak");
     *   VertaaTiedosto.tuhoaTiedosto(tiedosto);
     *   VertaaTiedosto.tuhoaTiedosto(tiedostobak);
     *   VertaaTiedosto.kirjoitaTiedosto(tiedosto, ";id|elementti    |vahvuusID  |heikkousID\n 1 |vesi         |2          |6\n 2 |tuli         |4          |1\n 3 |maa          |5          |4");
     *   Elementit el = new Elementit(tiedosto);
     *   el.lueTiedostosta();
     *   el.getLkm() === 3;
     *   el.etsiElementti(1).getNimi() === "vesi";
     *   el.etsiElementti(2).getVahvuusID() === 4;
     *   el.etsiElementti(3).getHeikkousID() === 4;
     *   el.lisaa(new Elementti(4, "testi", 2, 3));
     *   el.tallenna();
     *   
     *   Elementit eDat = new Elementit(tiedosto);      eDat.lueTiedostosta();
     *   Elementit eBak = new Elementit(tiedostobak);   eBak.lueTiedostosta();
     *   eDat.etsiElementti(3).getNimi() === "maa";     eBak.etsiElementti(3).getNimi() === "maa";
     *   eDat.etsiElementti(4).getNimi() === "testi";   eBak.etsiElementti(4) === null;
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
                    Elementti e = etsiElementti(i);
                    fo.println(String.format("% 3d|%s|%d            |%d", e.getID(), e.getNimi(), e.getVahvuusID(), e.getHeikkousID())); 
                }
            } catch ( FileNotFoundException ex ) { 
                throw new SailoException("Tiedosto " + fDat.getName() + " ei aukea"); 
            } catch ( IOException ex ) { 
                throw new SailoException("Tiedoston " + fDat.getName() + " kirjoittamisessa ongelmia"); 
            } 
     
            muutettu = false; 
    }
    
    
    /**
     * Alustaa Elementit-olion kolmella elementillä kokeilua varten
     * TODO: poista
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
