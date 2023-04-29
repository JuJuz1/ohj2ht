package pokemonrekisterifx;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;


/**
 * Pokemonin lisäämistä ja tietojen muokkaamista ohjaava kontrolleri
 * 
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 29.4.2023
 */
public class PokemonRekisteriPokemonController implements ModalControllerInterface<Pokemon>,Initializable{

    @FXML private TextField editNimi;
    @FXML private TextField editVahvuus;
    @FXML private TextField editIka;
    @FXML private TextField editEvoluutio;
    @FXML private TextArea  areaLisa;
    
    @FXML private CheckBox cbElementti1;
    @FXML private CheckBox cbElementti2;
    @FXML private CheckBox cbElementti3;
    @FXML private CheckBox cbElementti4;
    @FXML private CheckBox cbElementti5;
    @FXML private CheckBox cbElementti6;
    
    @FXML private Label labelVirhe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    
    @FXML private void handleOK() {
        hyvaksyMuutokset();
    }
    
    
    @FXML private void handleCancel() {
        pokemonKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    // Tarvitaan ehkä
    @FXML private void handleDefaultOK() {
        handleOK();
    }
    
    @FXML private void handleDefaultCancel() {
        handleCancel();
    }

    // FXML
    // ===================================================================
    // Muu koodi
    
    private Pokemon pokemonKohdalla;
    private Rekisteri rekisteri;
    private TextField[] edits;
    private CheckBox[] elementit;
    private int elementtiLkm;
    
    
    /**
     * Kutsutaan, kun muokkaus/lisays -ikkunassa painetaan tallenna.
     * Tehdyt muutokset tallennetaan pokemonKohdalla-olioon.
     * Ei vielä tallenneta pokemonia tiedostoon.
     */
    private void hyvaksyMuutokset() {
        int m = 0;
        for (int i = 0; i <= 5; i++) {
            if (elementit[i].isSelected()) m++;
        }
        if (2 <= m) {
            int eka = pokemonKohdalla.getElementtiID(1);
            int toka = pokemonKohdalla.getElementtiID(2);
            for (int n = 1; n <= 6; n++) {
                if (eka == n || toka == n) {
                    elementit[n-1].setSelected(true);
                }
                else elementit[n-1].setSelected(false);
            }
        }
        String ika = editIka.getText();
        if (ika.matches("[1-9][0-9]*")) {
            int i = Mjonot.erotaInt(ika, -1);
            if (Pokemon.tarkistaVahvuusTaiIka(i)) {
                asetaIkaText(i, editIka);
            }
        }
        
        if (pokemonKohdalla != null)

        ModalController.closeStage(labelVirhe);
    }
    

    /**
     * Tekee tarvittavat muut alustukset. Mm laittaa edit-kentistä tulevan
     * tapahtuman menemään kasitteleMuutosPokemoniin-metodiin ja vie sille
     * kentännumeron parametrina.
     */
    private void alusta() {
        
        // editElementti1, editElementti2
        edits = new TextField[]{editNimi, editVahvuus, editIka, editEvoluutio};
        elementit = new CheckBox[]{cbElementti1, cbElementti2, cbElementti3, cbElementti4, 
                cbElementti5, cbElementti6};
        int i = 0;
        for (TextField edit : edits) {
            if (edit != null) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosPokemoniin(k, (TextField)(e.getSource())));
            }
        }
        
        areaLisa.setOnKeyReleased(e -> kasitteleMuutosPokemoniinArea((TextArea)(e.getSource())));
        
        i = 0;
        for (CheckBox ele : elementit) {
            if (ele != null) {
            final int k = ++i;
            ele.selectedProperty().addListener((observable, oldValue, newValue) -> {
                kasitteleMuutosPokemoniinCB(k, newValue);
            });
            }
        }
        
        labelVirhe.setVisible(false);
    }
    
    
    /**
     * Käsitellään elementin ID pokemonille
     * @param k elementin kentän id
     * @param arvo uusi arvo
     */
    private void kasitteleMuutosPokemoniinCB(int k, boolean arvo) {
        if (elementtiLkm == -1) return;
        if (pokemonKohdalla == null) return;

        if (arvo && elementtiLkm == 0) {
            pokemonKohdalla.setElementtiID(1, k);
            elementtiLkm = 1;
            return;
        }
        if (arvo && elementtiLkm == 1) {
            pokemonKohdalla.setElementtiID(2, k);
            elementtiLkm = 2;
            return;
        }
        if (arvo && elementtiLkm == 2) {
            elementtiLkm = -1;
            elementit[k-1].setSelected(false);
            naytaVirhe("Voit valita korkeintaan 2 elementtiä!");
            elementtiLkm = 2;
            return;
        }
        if (!arvo && elementtiLkm == 2) {
            if (pokemonKohdalla.getElementtiID(1) == k) {
                pokemonKohdalla.setElementtiID(1, pokemonKohdalla.getElementtiID(2));
                pokemonKohdalla.setElementtiID(2, 0);
                elementtiLkm = 1;
            } else {
                pokemonKohdalla.setElementtiID(2, 0);
                elementtiLkm = 1;
            }
            return;
        }
        if (!arvo && elementtiLkm == 1) {
            elementtiLkm = -1;
            naytaVirhe("Pokemonilla täytyy olla vähintää 1 elementti");
            elementit[k-1].setSelected(true);
            elementtiLkm = 1;
        }
    }
    
    
    /**
     * Käsitellään muokkaus
     * @param k kentän id
     * @param edit se textfield jota muokataan
     */
    private void kasitteleMuutosPokemoniin(int k, TextField edit) {
        if (pokemonKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = pokemonKohdalla.setNimi(s); break;
            case 2 : virhe = pokemonKohdalla.setVahvuus(s); break;
            case 3 : virhe = pokemonKohdalla.setIka(s); break;
            case 4 : virhe = pokemonKohdalla.setEvoluutio(s); break;
            case 5 : virhe = pokemonKohdalla.setLisatiedot(s); break;
        default:
        }
        if (virhe == null) {
            Dialogs.setToolTipText(edit,"");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit,virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Sijoitetaan textareaan lisätiedot
     * @param area area
     */
    private void kasitteleMuutosPokemoniinArea(TextArea area) {
        if (pokemonKohdalla == null) return;
        String s = area.getText();
        String virhe = pokemonKohdalla.setLisatiedot(s);
        
        // Ei ehkä tarpeellista nämä
        if (virhe == null) {
            Dialogs.setToolTipText(area,"");
            area.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(area,virhe);
            area.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Tehdään ikäaluetarkistus ja asetetaan ikä
     * @param ika ika kokonaislukuna
     * @param ikakentta ikäkenttä
     */
    private void asetaIkaText(int ika, TextField ikakentta) {
        for (int i = 10; i <= 40; i+=10) {
            if (0 < ika && ika <= i) {
                ikakentta.setText(String.format("%s-%s", i-10, i));
                return;
            }
        }
        ikakentta.setText("40+");
    }


    /**
     * Näytetään virhe dialogissa
     * @param virhe virheteksti
     */
    private void naytaVirhe(String virhe) {
        labelVirhe.setVisible(true);
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    @Override
    public Pokemon getResult() {
        return pokemonKohdalla;
    }
    
    
    /**
     * Asetetaan rekisteri-attribuutti elementtien ja ikäryhmien käsittelyä varten.
     * Samalla valitaan halutut checkboxit.
     * @param r Rekisteri
     */
    private void alusta2(Rekisteri r) {
        rekisteri = r;
        elementtiLkm = -1;
        int eka = pokemonKohdalla.getElementtiID(1);
        int toka = pokemonKohdalla.getElementtiID(2);
        
        for (int n = 1; n <= 6; n++) {
            if (eka == n || toka == n) {
                elementit[n-1].setSelected(true);
            }
        }
        elementtiLkm = pokemonKohdalla.getElementtienLkm();
        naytaPokemon(pokemonKohdalla);
    }
    
    
    /**
     * Näytetään pokemonin tiedot MUOKKAUKSEN tekstikentissä
     * @param pokemon Pokemon, jonka tiedot näytetään
     */
    private void naytaPokemon(Pokemon pokemon) {
        if (pokemon == null) return;
        editNimi.setText(pokemon.getNimi());
        editVahvuus.setText(""+pokemon.getVahvuus());
        
        editIka.setText(rekisteri.annaIka(pokemon)); 

        editEvoluutio.setText(""+pokemon.getEvoluutio());
        areaLisa.setText(pokemon.getLisatiedot());
    }

    
    @Override
    public void handleShown() {
        // Ei tehdä mitään
    }
    
    
    @Override
    public void setDefault(Pokemon oletus) {
        pokemonKohdalla = oletus;
    }
    
    
    /**
     * Luodaan pokemonin muokkaus dialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param klooni mitä dataan näytetään oletuksena
     * @param rek guicontroller rekisteri
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    protected static Pokemon kysyPokemon(Stage modalityStage, Pokemon klooni, Rekisteri rek) {
        return ModalController.<Pokemon, PokemonRekisteriPokemonController>showModal(
                 PokemonRekisteriPokemonController.class.getResource
                 ("PokemonRekisteriMuokkaaTietoja.fxml"),
                 "Muokkaaminen",
                 modalityStage, klooni, ctrl -> {ctrl.alusta2(rek);});
    }
}