module iesthiar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens iesthiar to javafx.fxml;
    exports iesthiar;
}
