package com.sistema;

import com.sistema.model.pojo.Ponto;
import com.sistema.util.ValidadorString;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/UIInicial"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    private static void testeBD(){
        Ponto p1 = new Ponto(3, (float)-10.0, (float)10.0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaGerenciamentoOnibusPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p1);
        em.getTransaction().commit();
        System.out.println("TesteBD feito");
    }
    private static void testesREGEX(){
        ValidadorString vs = new ValidadorString();
        String nome, email, telefone, rg, cpf;
        
        email = "ra115672@uem.br";
        if (vs.validarEmail(email) == true){
            System.out.println(email);
        }
        
        telefone = "44 999515530";
        if (vs.validarTelefone(telefone) == true){
            System.out.println(vs.formatarTelefone(telefone));
        }
        
        nome = "Stany Helberth";
        if (vs.validarNome(nome) == true){
            System.out.println(nome);
        }
        
        cpf = "111.222.333-44";
        if (vs.validarCPF(cpf) == true){
            System.out.println(vs.formatarCPF(cpf));
        }
        
        rg = "112223334";
        if (vs.validarRG(rg) == true){
            System.out.println(vs.formatarRG(rg));
        }
    } 

    public static void main(String[] args) {
        testesREGEX();
        launch();
    }

}

