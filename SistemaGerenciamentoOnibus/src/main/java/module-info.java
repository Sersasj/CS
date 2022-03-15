module com.sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens com.sistema to javafx.fxml;
    exports com.sistema;
}
