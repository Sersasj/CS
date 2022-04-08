module com.sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.scripting;
    requires java.sql;
    requires java.base;
    requires java.persistence;
    requires org.apache.commons.io;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires org.json;
    opens com.sistema to javafx.fxml;
    opens com.sistema.controller to javafx.fxml ;
    opens com.sistema.model.pojo to javafx.base, org.hibernate.orm.core;
    exports com.sistema;
}
