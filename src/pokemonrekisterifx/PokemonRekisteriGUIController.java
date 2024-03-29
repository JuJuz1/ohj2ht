package pokemonrekisterifx;

import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;
import rekisteri.SailoException;

/**
 * Controlleri, joka ohjaa käyttöliittymää
 * TODO: tulostus
 * TODO: apua
 * 
 * @author Juuso Piippo & Elias Lehtinen
 * @version 29.4.2023
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 */
public class PokemonRekisteriGUIController implements Initializable {

    @FXML private ListChooser<Pokemon> chooserPokemonit;
    @FXML private ListChooser<Pokemon> chooserPokemonit2; // Vertaile-välilehti
    @FXML private ListChooser<Pokemon> chooserPokemonit3; // Vertaile-välilehti
    
    @FXML private TextField hakuEhto;   
    @FXML private TextField hakuEhto2;  // Vertaile-välilehti
    
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ComboBoxChooser<String> cbKentat2;  // Vertaile-välilehti
    
    // Tarkempi haku:
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
    
    // Vertaile-välilehden tarkempi haku:
    @FXML private CheckBox cb1Vertaile;
    @FXML private CheckBox cb2Vertaile;
    @FXML private CheckBox cb3Vertaile;
    @FXML private CheckBox cb4Vertaile;
    @FXML private CheckBox cb5Vertaile;
    @FXML private CheckBox cb6Vertaile;
    @FXML private CheckBox cb7Vertaile;
    @FXML private CheckBox cb8Vertaile;
    @FXML private CheckBox cb9Vertaile;
    @FXML private CheckBox cb10Vertaile;
    @FXML private CheckBox cb11Vertaile;
    @FXML private CheckBox cb12Vertaile;
    @FXML private TextField textIkaMinVertaile;
    @FXML private TextField textIkaMaxVertaile;
    
    // Pokemonin tiedot:
    @FXML private TextField editNimi;
    @FXML private TextField editElementti1;
    @FXML private TextField editElementti2;
    @FXML private TextField editVahvuus;
    @FXML private TextField editIka;
    @FXML private TextField editEvoluutio;
    @FXML private TextArea areaLisa;
    
    // Seuraava evoluutio:
    @FXML private TextField editNimiEv1;
    @FXML private TextField editElementti1Ev1;
    @FXML private TextField editElementti2Ev1;
    @FXML private TextField editVahvuusEv1;
    @FXML private TextField editIkaEv1;
    @FXML private TextField editEvoluutioEv1;
    @FXML private TextArea areaLisaEv1;
    
    // Sitä seuraava evoluutio:
    @FXML private TextField editNimiEv2;
    @FXML private TextField editElementti1Ev2;
    @FXML private TextField editElementti2Ev2;
    @FXML private TextField editVahvuusEv2;
    @FXML private TextField editIkaEv2;
    @FXML private TextField editEvoluutioEv2;
    @FXML private TextArea areaLisaEv2;
    
    // Vertaile-välilehti, vasen pokemon:
    @FXML private TextField editOtsikkoVas;
    @FXML private TextField editNimiVas;
    @FXML private TextField editElementti1Vas;
    @FXML private TextField editElementti2Vas;
    @FXML private TextField editVahvuusVas;
    @FXML private TextField editIkaVas;
    @FXML private TextField editEvoluutioVas;
    @FXML private TextArea areaLisaVas;
    
    @FXML private TextField editTaisteluElementitVas;
    @FXML private TextField editTaisteluVahvuusVas;
    @FXML private TextField editTaisteluEvoluutioVas;
    @FXML private TextField editTaisteluVoittoVas;
    
    // Vertaile-välilehti, oikea pokemon:
    @FXML private TextField editOtsikkoOik;
    @FXML private TextField editNimiOik;
    @FXML private TextField editElementti1Oik;
    @FXML private TextField editElementti2Oik;
    @FXML private TextField editVahvuusOik;
    @FXML private TextField editIkaOik;
    @FXML private TextField editEvoluutioOik;
    @FXML private TextArea areaLisaOik;
    
    @FXML private TextField editTaisteluElementitOik;
    @FXML private TextField editTaisteluVahvuusOik;
    @FXML private TextField editTaisteluEvoluutioOik;
    @FXML private TextField editTaisteluVoittoOik;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    

    @FXML private void handleTallenna() {
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
    }


    @FXML private void handlePoistaPokemon() {
        poistaPokemon();
    }


    @FXML private void handleApua() {
        Dialogs.showMessageDialog("Ei osata vielä antaa apua");
    }


    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Pokemon-rekisteri\nVersio: "+versio+"\nTekijät:\nJuuso piippo\nElias Lehtinen");
    }


    @FXML private void handleMuokkaa() {
        muokkaa();
    }

    
    @FXML private void handleHakuehto() {
        hae(0);
    }
    
    
    @FXML private void handleHakuehtoVertaile() {
        haeVertaile(0);
    }
    

    @FXML private void handleHae() {
        hae(0);
    }
    
    
    @FXML private void handleHaeVertaile() {
        haeVertaile(0);
    }


    @FXML private void handlePeruuta() {
        Dialogs.showMessageDialog("Ei osata vielä peruuttaa");
    }
    

    // FXML
    // ===================================================================
    // Muu koodi

    private Rekisteri rekisteri;
    private Pokemon pokemonKohdalla;
    private String versio = "29.4.2023";
    // Tiedot-välilehden tarkempi haku:
    private int[] hakuehdot = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 10000};
    private CheckBox[] checkE;
    private CheckBox[] checkI;
    private TextField[] text;
    // Vertaile-välilehden tarkempi haku:
    private int[] hakuehdotVertaile = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 10000};
    private CheckBox[] checkEVertaile;
    private CheckBox[] checkIVertaile;
    private TextField[] textVertaile;
    
    
    /**
     * Alustaa listan
     */
    private void alusta() {
        // Lajittelutapa:
        cbKentat.clear(); 
        cbKentat.add("Nimi: A -> Ö", null);
        cbKentat.add("Nimi: Ö -> A", null);
        cbKentat.add("Vahvuus: Pienin -> Suurin", null);
        cbKentat.add("Vahvuus: Suurin -> Pienin", null);
        cbKentat.add("Ikä: Nuorin -> Vanhin", null);
        cbKentat.add("Ikä: Vanhin -> Nuorin", null);
        cbKentat.getSelectionModel().select(0); 
        // Vertaile-välilehden Lajittelutapa:
        cbKentat2.clear(); 
        cbKentat2.add("Nimi: A -> Ö", null);
        cbKentat2.add("Nimi: Ö -> A", null);
        cbKentat2.add("Vahvuus: Pienin -> Suurin", null);
        cbKentat2.add("Vahvuus: Suurin -> Pienin", null);
        cbKentat2.add("Ikä: Nuorin -> Vanhin", null);
        cbKentat2.add("Ikä: Vanhin -> Nuorin", null);
        cbKentat2.getSelectionModel().select(0); 
        // Tarkemman haun ehdot:
        checkE = new CheckBox[] {cb1, cb2, cb3, cb4, cb5, cb6, cb7};
        checkI = new CheckBox[] {cb8, cb9, cb10, cb11, cb12};
        text = new TextField[] {textIkaMin, textIkaMax};
        // Vertaile-välilehden tarkemman haun ehdot:
        checkEVertaile = new CheckBox[] {cb1Vertaile, cb2Vertaile, cb3Vertaile, cb4Vertaile, cb5Vertaile, cb6Vertaile, cb7Vertaile};
        checkIVertaile = new CheckBox[] {cb8Vertaile, cb9Vertaile, cb10Vertaile, cb11Vertaile, cb12Vertaile};
        textVertaile = new TextField[] {textIkaMinVertaile, textIkaMaxVertaile};
        
        
        lisaaKuuntelijatCBele(checkE, cb1, hakuehdot);
        lisaaKuuntelijatCBele(checkEVertaile, cb1Vertaile, hakuehdotVertaile);
        
        lisaaKuuntalijatCBika(checkI, hakuehdot);
        lisaaKuuntalijatCBika(checkIVertaile, hakuehdotVertaile);
        
        lisaaKuuntelijatVahvuus(text, hakuehdot);
        lisaaKuuntelijatVahvuus(textVertaile, hakuehdotVertaile);
        
        chooserPokemonit.clear();
        chooserPokemonit.addSelectionListener(e -> naytaPokemon());
        
        chooserPokemonit2.clear();
        chooserPokemonit2.addSelectionListener(e -> naytaPokemonitVertaile());
        
        chooserPokemonit3.clear();
        chooserPokemonit3.addSelectionListener(e -> naytaPokemonitVertaile());
    }
    
    
    /**
     * Lisää checkboxeihin kuuntelijat, joita kutsutaan, kun arvo muuttuu.
     * Elementti-checkboxeille.
     * @param taulukko Taulukko checkboxeja
     * @param valitseKaikki CheckBox, jolla valitaan kaikki elementit
     * @param ehdot Taulukko, jossa hakuehdot kokonaislukuina
     */
    private void lisaaKuuntelijatCBele(CheckBox[] taulukko, CheckBox valitseKaikki, int[] ehdot) {
        int i = 0;
        for (CheckBox cb : taulukko) {
            if (cb != null) {
                cb.setSelected(true);
                final int k = ++i;
                cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    kasitteleMuutosHakuEhtoonCBele(ehdot, valitseKaikki, taulukko, k, newValue);
                });
            }
        }
    }
    
    
    /**
     * Lisää checkboxeihin kuuntelijat, joita kutsutaan, kun arvo muuttuu.
     * Ika-checkboxeille.
     * @param taulukko Taulukko checkboxeja
     * @param ehdot Taulukko, jossa hakuehdot kokonaislukuina
     */
    private void lisaaKuuntalijatCBika(CheckBox[] taulukko, int[] ehdot) {
        int i = 0;
        for (CheckBox cb : taulukko) {
            if (cb != null) {
                cb.setSelected(true);
                final int k = ++i;
                cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    kasitteleMuutosHakuEhtoonCBika(ehdot, k, newValue); });
            }
        }
    }
    
    
    /**
     * Lisaa textfieldeihin kuuntelijat. Vahvuuden min ja max arvoille tarkemmassa haussa.
     * @param ehdot Taulukko, jossa hakuehdot kokonaislukuina
     * @param taulukko Taulukko, jossa textfieldit
     */
    private void lisaaKuuntelijatVahvuus(TextField[] taulukko, int[] ehdot) {
        int i = 0;
        for (TextField t : taulukko) {
            if (t != null) {
                final int k = ++i;
                t.setOnKeyReleased(e -> kasitteleMuutosHakuEhtoonVahvuus(ehdot, k, (TextField)(e.getSource())));
            }
        }
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon elementeille (muutetaan arvo 0 tai 1)
     * @param taul Taulukko, joka sisältää hakuehdot kokonaislukuina
     * @param valitseKaikki checkbox, jolla valitaan kaikki elementit
     * @param checkTaul taulukko, joka sisältää elementteihin liittyvät checkboxit
     * @param k checkboxin kentän id (1-7)
     * @param arvo uusi arvo
     */
    private void kasitteleMuutosHakuEhtoonCBele(int[] taul, CheckBox valitseKaikki, CheckBox[] checkTaul, int k, boolean arvo) {
        boolean kaikki = valitseKaikki.isSelected();
        
        int i = k-1;
        if (!arvo && kaikki) {
            valitseKaikki.setSelected(false);
            taul[i] = 0;
            return;
        }
        if (!arvo) {
            taul[i] = 0;
            return;
        }
        if (arvo && kaikki) {
            for (CheckBox cb : checkTaul) {
                cb.setSelected(true);
            }
            for (int j = 0; j < 8; j++) {
            taul[j] = 1;
            }
        }
        if (arvo) {
            taul[i] = 1;
        }
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon iälle (muutetaan arvo 0 tai 1)
     * @param taul Taulukko, joka sisältää hakuehdot kokonaislukuina
     * @param k checkboxin kentän id (1-5)
     * @param arvo uusi arvo
     */
    private void kasitteleMuutosHakuEhtoonCBika(int[] taul, int k, boolean arvo) {
        int i = k-1+7; // +7 koska i € {0, 1, 2, 3, 4};
       
        if (!arvo) {
            taul[i] = 0;
            return;
        }
        if (arvo) {
            taul[i] = 1;
            return;
        }
    }
    
    
    /**
     * Käsitellään muutos hakuehdot taulukkoon (muutetaan arvo vastaamaan vahvuutta)
     * @param taul Taulukko, joka sisältää hakuehdot kokonaislukuina
     * @param k textfieldin kentän id (1 tai 2 == min tai max)
     * @param vahvuus uusi arvo vahvuudelle
     */
    private void kasitteleMuutosHakuEhtoonVahvuus(int[] taul, int k, TextField vahvuus) {
        String v = vahvuus.getText();
        int uusivahvuus = Mjonot.erotaInt(v, -1);
        if (k == 1) {
            taul[12] = uusivahvuus;
        }
        if (k == 2) {
            taul[13] = uusivahvuus;
        }
    }
    

    /**
     * Pokemonin tietojen muokkaus
     */
    private void muokkaa() {
        Pokemon pokemon = chooserPokemonit.getSelectedObject();
        if ( pokemon == null ) return;
        if ( pokemonKohdalla == null ) return;
        try { 
            Pokemon muokattu;
            muokattu = PokemonRekisteriPokemonController.kysyPokemon(null, pokemon.clone(), rekisteri);
            if ( muokattu == null ) return;
            rekisteri.korvaaTaiLisaa(muokattu);
            hae(muokattu.getID());
            haeVertaile(0);
         
        } catch (CloneNotSupportedException e) 
        {
            //
        } 
    }

    
    /**
     * @param rekisteri rekisteri
     */
    protected void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaPokemon();
    }
    
    
    /**
     * Avataan dialogi pokemonin luonnille
     */
    private void uusiPokemon() {
        Pokemon uusi = new Pokemon();
        uusi.vastaa_pikachu();
        uusi = PokemonRekisteriPokemonController.kysyPokemon(null, uusi, rekisteri);
        if ( uusi == null ) return;
        uusi.rekisteroi();
        rekisteri.lisaa(uusi);
        hae(uusi.getID());
        haeVertaile(0);
    }
    
    
    /**
     * Poistetaan pokemon taulukosta
     */
    private void poistaPokemon() {
        Pokemon poistettava = pokemonKohdalla;
        if (poistettava == null) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko pokemon: " + 
        poistettava.getNimi(), "Kyllä", "Ei") )
            return;
        rekisteri.poista(poistettava);
        int index = chooserPokemonit.getSelectedIndex();
        hae(0);
        haeVertaile(0);
        chooserPokemonit.setSelectedIndex(index);
    }
    

    /**
     * Nayttaa valitun pokemonin tiedot ja seuraavat evoluutiot
     */
    private void naytaPokemon() {
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
    }
    
    
    /**
     * Nayttaa valitut pokemonit ja niiden tiedot
     */
    private void naytaPokemonitVertaile() {
        Pokemon pokemonVas = chooserPokemonit2.getSelectedObject();
        Pokemon pokemonOik = chooserPokemonit3.getSelectedObject();
        // pokemonKohdalla = chooserPokemonit.getSelectedObject();
        if (pokemonVas == null || pokemonOik == null) return;
        
        // Näytetään vasemman pokemonin tiedot:
        editOtsikkoVas.setText(pokemonVas.getNimi());
        editNimiVas.setText(pokemonVas.getNimi());
        editElementti1Vas.setText(rekisteri.annaElementti(pokemonVas, 1));
        editElementti2Vas.setText(rekisteri.annaElementti(pokemonVas, 2));
        editVahvuusVas.setText(""+pokemonVas.getVahvuus());
        editIkaVas.setText(rekisteri.annaIka(pokemonVas));
        editEvoluutioVas.setText(""+pokemonVas.getEvoluutio());
        areaLisaVas.setText(pokemonVas.getLisatiedot());
        
        // Näytetään oikean pokemonin tiedot:
        editOtsikkoOik.setText(pokemonOik.getNimi());
        editNimiOik.setText(pokemonOik.getNimi());
        editElementti1Oik.setText(rekisteri.annaElementti(pokemonOik, 1));
        editElementti2Oik.setText(rekisteri.annaElementti(pokemonOik, 2));
        editVahvuusOik.setText(""+pokemonOik.getVahvuus());
        editIkaOik.setText(rekisteri.annaIka(pokemonOik));
        editEvoluutioOik.setText(""+pokemonOik.getEvoluutio());
        areaLisaOik.setText(pokemonOik.getLisatiedot());
        
        // Näytetään Kaksintaistelun tiedot:
        double voittoVas = rekisteri.kaksintaistelu(pokemonVas, pokemonOik);
        // Vasen pokemon:
        editTaisteluElementitVas.setText(rekisteri.elementitJonona(pokemonVas));
        editTaisteluVahvuusVas.setText(""+pokemonVas.getVahvuus());
        editTaisteluEvoluutioVas.setText(""+pokemonVas.getEvoluutio());
        editTaisteluVoittoVas.setText(String.format("%2.1f %%", voittoVas*100) );
        
        // Oikea pokemon
        editTaisteluElementitOik.setText(rekisteri.elementitJonona(pokemonOik));
        editTaisteluVahvuusOik.setText(""+pokemonOik.getVahvuus());
        editTaisteluEvoluutioOik.setText(""+pokemonOik.getEvoluutio());
        editTaisteluVoittoOik.setText(String.format("%2.1f %%", (1 - voittoVas)*100) );
        
        double voittoOik = (1 - voittoVas);
        
        // Poistetaan kaikki stylet
        editTaisteluVoittoOik.getStyleClass().removeAll("vihrea");
        editTaisteluVoittoVas.getStyleClass().removeAll("vihrea");
        editTaisteluVoittoOik.getStyleClass().removeAll("virhe");
        editTaisteluVoittoVas.getStyleClass().removeAll("virhe");
        
        if (voittoOik < voittoVas) { 
            editTaisteluVoittoVas.getStyleClass().add("vihrea");
            editTaisteluVoittoOik.getStyleClass().add("virhe");
        }
        if (voittoVas < voittoOik) { 
            editTaisteluVoittoOik.getStyleClass().add("vihrea");
            editTaisteluVoittoVas.getStyleClass().add("virhe");
        }
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
        String ehto = hakuEhto.getText();
        if (ehto.indexOf('*') < 0 && 0 < ehto.length()) ehto = "*" + ehto + "*";
        else ehto = "";
        
        int k = cbKentat.getSelectionModel().getSelectedIndex() + 1;
        
        chooserPokemonit.clear();
        
        tarkistaVahvuudet();

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
     * Alustaa pokemonit listan ja valitsee uusimman luodun pokemonin
     * @param id pokemonin ID
     */
    protected void haeVertaile(int id) {
        String ehto = hakuEhto2.getText();
        if (ehto.indexOf('*') < 0 && 0 < ehto.length()) ehto = "*" + ehto + "*";
        else ehto = "";
        
        // Minkä mukaan lajiteltu:
        int k = cbKentat2.getSelectionModel().getSelectedIndex() + 1;
        
        // Tyhjennetään listat:
        chooserPokemonit2.clear();
        chooserPokemonit3.clear();
        
        // Tarkistetaan ovatko vahvuudet järkeviä:
        tarkistaVahvuudetVertaile();

        int index = 0;
        boolean takaperin;
        takaperin = (k % 2 == 0);
        List<Pokemon> sopivat;
        sopivat =  (List<Pokemon>) rekisteri.etsiHakuehdolla(ehto, k, takaperin, hakuehdotVertaile);
        for (int i = 0; i < sopivat.size(); i++) {
            Pokemon p = sopivat.get(i);
            if (p.getID() == id) index = i;
            chooserPokemonit2.add(p.getNimi(), p);
            chooserPokemonit3.add(p.getNimi(), p);
        }
        chooserPokemonit2.setSelectedIndex(index);
        chooserPokemonit3.setSelectedIndex(index);
    }
    
    
    /**
     * Tarkistaa vahvuudet ja asettaa 0 ja 10000
     * minimiksi ja maksimiksi jos ns. laittomat
     */
    private void tarkistaVahvuudet() {
        int minVahv = hakuehdot[12];
        int maxVahv = hakuehdot[13];
        if (maxVahv < minVahv || minVahv < 0 || maxVahv < 0) {
            textIkaMin.setText("0");
            hakuehdot[12] = 0;
            textIkaMax.setText("10000");
            hakuehdot[13] = 10000;
        }
    }
    
    
    /**
     * Tarkistaa vahvuudet ja asettaa 0 ja 10000
     * minimiksi ja maksimiksi jos ns. laittomat.
     * Vertaile-välilehden hakuehdot
     */
    private void tarkistaVahvuudetVertaile() {
        int minVahv = hakuehdotVertaile[12];
        int maxVahv = hakuehdotVertaile[13];
        if (maxVahv < minVahv || minVahv < 0 || maxVahv < 0) {
            textIkaMinVertaile.setText("0");
            hakuehdotVertaile[12] = 0;
            textIkaMaxVertaile.setText("10000");
            hakuehdotVertaile[13] = 10000;
        }
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
    protected boolean voikoSulkea() {
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
            haeVertaile(0);
            return null;
        } catch (SailoException ex) {
            hae(0);
            haeVertaile(0);
            Dialogs.showMessageDialog("Tiedostosta lukemisessa ongelmia: " + ex.getMessage());
            return ex.getMessage();
        }
    }


    // Tulostetaan näkymä
    private void tulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
}