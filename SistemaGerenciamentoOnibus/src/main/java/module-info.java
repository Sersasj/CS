module com.sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens com.sistema to javafx.fxml;
    opens com.sistema.controller to javafx.fxml ;
    opens com.sistema.model.pojo to javafx.base ;
    exports com.sistema;
}
