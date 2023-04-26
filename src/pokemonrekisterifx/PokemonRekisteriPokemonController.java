package pokemonrekisterifx;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
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
    
    @FXML private Label labelVirhe;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML private void handleOK() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        ModalController.closeStage(labelVirhe);
    }


    // FXML
    // ===================================================================
    // Muu koodi
    private Pokemon pokemonKohdalla;
    private Rekisteri rekisteri;
    
    /**
     * Tarvittavat alustukset
     */
    public void alusta() {
        //
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
     * Näytetään pokemonin tiedot käyttöliittymän tekstikentissä
     * @param pokemon Pokemon, jonka tiedot näytetään
     */
    public void naytaPokemon(Pokemon pokemon) {
        if (pokemon == null) return;
        editNimi.setText(pokemon.getNimi());
        editElementti1.setText(rekisteri.annaElementti(pokemon, 1));
        editElementti2.setText(rekisteri.annaElementti(pokemon, 2));
        editVahvuus.setText(""+pokemon.getVahvuus());
        editIka.setText(rekisteri.annaIka(pokemon));
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
}