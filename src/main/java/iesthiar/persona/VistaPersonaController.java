package iesthiar.persona;

import java.net.URL;
import java.util.ResourceBundle;

import iesthiar.LibretaDirecciones;
import iesthiar.persona.persona;
import iesthiar.util.UtilidadDeFechas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VistaPersonaController implements Initializable {
  @FXML
  private TableView<persona> personaTabla;
  @FXML
  private TableColumn<persona, String> nombreColumna;
  @FXML
  private TableColumn<persona, String> apellidosColumna;

  @FXML
  private Label nombreEtiqueta;
  @FXML
  private Label apellidosEtiqueta;
  @FXML
  private Label direccionEtiqueta;
  @FXML
  private Label codigoPostalEtiqueta;
  @FXML
  private Label ciudadEtiqueta;
  @FXML
  private Label fechaNacimientoEtiqueta;

  // Reference to the main application.
  private LibretaDirecciones libretaDirecciones;

  /**
   * El constructor es llamado antes que el método initialize()
   */
  public VistaPersonaController() {
  }

  /**
   * Inicializa la clase controladora. Este método se llama automáticamente
   * después de la carga del fichero fxml.
   * Si la clase implementa Initializable obliga a crear el método
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
    apellidosColumna.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
    // Borramos los textos de los datos de una persona
    mostrarDetallesPersona(null);

    // Escuchamos cambios en la selección de la tabla para mostrar los detalles
    personaTabla.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> mostrarDetallesPersona(newValue));
  }

  /**
   * Se llama desde la aplicación principal, para tener una referencia a si
   * misma
   * 
   * @param libretaDirecciones
   */
  public void setLibretaDirecciones(LibretaDirecciones libretaD) {
    this.libretaDirecciones = libretaD;

    // Add observable list data to the table
    personaTabla.setItems(libretaD.getDatosPersona());
  }

  /**
   * Rellena todos los textos para mostrar los detalles de una persona
   * Si la persona es null, los textos se borran
   * 
   * @param persona la persona o null
   */
  private void mostrarDetallesPersona(persona persona) {
    if (persona != null) {
      // Fill the labels with info from the person object.
      nombreEtiqueta.setText(persona.getNombre());
      apellidosEtiqueta.setText(persona.getApellidos());
      direccionEtiqueta.setText(persona.getDireccion());

      codigoPostalEtiqueta.setText(Integer.toString(persona.getCodigoPostal()));
      ciudadEtiqueta.setText(persona.getCiudad());

      // TODO: Tenemos que convertir la fecha de necimiento a texto
      // fechaNacimientoEtiqueta.setText(...);
      fechaNacimientoEtiqueta.setText(UtilidadDeFechas.formato(persona.getFechaNacimiento()));
    } else {
      // Person is null, remove all the text.
      nombreEtiqueta.setText("");
      apellidosEtiqueta.setText("");
      direccionEtiqueta.setText("");
      codigoPostalEtiqueta.setText("");
      ciudadEtiqueta.setText("");
      fechaNacimientoEtiqueta.setText("");
    }
  }

  /**
   * Se llama cuando pulsamos el boton Borrar
   */
  @FXML
  private void borrarPersona() {
    int indiceSeleccionado = personaTabla.getSelectionModel().getSelectedIndex();
    if (indiceSeleccionado >= 0) {
      personaTabla.getItems().remove(indiceSeleccionado);
    } else {
      // Muestro alerta
      Alert alerta = new Alert(AlertType.WARNING);
      alerta.setTitle("Atención");
      alerta.setHeaderText("Persona no seleccionada");
      alerta.setContentText("Por favor, selecciona una persona de la tabla");
      alerta.showAndWait();
    }
  }

  // Muestro el diálogo editar persona cuando el usuario hace clic en el botón de
  // Crear
  @FXML
  private void crearPersona() {
    persona temporal = new persona();
    boolean guardarClicked = libretaDirecciones.muestraEditarPersona(temporal);
    if (guardarClicked) {
      libretaDirecciones.getDatosPersona().add(temporal);
    }
  }

  // Muestro el diálogo editar persona cuando el usuario hace clic en el botón de
  // Editar
  @FXML
  private void editarPersona() {
    persona seleccionada = personaTabla.getSelectionModel().getSelectedItem();
    if (seleccionada != null) {
      boolean guardarClicked = libretaDirecciones.muestraEditarPersona(seleccionada);
      if (guardarClicked) {
        mostrarDetallesPersona(seleccionada);
      }
    } else {
      // Muestro alerta
      Alert alerta = new Alert(Alert.AlertType.WARNING);
      alerta.setTitle("Alerta");
      alerta.setHeaderText("Persona no seleccionada");
      alerta.setContentText("Por favor, selecciona una persona");
      alerta.showAndWait();
    }
  }

}