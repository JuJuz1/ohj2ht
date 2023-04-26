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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import rekisteri.Pokemon;
import rekisteri.Rekisteri;
import rekisteri.SailoException;

//import javafx.event.ActionEvent; sama homma ku handleTallenna

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 26.4.2023
 *
 */
public class PokemonRekisteriGUIController implements Initializable{

    @FXML private ListChooser<Pokemon> chooserPokemonit;
    
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
     * Nayttaa valitun pokemonin textareassa lisatiedot
     */
    protected void naytaPokemon() {
        Pokemon pokemon = chooserPokemonit.getSelectedObject();
        
        if (pokemon == null) return;
        editNimi.setText(pokemon.getNimi());
        editElementti1.setText(rekisteri.annaElementti(pokemon, 1));
        editElementti2.setText(rekisteri.annaElementti(pokemon, 2));
        editVahvuus.setText(""+pokemon.getVahvuus());
        editIka.setText(rekisteri.annaIka(pokemon));
        editEvoluutio.setText(""+pokemon.getEvoluutio());
        areaLisa.setText(pokemon.getLisatiedot());
        
        // TODO: seuraavat evoluutiot
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


    // Haetaan hakukentän perusteella
    private void hae() {
        Dialogs.showMessageDialog("Ei osata vielä hakea");
    }

}