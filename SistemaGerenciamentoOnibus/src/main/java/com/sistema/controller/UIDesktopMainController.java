/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import static com.sistema.controller.UIMobileCorridaController.readJsonFromUrl;
import com.sistema.model.pojo.Corrida;
import com.sistema.model.pojo.Linha;
import com.sistema.model.pojo.Motorista;
import com.sistema.model.pojo.Onibus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIDesktopMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonFechar, buttonMinimizar, buttonToggleMenu;
    @FXML
    private AnchorPane anchorPaneMapa, anchorPaneOpacidade;
    @FXML
    private HBox hBoxMenuAberto, hBoxMain;
    @FXML
    private StackPane stackPaneRoot;
    @FXML
    private AnchorPane anchorPaneConteudo;
    
    private Boolean existeEmergencia = false;
    private Corrida corridaEmergencia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
        
        // verifica se tem uma emergencia guardada a cada 5 seg
        Timeline timer = new Timeline(
            new KeyFrame(Duration.seconds(5), evt -> {
                if(existeEmergencia){
                    popUpEmergencia(corridaEmergencia);
                }
                           
            })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        
        // busca por emergencias no servidor
        ScheduledService<Void> schedule = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {        
                            verifyEmergencia();
                        } catch (Exception ex) {
                            Logger.getLogger(UIDesktopMainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return null;
                    }
                };
            }
        };
        schedule.setPeriod(Duration.seconds(2));
        schedule.start();
    }
    
    public void carregarTela(String enderecoTela){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(enderecoTela));

            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

            anchorPaneConteudo.getChildren().setAll(anchorPane);
            anchorPaneConteudo.setTopAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setLeftAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setRightAnchor(anchorPane, 0.0);
            anchorPaneConteudo.setBottomAnchor(anchorPane, 0.0);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
            
    @FXML
    public void handleButtonMapa(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
    }
    
    @FXML
    public void handleButtonHome(MouseEvent event) throws Exception {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
    }

    @FXML
    public void handleButtonMotorista(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDMotorista.fxml");
    }

    @FXML
    public void handleButtonOnibus(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDOnibus.fxml");
    }
    @FXML
    public void handleButtonLinha(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDLinha.fxml");
    }    
    @FXML
    public void handleButtonProblemas(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopProblemas.fxml");
    }    
    @FXML
    public void handleButtonHistorico(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopHistoricoCorridas.fxml");
    }
    
    @FXML
    public void handleButtonRelatorio(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopRelatorio.fxml");
    }
    
    
    public void putText(String input, String urlStr) throws Exception{
        URL url = new URL(urlStr);
        HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
        //urlConnection.setConnectTimeout(5000);
        urlConnection.setRequestMethod("PUT"); 

        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("X-Master-Key", "$2b$10$Jn.s9m9qjc8TzU4e5hT6l./50kdb39Hja59kP43K/EvGGpVwEBIY6");

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);

        //String input = json.toString();
        OutputStream os = urlConnection.getOutputStream();
        os.write(input.getBytes("UTF-8"));
        os.flush();
    

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (urlConnection.getInputStream())));



            urlConnection.disconnect(); 
    }


    
    public void verifyEmergencia() throws Exception{
    JSONObject json = readJsonFromUrl("https://api.jsonbin.io/b/6271c0a738be296761fbf3ca");     
    JSONObject jsonAux = new JSONObject();
    JSONArray jsonArray = json.getJSONArray("emergencias");
    // Ativa popup se placa != teste e remove ele   
    for(int i = 0; i < jsonArray.length(); i++){
        if(!jsonArray.getJSONObject(i).get("placa").equals("Teste")){
            Motorista motorista = new Motorista();
            Onibus onibus = new Onibus();
            Linha linha = new Linha();
            corridaEmergencia = new Corrida();
            motorista.setCpf(jsonArray.getJSONObject(i).get("cpf").toString());
            motorista.setNome(jsonArray.getJSONObject(i).get("motorista").toString());
            motorista.setTelefone(jsonArray.getJSONObject(i).get("telefone").toString());
            onibus.setPlaca(jsonArray.getJSONObject(i).get("placa").toString());
            linha.setNumero(Integer.parseInt(jsonArray.getJSONObject(i).get("linhaNum").toString()));
            linha.setNome(jsonArray.getJSONObject(i).get("linhaNome").toString());
            corridaEmergencia.setOnibus(onibus);
            corridaEmergencia.setMotorista(motorista);
            corridaEmergencia.setLatitude(Float.parseFloat(jsonArray.getJSONObject(i).get("lat").toString()));
            corridaEmergencia.setLatitude(Float.parseFloat(jsonArray.getJSONObject(i).get("lng").toString()));
            corridaEmergencia.setLinha(linha);

            existeEmergencia = true;
            
            jsonArray.remove(i);
            i = 0;
        }
    }
  
    
    for(int i = 0; i < jsonArray.length(); i++){   
        jsonAux.accumulate("emergencias", jsonArray.getJSONObject(i));
    } 
    
    
    putText(jsonAux.toString(),"https://api.jsonbin.io/v3/b/6271c0a738be296761fbf3ca");
    
    
    }


    
    
    
    @FXML
    public void popUpEmergencia(Corrida corrida) {
        
        try {
            // vai usar uma corrida como parametro
            System.out.println("emergencia ativada");
            System.out.println(corrida.toString());
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIDesktopPopupEmergencia.fxml"));
            UIDesktopPopupEmergenciaController controller = new UIDesktopPopupEmergenciaController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent popupEmergencia = fxmlLoader.load();
            
            Stage stageEmergencia = new Stage();
            stageEmergencia.initModality(Modality.APPLICATION_MODAL);
            stageEmergencia.setScene(new Scene(popupEmergencia));
            stageEmergencia.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
        existeEmergencia = false;
    }

    @FXML
    public void handleButtonFechar(ActionEvent event) {
        Stage stage = (Stage) buttonFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleButtonMinimizar(ActionEvent event) {
        Stage stage = (Stage) buttonMinimizar.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void handleToggleMenu(ActionEvent event) {
        hBoxMenuAberto.setDisable(!hBoxMenuAberto.isDisable());
        hBoxMenuAberto.setVisible(!hBoxMenuAberto.isVisible());
    }

    @FXML
    public void handlePaneOpacidade(MouseEvent event) {
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
    }

}
