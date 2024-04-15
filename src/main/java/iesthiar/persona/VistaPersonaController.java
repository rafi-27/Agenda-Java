package iesthiar.persona;

import java.net.URL;
import java.util.ResourceBundle;

import iesthiar.LibretaDirecciones;
import iesthiar.persona.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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




}