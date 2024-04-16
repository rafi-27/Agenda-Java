package iesthiar;

import java.io.IOException;
import java.net.URL;

import iesthiar.persona.EditorPersonaControlador;
import iesthiar.persona.VistaPersonaController;
import iesthiar.persona.persona;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibretaDirecciones extends Application {
    /*
     * Otros atributos
     */

    private ObservableList<persona> datosPersona = FXCollections.observableArrayList();

    /*
     * Constructor inicializando con datos de ejemplo
     */
    public LibretaDirecciones() {
        datosPersona.add(new persona("Aitor", "Tilla"));
        datosPersona.add(new persona("Paco", "Jones"));
        datosPersona.add(new persona("Victor", "Tazo"));
        datosPersona.add(new persona("Aquiles", "Castro"));
        datosPersona.add(new persona("Elton", "Tito"));
        datosPersona.add(new persona("Aitor", "Menta"));
    }

    public ObservableList<persona> getDatosPersona() {
        return datosPersona;
    }

    private Stage escenarioPrincipal;
    private BorderPane contenedorPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) {
        // Necesario para cambiar las escenas
        this.escenarioPrincipal = escenarioPrincipal;
        // Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");
        // Establezco el icono de aplicación
        this.escenarioPrincipal.getIcons().add(
                new Image(LibretaDirecciones.class.getResourceAsStream("img/libretaDirecciones.png")));
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
            // Carga la vista de persona.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(LibretaDirecciones.class.getResource("persona/vistaPersona.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Añade la vista al centro del contenedor principal
            contenedorPrincipal.setCenter(personOverview);
            VistaPersonaController controlador = loader.getController();
            controlador.setLibretaDirecciones(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vista editarPersona
    public boolean muestraEditarPersona(persona persona) {
        // Cargo la vista persona a partir de VistaPersona.fxml
        Pane editarPersona = null;
        FXMLLoader loader = new FXMLLoader();

        try {
            URL location = LibretaDirecciones.class.getResource("persona/editarPersona.fxml");
            loader.setLocation(location);
            editarPersona = (Pane) loader.load();
        } catch (IOException ex) {
            // ex.printStackTrace();
            System.err.println("---------------------------------------");
            return false;
        }

        // Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.setTitle("Editar Persona");
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        escenarioEdicion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(editarPersona);
        escenarioEdicion.setScene(escena);

        // Asigno el escenario de edición y la persona seleccionada al controlador
        EditorPersonaControlador controlador = loader.getController();
        controlador.setEscenarioEdicion(escenarioEdicion);
        controlador.setPersona(persona);

        // Muestro el diálogo hasta que el usuario lo cierre
        escenarioEdicion.showAndWait();

        // devuelvo el botón pulsado
        return controlador.isGuardarClicked();
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