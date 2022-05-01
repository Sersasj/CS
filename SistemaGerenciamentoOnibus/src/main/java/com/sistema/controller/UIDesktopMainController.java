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
import com.sistema.util.Mediator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTela("/com/sistema/view/UIDesktopMapa.fxml");
        hBoxMenuAberto.setDisable(true);
        hBoxMenuAberto.setVisible(false);
        
        // verifica se tem emergencia a cada 5 seg
        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
            Platform.runLater(() -> {
                try {        
                    verifyEmergencia();
                } catch (Exception ex) {
                    Logger.getLogger(UIDesktopMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });    

    
            }
        }, 1000,5000);
        
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
    void handleButtonAdmin(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDAdmin.fxml");
    }

    @FXML
    public void handleButtonOnibus(MouseEvent event) {
        carregarTela("/com/sistema/view/UIDesktopCRUDOnibus.fxml");
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
        urlConnection.setRequestProperty("X-Master-Key", "$2b$10$I/.wsfiIExM9YArP5Hz55uQc5L.0l80Cb4Nt865TxeT39uPSd4Q5S");

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);

        //String input = json.toString();
        OutputStream os = urlConnection.getOutputStream();
        os.write(input.getBytes("UTF-8"));
        os.flush();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + urlConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (urlConnection.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            urlConnection.disconnect(); 
    }


    
    public void verifyEmergencia() throws Exception{
    JSONObject json = readJsonFromUrl("https://api.jsonbin.io/b/626db70c019db4679693e10e");     
    JSONObject jsonAux = new JSONObject();
    JSONArray jsonArray = json.getJSONArray("emergencias");
    // Ativa popup se placa != teste e remove ele   
    for(int i = 0; i < jsonArray.length(); i++){
        if(!jsonArray.getJSONObject(i).get("placa").equals("Teste")){
            Corrida corrida = new Corrida();
            Motorista motorista = new Motorista();
            Onibus onibus = new Onibus();
            Linha linha = new Linha();
            motorista.setCpf(jsonArray.getJSONObject(i).get("cpf").toString());
            motorista.setNome(jsonArray.getJSONObject(i).get("motorista").toString());
            motorista.setTelefone(jsonArray.getJSONObject(i).get("telefone").toString());
            onibus.setPlaca(jsonArray.getJSONObject(i).get("placa").toString());
            linha.setNumero(Integer.parseInt(jsonArray.getJSONObject(i).get("linhaNum").toString()));
            linha.setNome(jsonArray.getJSONObject(i).get("linhaNome").toString());
            corrida.setOnibus(onibus);
            corrida.setMotorista(motorista);
            corrida.setLatitude(Float.parseFloat(jsonArray.getJSONObject(i).get("lat").toString()));
            corrida.setLatitude(Float.parseFloat(jsonArray.getJSONObject(i).get("lng").toString()));
            corrida.setLinha(linha);

            
            popUpEmergencia(corrida);
            
            jsonArray.remove(i);
            i = 0;
        }
    }
  
    
    for(int i = 0; i < jsonArray.length(); i++){   
        jsonAux.accumulate("emergencias", jsonArray.getJSONObject(i));
    } 
    
    
    putText(jsonAux.toString(),"https://api.jsonbin.io/v3/b/626db70c019db4679693e10e");
    
    
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
