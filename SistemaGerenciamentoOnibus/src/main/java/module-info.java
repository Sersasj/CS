module com.sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    
    opens com.sistema to javafx.fxml;
    opens com.sistema.controller to javafx.fxml ;
    opens com.sistema.model.pojo to javafx.base, org.hibernate.orm.core;
    exports com.sistema;
}
