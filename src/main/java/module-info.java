module iesthiar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens iesthiar to javafx.fxml;
    exports iesthiar;

    opens iesthiar.persona to javafx.fxml;
    exports iesthiar.persona;
}
