module com.sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.sistema to javafx.fxml;
    exports com.sistema;
}
