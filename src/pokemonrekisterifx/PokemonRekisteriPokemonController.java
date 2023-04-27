package pokemonrekisterifx;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.LabelView;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;
import rekisteri.SailoException;


/**
 * Pokemonin tietojen näyttämistä ja muokkaamista ohjaava kontrolleri
 * @author Juuso Piippo & Elias Lehtinen
 * @version 26.4.2023
 *
 */
public class PokemonRekisteriPokemonController implements ModalControllerInterface<Pokemon>,Initializable{

    @FXML private TextField editNimi;
    //@FXML private TextField editElementti1;
    //@FXML private TextField editElementti2;
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
        /*if ( pokemonKohdalla != null && tarkistaNimi(pokemonKohdalla.getNimi()) ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        */
        ModalController.closeStage(labelVirhe);
    }
    
    
    @FXML private void handleCancel() {
        ModalController.closeStage(labelVirhe);
    }

    // Tarvitaan ehkä
    @FXML private void handleDefaultOK() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleDefaultCancel() {
        ModalController.closeStage(labelVirhe);
    }

    // FXML
    // ===================================================================
    // Muu koodi
    
    private Pokemon pokemonKohdalla;
    private Rekisteri rekisteri;
    private TextField[] edits;
    private CheckBox[] elementit;
    

    /**
     * Tekee tarvittavat muut alustukset. Mm laittaa edit-kentistä tulevan
     * tapahtuman menemään kasitteleMuutosPokemoniin-metodiin ja vie sille
     * kentännumeron parametrina. Laitetaan CheckBoxit oikeiksi arvoiksi.
     * TODO: CHECKBOXIT TOIMIMAAN
     */
    protected void alusta() {
        
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
        
        
        // TODO: CHECKBOXIT TOIMIMAAN
        // TÄLLÄ HETKELLÄ KAATUU
        
        /*
        
        int eka = pokemonKohdalla.getElementtiID(1);
        TÄHÄN KAATUU KOSKA TARVITTAISIIN REKISTERIÄ
        // int toka = pokemonKohdalla.getElementtiID(2);
            if (eka == 1 && cbElementti1 != null) cbElementti1.setSelected(true);
            if (eka == 2 && cbElementti2 != null) cbElementti2.setSelected(true);
            if (eka == 3 && cbElementti3 != null) cbElementti3.setSelected(true);
            if (eka == 4 && cbElementti4 != null) cbElementti4.setSelected(true);
            if (eka == 5 && cbElementti5 != null) cbElementti5.setSelected(true);
            if (eka == 6 && cbElementti6 != null) cbElementti6.setSelected(true);
            */
    }
    
    
    /**
     * Käsitellään elementin ID pokemonille
     * @param k elementin kentän id
     * @param arvo uusi arvo
     */
    public void kasitteleMuutosPokemoniinCB(int k, boolean arvo) {
        if (pokemonKohdalla == null) return;
        int ele_lkm = pokemonKohdalla.getElementtienLkm();
        if (ele_lkm == 0 || ele_lkm == 1) {
            pokemonKohdalla.setElementtiID(ele_lkm + 1, k);
        }
        // Jos painetaan pois
        else if (!arvo) pokemonKohdalla.setElementtiID(ele_lkm, 0);
    }
    
    
    /**
     * Käsitellään muokkaus
     * @param k kentän id
     * @param edit se textfield jota muokataan
     */
    public void kasitteleMuutosPokemoniin(int k, TextField edit) {
        if (pokemonKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = pokemonKohdalla.setNimi(s); break;
            //case 2 : virhe = pokemonKohdalla.setElementti1(s); break;
            //case 3 : virhe = pokemonKohdalla.setElementti2(s); break;
            case 2 : virhe = pokemonKohdalla.setVahvuus(s); break;
            case 3 : virhe = pokemonKohdalla.setIka(s);
            if (virhe == null) {
                int ika = Mjonot.erotaInt(s, -1); 
                asetaIkaText(ika, edit); // Toimii huonosti
                // TODO: Pitää parannella, jos edes voi
            }
            break;
            case 4 : virhe = pokemonKohdalla.setEvoluutio(s); break;
            //case 5 : virhe = pokemonKohdalla.setLisaTiedot(s); break;
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
     * Tehdään ikäaluetarkistus ja asetetaan ikä
     * @param ika ika kokonaislukuna
     * @param ikakentta ikäkenttä
     */
    public void asetaIkaText(int ika, TextField ikakentta) {
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
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.getText();
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setVisible(true);
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    @Override
    public Pokemon getResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    /**
     * Asetetaan rekisteri-attribuutti elementtien ja ikäryhmien käsittelyä varten
     * @param r Rekisteri
     */
    public void setRekisteri(Rekisteri r) {
        rekisteri = r;
    }
    
    
    /**
     * Näytetään pokemonin tiedot MUOKKAUKSEN tekstikentissä
     * @param pokemon Pokemon, jonka tiedot näytetään
     */
    public void naytaPokemon(Pokemon pokemon) {
        if (pokemon == null) return;
        editNimi.setText(pokemon.getNimi());
        //editElementti1.setText(rekisteri.annaElementti(pokemon, 1));
        //editElementti2.setText(rekisteri.annaElementti(pokemon, 2));
        editVahvuus.setText(""+pokemon.getVahvuus());
        
        //editIka.setText(rekisteri.annaIka(pokemon)); 
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        // :TODO EI TOIMI VIELÄ, EI VOI KUTSUA REKISTERIÄ ENNEN SETKERHOA
        // KYSYPOKEMON SUORITUSJÄRJESTYS MEILLÄ:
        /*
            controller-luokan mahdollinen muodostaja
            initialize - alustaa .fxml-tiedostossa määritellyt attribuutit
            setDefault - alustaa pokemon-attribuutin TS. TÄSSÄ KUTSUTAAN JO NAYTAPOKEMON !!!
        !!! setRekisteri - alustaa rekisteri-olion !!!
            handleShown - kutsutaan kun dialogi on tullut näyttöön
        */
        editIka.setText("ika"); // VÄLIAIKAINEN
        editEvoluutio.setText(""+pokemon.getEvoluutio());
        areaLisa.setText(pokemon.getLisatiedot());
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    
    @Override
    public void setDefault(Pokemon oletus) {
        pokemonKohdalla = oletus;
        naytaPokemon(pokemonKohdalla);
    }
    
    
    /**
     * Luodaan pokemonin muokkaus dialogi ja palautetaan sama tietue muutettuna tai null
     * TODO: korjattava toimimaan
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param klooni mitä dataan näytetään oletuksena
     * @param rek guicontroller rekisteri
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Pokemon kysyPokemon(Stage modalityStage, Pokemon klooni, Rekisteri rek) {
        return ModalController.<Pokemon, PokemonRekisteriPokemonController>showModal(
                    PokemonRekisteriPokemonController.class.getResource
                    ("PokemonRekisteriMuokkaaTietoja.fxml"),
                    "Muokkaaminen",
                    modalityStage, klooni, ctrl -> {ctrl.setRekisteri(rek);});
                                                   // Tässä vasta alustetaan
    }
}