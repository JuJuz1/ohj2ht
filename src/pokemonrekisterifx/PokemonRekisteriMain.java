package pokemonrekisterifx;

import javafx.application.Application;
import javafx.stage.Stage;
import rekisteri.Rekisteri;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Juuso Piippo & Elias Lehtinen
 * @version 16.2.2023
 *
 */
public class PokemonRekisteriMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("PokemonRekisteriGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final PokemonRekisteriGUIController rekisteriCtrl = (PokemonRekisteriGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("pokemonrekisteri.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("PokemonRekisteri");
            
            Rekisteri rekisteri = new Rekisteri();
            rekisteriCtrl.setRekisteri(rekisteri);
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args ei kayt.
     */
    public static void main(String[] args) {
        launch(args);
    }
}