package pokemonrekisterifx;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;
import rekisteri.SailoException;

//import javafx.event.ActionEvent; sama homma ku handleTallenna

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 15.3.2023
 *
 */
public class PokemonRekisteriGUIController implements Initializable{

    @FXML private TextArea areaLisa;
    @FXML private ListChooser<Pokemon> chooserPokemonit;
    
    
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
        Dialogs.showMessageDialog("Ei osata vielä poistaa pokemoneja");
    }


    @FXML private void handleApua() {
        Dialogs.showMessageDialog("Ei osata vielä antaa apua");
    }


    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Ei osata vielä antaa tietoja");
    }


    @FXML private void handleMuokkaa() {
        ModalController.showModal(PokemonRekisteriGUIController.class
            .getResource("PokemonRekisteriMuokkaaTietoja.fxml"),"Pokemonin muokkaus", null, "");
    }


    @FXML private void handleHae() {
        hae();
    }


    @FXML private void handlePeruuta() {
        Dialogs.showMessageDialog("Ei osata vielä peruuttaa");
    }

    // FXML
    // ===================================================================
    // Muu koodi

    private Rekisteri rekisteri;

    /**
     * @param rekisteri rekisteri
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaPokemon();
    }
    
    
    /**
     * Alustaa Elementit-olion muutamalla elementillä kokeilua varten
     * TODO: poista kun ei enää tarvita
     */
    public void alustaElementit() {
        rekisteri.getElementit().alustaElementeilla();
    }
    
    
    /**
     * Alustaa Iat-olion muutamalla ikäluokalla kokeilua varten
     * TODO: poista kun ei enää tarvita
     */
    public void alustaIat() {
        rekisteri.getIat().alustaIkaLuokilla();
    }


    /**
     * Nayttaa valitun pokemonin textareassa lisatiedot
     */
    protected void naytaPokemon() {
        Pokemon pokemonKohdalla = chooserPokemonit.getSelectedObject();

        if (pokemonKohdalla == null)
            return;

        areaLisa.setText("");
        try (PrintStream os = TextAreaOutputStream
                .getTextPrintStream(areaLisa)) {
            pokemonKohdalla.tulosta(os);
            rekisteri.tulostaElementit(pokemonKohdalla, os);
            rekisteri.tulostaIka(pokemonKohdalla, os);
        }
    }


    /**
     * Luo uuden pokemonin ja alustaa pikachulla
     */
    protected void uusiPokemon() {
        Pokemon uusi = new Pokemon();
        uusi.rekisteroi();
        uusi.vastaa_pikachu();
        rekisteri.lisaa(uusi);
        hae(uusi.getID());
    }


    /**
     * Alustaa pokemonit listan ja valitsee uusimman luodun pokemonin
     * @param id pokemonin ID
     */
    protected void hae(int id) {
        chooserPokemonit.clear();

        int index = 0;
        for (int i = 0; i < rekisteri.getLkm(); i++) {
            Pokemon pokemon = rekisteri.getPokemon(i);
            if (pokemon.getID() == id)
                index = i;
            chooserPokemonit.add(pokemon.getNimi(), pokemon);
        }
        chooserPokemonit.setSelectedIndex(index);
    }


    /**
     * Alustaa listan
     */
    protected void alusta() {
        chooserPokemonit.clear();
        chooserPokemonit.addSelectionListener(e -> naytaPokemon());
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


    // Tulostetaan näkymä
    private void tulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }


    // Haetaan hakukentän perusteella
    private void hae() {
        Dialogs.showMessageDialog("Ei osata vielä hakea");
    }

}