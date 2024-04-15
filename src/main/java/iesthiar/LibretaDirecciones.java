package iesthiar;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LibretaDirecciones extends Application {

    private Stage escenarioPrincipal;
    private BorderPane contenedorPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) {
        // Necesario para cambiar las escenas
        this.escenarioPrincipal = escenarioPrincipal;
        // Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");
        // inicialización del contenedor principal
        initContenedorPrincipal();
        // muestro la vista persona
        mostrarVistaPersona();
    }

    /**
     * Initializes the root layout.
     */
    public void initContenedorPrincipal() {
        try {
            // Carga el contenedor principal desde el fxml.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(LibretaDirecciones.class.getResource("vistaPrincipal.fxml"));
            contenedorPrincipal = (BorderPane) loader.load();

            // Muestra la escena del contenedor principal
            Scene scene = new Scene(contenedorPrincipal);
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*** Carga y muestra la escena que */
    public void mostrarVistaPersona() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(LibretaDirecciones.class.getResource("persona/vistaPersona.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Añade la vista al centro del contenedor principal
            contenedorPrincipal.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el escenario principal.
     * 
     * @return
     */
    public Stage getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
