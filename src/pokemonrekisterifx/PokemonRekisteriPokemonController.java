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
    @FXML private TextField editElementti1;
    @FXML private TextField editElementti2;
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
    
    @FXML private static Label labelVirhe;
    
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


    // FXML
    // ===================================================================
    // Muu koodi
    
    private Pokemon pokemonKohdalla;
    private Rekisteri rekisteri;
    private TextField[] edits;
    private CheckBox[] elementit;
    

    /**
     * Tekee tarvittavat muut alustukset. Mm laittaa edit-kentistä tulevan
     * tapahtuman menemään kasitteleMuutosJaseneen-metodiin ja vie sille
     * kentännumeron parametrina.
     */
    protected void alusta() {
        edits = new TextField[]{editNimi, editElementti1, editElementti2, editVahvuus,
                editIka, editEvoluutio};
        elementit = new CheckBox[]{cbElementti1, cbElementti2, cbElementti3, cbElementti4, 
                cbElementti5, cbElementti6};
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosPokemoniin(k, (TextField)(e.getSource())));
        }
        
        i = 0;
        for (CheckBox ele : elementit) {
            final int k = ++i;
            ele.selectedProperty().addListener((observable, oldValue, newValue) -> {
                kasitteleMuutosPokemoniinCB(k, newValue);
            });}
    }
    
    /**
     * @param k elementin kentän id
     * @param arvo uusi arvo
     */
    public void kasitteleMuutosPokemoniinCB(int k, boolean arvo) {
        if (pokemonKohdalla == null) return;
        if (arvo) {
            //pokemonKohdalla.setElementtiID1(k);
            // jne.
        }
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
            case 4 : virhe = pokemonKohdalla.setVahvuus(s); break;
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
     * Näytetään virhe dialogissa
     * @param virhe virheteksti
     */
    public static void naytaVirhe(String virhe) {
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
    
    
    /**
     * Luodaan pokemonin muokkaus dialogi ja palautetaan sama tietue muutettuna tai null
     * TODO: korjattava toimimaan
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param klooni mitä dataan näytetään oletuksena
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Pokemon kysyPokemon(Stage modalityStage, Pokemon klooni) {
        return ModalController.<Pokemon, PokemonRekisteriPokemonController>showModal(
                    PokemonRekisteriPokemonController.class.getResource("PokemonMuokkaaTietoja.fxml"),
                    "Rekisteri",
                    modalityStage, klooni, null 
                );
    }


    @Override
    public void setDefault(Pokemon oletus) {
        pokemonKohdalla = oletus;
        naytaPokemon(pokemonKohdalla);
    }
}