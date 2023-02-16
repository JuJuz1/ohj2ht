package pokemonrekisteri;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;

//import javafx.event.ActionEvent; sama homma ku handleTallenna

/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 16.2.2023
 *
 */
public class PokemonRekisteriGUIController {
    
    @FXML private void handleTallenna(/*ActionEvent event (ehkä tarvitaan jatkossa)*/) {
        tallenna();
        }
    
    @FXML private void handleTulosta() {
        tulosta();
        }
    
    @FXML private void handleLopeta() {
        lopeta();
        }
    
    @FXML private void handleLisaaPokemon() {
        ModalController.showModal(PokemonRekisteriGUIController.class.getResource
        ("PokemonRekisteriLisaaUusi.fxml"), "Pokemonin lisäys", null, "");
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
        ModalController.showModal(PokemonRekisteriGUIController.class.getResource
        ("PokemonRekisteriMuokkaaTietoja.fxml"), "Pokemonin muokkaus", null, "");
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
    
    // Tallennetaan tiedot
    private void tallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
    }
    
    // Tulostetaan näkymä
    private void tulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    // Lopetetaan kyseinen ohjelma
    private void lopeta() {
        Dialogs.showMessageDialog("Ei osata vielä lopettaa");
    }
    
    // Haetaan hakukentän perusteella
    private void hae() {
        Dialogs.showMessageDialog("Ei osata vielä hakea");
    }
    
    
}