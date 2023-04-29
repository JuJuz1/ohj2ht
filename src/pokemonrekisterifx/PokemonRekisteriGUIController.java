package pokemonrekisterifx;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;
import rekisteri.SailoException;

//import javafx.event.ActionEvent; sama homma ku handleTallenna

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 27.4.2023
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * 
 * Pokemonin muokkaaminen ja lisääminen eivät toimi.
 * Luultavimmin liittyy Controllerin closestage-metodiin,
 * joka ei osaa palauttaa oikeaa viitettä muokattuun / lisättyyn
 * pokemoniin
 */
public class PokemonRekisteriGUIController implements Initializable {

    @FXML private ListChooser<Pokemon> chooserPokemonit;
    
    @FXML private TextField hakuEhto;
    @FXML private ComboBoxChooser<String> cbKentat;
    
    @FXML private CheckBox cb1;
    @FXML private CheckBox cb2;
    @FXML private CheckBox cb3;
    @FXML private CheckBox cb4;
    @FXML private CheckBox cb5;
    @FXML private CheckBox cb6;
    @FXML private CheckBox cb7;
    @FXML private CheckBox cb8;
    @FXML private CheckBox cb9;
    @FXML private CheckBox cb10;
    @FXML private CheckBox cb11;
    @FXML private CheckBox cb12;
    
    @FXML private TextField textIkaMin;
    @FXML private TextField textIkaMax;
    
    @FXML private TextField editNimi;
    @FXML private TextField editElementti1;
    @FXML private TextField editElementti2;
    @FXML private TextField editVahvuus;
    @FXML private TextField editIka;
    @FXML private TextField editEvoluutio;
    @FXML private TextArea areaLisa;
    
    @FXML private TextField editNimiEv1;
    @FXML private TextField editElementti1Ev1;
    @FXML private TextField editElementti2Ev1;
    @FXML private TextField editVahvuusEv1;
    @FXML private TextField editIkaEv1;
    @FXML private TextField editEvoluutioEv1;
    @FXML private TextArea areaLisaEv1;
    
    @FXML private TextField editNimiEv2;
    @FXML private TextField editElementti1Ev2;
    @FXML private TextField editElementti2Ev2;
    @FXML private TextField editVahvuusEv2;
    @FXML private TextField editIkaEv2;
    @FXML private TextField editEvoluutioEv2;
    @FXML private TextArea areaLisaEv2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML private void handleTallenna(/* ActionEvent event (ehkä tarvitaan jatkossa) */) {
        tallenna();
    }


    @FXML private void handleTulosta() {
        tulosta();
    }


    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }


    @FXML private void handleLisaaPokemon() {
        uusiPokemon();
        /* ModalController.showModal(PokemonRekisteriGUIController.class.getResource
            ("PokemonRekisteriLisaaUusi.fxml"),"Pokemonin lisäys", null, ""); */
    }


    @FXML private void handlePoistaPokemon() {
        poistaPokemon();
    }


    @FXML private void handleApua() {
        Dialogs.showMessageDialog("Ei osata vielä antaa apua");
    }


    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Ei osata vielä antaa tietoja");
    }


    @FXML private void handleMuokkaa() {
        muokkaa();
    }

    
    @FXML private void handleHakuehto() {
        hae(0);
    }
    

    @FXML private void handleHae() {
        hae(0);
    }


    @FXML private void handlePeruuta() {
        Dialogs.showMessageDialog("Ei osata vielä peruuttaa");
    }

    // FXML
    // ===================================================================
    // Muu koodi

    private Rekisteri rekisteri;
    private Pokemon pokemonKohdalla;
    private int[] hakuehdot = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 10000};
    private CheckBox[] checkE;
    private CheckBox[] checkI;
    private TextField[] text;
    
    
    /**
     * Alustaa listan
     */
    protected void alusta() {
        cbKentat.clear(); 
        cbKentat.add("Nimi: A -> Ö", null);
        cbKentat.add("Nimi: Ö -> A", null);
        cbKentat.add("Vahvuus: Pienin -> Suurin", null);
        cbKentat.add("Vahvuus: Suurin -> Pienin", null);
        cbKentat.add("Ikä: Nuorin -> Vanhin", null);
        cbKentat.add("Ikä: Vanhin -> Nuorin", null);
        cbKentat.getSelectionModel().select(0); 
        
        checkE = new CheckBox[] {cb1, cb2, cb3, cb4, cb5, cb6, cb7};
        checkI = new CheckBox[] {cb8, cb9, cb10, cb11, cb12};
        text = new TextField[] {textIkaMin, textIkaMax};
        
        int i = 0;
        for (CheckBox cb : checkE) {
                if (cb != null) {
                cb.setSelected(true);
                final int k = ++i;
                cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    kasitteleMuutosHakuEhtoonCBele(k, newValue);
                });
                }
            }
        
        i = 0;
        for (CheckBox cb : checkI) {
            if (cb != null) {
            cb.setSelected(true);
            final int k = ++i;
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                kasitteleMuutosHakuEhtoonCBika(k, newValue);
            });
            }
        }
        
        i = 0;
        for (TextField t : text) {
            if (t != null) {
                final int k = ++i;
                t.setOnKeyReleased(e -> kasitteleMuutosHakuEhtoon(k, (TextField)(e.getSource())));
                }
            }
        
        chooserPokemonit.clear();
        chooserPokemonit.addSelectionListener(e -> naytaPokemon());
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon elementeille (muutetaan arvo 0 tai 1)
     * @param k checkboxin kentän id (1-7)
     * @param arvo uusi arvo
     */
    public void kasitteleMuutosHakuEhtoonCBele(int k, boolean arvo) {
        boolean kaikki = cb1.isSelected();
        
        int i = k-1;
        if (!arvo && kaikki) {
            cb1.setSelected(false);
            hakuehdot[i] = 0;
            return;
        }
        if (!arvo) {
            hakuehdot[i] = 0;
            return;
        }
        if (arvo && kaikki) {
            for (CheckBox cb : checkE) {
                cb.setSelected(true);
            }
            for (int j = 0; j < 8; j++) {
            hakuehdot[j] = 1;
            }
        }
        if (arvo) {
            hakuehdot[i] = 1;
        }
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon iälle (muutetaan arvo 0 tai 1)
     * @param k checkboxin kentän id (1-5)
     * @param arvo uusi arvo
     */
    public void kasitteleMuutosHakuEhtoonCBika(int k, boolean arvo) {
        int i = k-1+7; // +7 koska i = {0, 1, 2, 3, 4};
        
        if (!arvo) {
            hakuehdot[i] = 0;
            return;
        }
        if (arvo) {
            hakuehdot[i] = 1;
            return;
        }
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon (muutetaan arvo vastaamaan vahvuutta)
     * @param k textfieldin kentän id (1 tai 2 == min tai max)
     * @param vahvuus uusi arvo vahvuudelle
     */
    public void kasitteleMuutosHakuEhtoon(int k, TextField vahvuus) {
        String v = vahvuus.getText();
        int uusivahvuus = Mjonot.erotaInt(v, -1);
        int minVahv;
        int maxVahv;
        if (k == 1) {
            maxVahv = hakuehdot[13];
            if (maxVahv <= uusivahvuus) { 
                vahvuus.setText("0");
                hakuehdot[12] = 0;
            }
            else hakuehdot[12] = uusivahvuus;
            return;
        }
        if (k == 2) {
            minVahv = hakuehdot[12];
            if (uusivahvuus <= minVahv) { 
                vahvuus.setText("10000");
                hakuehdot[13] = 10000;
            }
            else hakuehdot[13] = uusivahvuus;
            return;
        }
    }
    
    
    
    /**
     * @return rekisteri
     */
    public Rekisteri getRekisteri() {
        return rekisteri;
    }

    /**
     * Pokemonin tietojen muokkaus
     */
    public void muokkaa() {
        Pokemon pokemon = chooserPokemonit.getSelectedObject();
        if ( pokemon == null ) return;
        if ( pokemonKohdalla == null ) return;
        try { 
            Pokemon muokattu;
            muokattu = PokemonRekisteriPokemonController.kysyPokemon(null, pokemon.clone(), rekisteri);
            if ( muokattu == null ) return;
            rekisteri.korvaaTaiLisaa(muokattu);
            hae(muokattu.getID());
         
        } catch (CloneNotSupportedException e) 
        {
            //
        } 
        /*
        catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        } 
        */
    }

    /**
     * @param rekisteri rekisteri
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaPokemon();
    }
    
    
    /**
     * Avataan dialogi pokemonin luonnille
     */
    protected void uusiPokemon() {
        Pokemon uusi = new Pokemon();
        uusi.vastaa_pikachu();
        uusi = PokemonRekisteriPokemonController.kysyPokemon(null, uusi, rekisteri);
        if ( uusi == null ) return;
        uusi.rekisteroi();
        rekisteri.lisaa(uusi);
        hae(uusi.getID());
    }
    
    
    /**
     * Poistetaan pokemon taulukosta
     */
    protected void poistaPokemon() {
        Pokemon poistettava = pokemonKohdalla;
        if (poistettava == null) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko pokemon: " + 
        poistettava.getNimi(), "Kyllä", "Ei") )
            return;
        rekisteri.poista(poistettava);
        int index = chooserPokemonit.getSelectedIndex();
        hae(0);
        chooserPokemonit.setSelectedIndex(index);
    }
    


    /**
     * Nayttaa valitun pokemonin tiedot ja seuraavat evoluutiot
     */
    protected void naytaPokemon() {
        Pokemon pokemon = chooserPokemonit.getSelectedObject();
        pokemonKohdalla = chooserPokemonit.getSelectedObject();
        if (pokemon == null) return;
        int evoluutio = pokemon.getEvoluutio();
        Pokemon ev1 = pokemon;
        Pokemon ev2 = pokemon;
        if (evoluutio < 3) ev1 = rekisteri.etsiPokemon(pokemon.getEvoluutioIDSeuraava());
        if (evoluutio < 2 && ev1 != null) ev2 = rekisteri.etsiPokemon(ev1.getEvoluutioIDSeuraava());
        
        editNimi.setText(pokemon.getNimi());
        editElementti1.setText(rekisteri.annaElementti(pokemon, 1));
        editElementti2.setText(rekisteri.annaElementti(pokemon, 2));
        editVahvuus.setText(""+pokemon.getVahvuus());
        editIka.setText(rekisteri.annaIka(pokemon));
        editEvoluutio.setText(""+evoluutio);
        areaLisa.setText(pokemon.getLisatiedot());
        
        if (ev1 != null && ev1 != pokemon) {
            editNimiEv1.setText(ev1.getNimi());
            editElementti1Ev1.setText(rekisteri.annaElementti(ev1, 1));
            editElementti2Ev1.setText(rekisteri.annaElementti(ev1, 2));
            editVahvuusEv1.setText(""+ev1.getVahvuus());
            editIkaEv1.setText(rekisteri.annaIka(ev1));
            editEvoluutioEv1.setText(""+ev1.getEvoluutio());
            areaLisaEv1.setText(ev1.getLisatiedot());
        } else {
            tyhjennaEvol1();
        }
        
        if (ev2 != null && ev2 != pokemon) {
            editNimiEv2.setText(ev2.getNimi());
            editElementti1Ev2.setText(rekisteri.annaElementti(ev2, 1));
            editElementti2Ev2.setText(rekisteri.annaElementti(ev2, 2));
            editVahvuusEv2.setText(""+ev2.getVahvuus());
            editIkaEv2.setText(rekisteri.annaIka(ev2));
            editEvoluutioEv2.setText(""+ev2.getEvoluutio());
            areaLisaEv2.setText(ev2.getLisatiedot());
        } else {
            tyhjennaEvol2();
        }
        /*
        if (pokemonKohdalla == null)
            return;

        areaLisa.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLisa)) {
            pokemonKohdalla.tulosta(os);
            rekisteri.tulostaElementit(pokemonKohdalla, os);
            rekisteri.tulostaIka(pokemonKohdalla, os);
        }
        */
    }
    
    
    /**
     * Tyhjentää kentät jotka näyttävät valitun pokemonin tiedot
     */
    protected void tyhjennaPokemon() {
        editNimi.setText("");
        editElementti1.setText("");
        editElementti2.setText("");
        editVahvuus.setText("");
        editIka.setText("");
        editEvoluutio.setText("");
        areaLisa.setText("");
    }
    
    
    /**
     * Tyhjentää kentät jotka näyttävät seuraavan evoluution tiedot
     */
    protected void tyhjennaEvol1() {
        editNimiEv1.setText("");
        editElementti1Ev1.setText("");
        editElementti2Ev1.setText("");
        editVahvuusEv1.setText("");
        editIkaEv1.setText("");
        editEvoluutioEv1.setText("");
        areaLisaEv1.setText("");
    }
    
    
    /**
     * Tyhjentää kentät jotka näyttävät viimeisen evoluution tiedot
     */
    protected void tyhjennaEvol2() {
        editNimiEv2.setText("");
        editElementti1Ev2.setText("");
        editElementti2Ev2.setText("");
        editVahvuusEv2.setText("");
        editIkaEv2.setText("");
        editEvoluutioEv2.setText("");
        areaLisaEv2.setText("");
    }


    /**
     * Alustaa pokemonit listan ja valitsee uusimman luodun pokemonin
     * @param id pokemonin ID
     */
    protected void hae(int id) {
        // Jos on hakuehtoa
        String ehto = hakuEhto.getText();
        if (ehto.indexOf('*') < 0 && 0 < ehto.length()) ehto = "*" + ehto + "*";
        else ehto = "";
        
        int k = cbKentat.getSelectionModel().getSelectedIndex() + 1;
        
        chooserPokemonit.clear();

        int index = 0;
        boolean takaperin;
        takaperin = (k % 2 == 0);
        List<Pokemon> sopivat;
        sopivat =  (List<Pokemon>) rekisteri.etsiHakuehdolla(ehto, k, takaperin, hakuehdot);
        for (int i = 0; i < sopivat.size(); i++) {
            Pokemon p = sopivat.get(i);
            if (p.getID() == id) index = i;
            chooserPokemonit.add(p.getNimi(), p);
        }
        chooserPokemonit.setSelectedIndex(index);
    }


    /**
     * Tallentaa tiedot (pokemonit, elementit ja iät) jos niihin on tehty muutoksia.
     * Jos ei muutoksia, tietoja ei tallenneta.
     * @return null, jos tallentaminen onnistuu, jos on ongelmia, palauttaa virheen tekstinä
     */
    private String tallenna() {
        try {
            rekisteri.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia: " + ex.getMessage());
            return ex.getMessage();
        }
        
    }
    
    
    /**
     * Tarkistaa onko tiedot tallennettu (ennen ohjelman sulkemista)
     * @return true, jos on tallennettu ja sovelluksen voi sulkea
     */
    public boolean voikoSulkea() {
        this.tallenna();
        return true;
    }
    
    
    /**
     * Lukee tiedot (pokemonit, elementit ja iät) tiedostosta.
     * @return null, jos onnistuu, jos on ongelmia, palauttaa virheen tekstinä
     */
    protected String lueTiedostosta() {
        try {
            rekisteri.lueTiedostosta();
            hae(0);
            return null;
        } catch (SailoException ex) {
            hae(0);
            Dialogs.showMessageDialog("Tiedostosta lukemisessa ongelmia: " + ex.getMessage());
            return ex.getMessage();
        }
    }


    // Tulostetaan näkymä
    private void tulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
}