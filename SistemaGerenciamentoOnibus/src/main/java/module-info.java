module com.trabcs.sistemagerenciamentoonibus {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.trabcs.sistemagerenciamentoonibus to javafx.fxml;
    exports com.trabcs.sistema;
}
