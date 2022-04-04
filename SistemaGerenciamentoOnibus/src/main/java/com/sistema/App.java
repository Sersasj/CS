package com.sistema;

import com.sistema.model.dao.MotoristaDAO;
import com.sistema.model.pojo.Motorista;
import com.sistema.model.pojo.Ponto;
import com.sistema.server.Server;
import com.sistema.util.ValidadorString;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        MotoristaDAO motoristaDAO = new MotoristaDAO();
        Motorista m = motoristaDAO.getById("111.111.111-11");

        System.out.println("aaaa" + m.toString());
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
        
        cpf = "11122";
        System.out.println(vs.formatarCPFIncompleto(cpf));
        cpf = "111.222";
        System.out.println(vs.formatarCPFIncompleto(cpf));
        cpf = "111.222.3";
        System.out.println(vs.formatarCPFIncompleto(cpf));
        cpf = "111.222.333";
        System.out.println(vs.formatarCPFIncompleto(cpf));
        cpf = "111.222.333-4";
        System.out.println(vs.formatarCPFIncompleto(cpf));
        cpf = "111.222.333-4123";
        System.out.println(vs.formatarCPFIncompleto(cpf));
//        if (vs.validarCPF(cpf) == true){
//            System.out.println(vs.formatarCPF(cpf));
//        }
        
        rg = "112223334";
        if (vs.validarRG(rg) == true){
            System.out.println(vs.formatarRG(rg));
        }
    } 

    public static void main(String[] args) {
        testesREGEX();
        launch();
        //testeBD();
    }

}

