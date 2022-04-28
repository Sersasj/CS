/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sistema.controller;

import com.sistema.model.dao.CorridaDAO;
import com.sistema.model.pojo.Corrida;
import com.sistema.util.Mediator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author sergi
 */
public class UIMobileCorridaController implements Initializable {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    @FXML
    private Label labelLinha, labelPlaca, labelMotorista;
    @FXML
    private Button buttonEmergencia, buttonIniciar, buttonFinalizar, btnVoltar;
    
    private Corrida corrida;

    private final CorridaDAO corridaDAO = new CorridaDAO();

    /**
     * Initializes the controller class.
     */
    public void simularCorrida() {
        Random rand = new Random();

        corrida.setPassPagantes(rand.nextInt(60) + 10);
        corrida.setPassNaoPagantes(rand.nextInt(40));
        corrida.setConsumoCombustivel(rand.nextFloat() * (float) 20.0 + (float) 3.0);
        corrida.setDistanciaPercorrida(rand.nextFloat() * (float) 5.0 + (float) 30.0);
        corrida.setConsumoCombustivel(rand.nextFloat() * 100);

    }
    private static String readAll(Reader rd) throws IOException {
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
      }
      return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
      InputStream is = new URL(url).openStream();
      try {
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONObject json = new JSONObject(jsonText);
        return json;
      } finally {
        is.close();
      }
    }    
    
    public void putText(String input) throws Exception{
        URL url = new URL("https://api.jsonbin.io/v3/b/6255c2fa21e89024ee8b8f35");
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
    
    public void finalizarLocalizacao() throws Exception{
    String placa = corrida.getOnibus().getPlaca();
    // Le Json
    JSONObject json = readJsonFromUrl("https://api.jsonbin.io/b/6255c2fa21e89024ee8b8f35"); 
    JSONObject jsonAux = new JSONObject();
    JSONArray jsonArray = json.getJSONArray("marcadores");

    
    // remove quem tem placa igual a corrida finalizada
    for(int i = 0; i < jsonArray.length(); i++){
        if(jsonArray.getJSONObject(i).get("placa").equals(placa)){
            jsonArray.remove(i);
            i = 0;
        }
    }
    
    for(int i = 0; i < jsonArray.length(); i++){   
        jsonAux.accumulate("marcadores", jsonArray.getJSONObject(i));
    } 
    
    
    putText(jsonAux.toString());
    
    
    }
    
    
  
    public void iniciarLocalizacao() throws Exception{
    String nome = corrida.getMotorista().getNome();
    String cpf = corrida.getMotorista().getCpf();
    String placa = corrida.getOnibus().getPlaca();
    String linha = corrida.getLinha().getNome();
    String lat = Float.toString(corrida.getLatitude());
    String lng = Float.toString(corrida.getLongitude());
    
    // Le Json
    JSONObject json = readJsonFromUrl("https://api.jsonbin.io/b/6255c2fa21e89024ee8b8f35");
    //System.out.println(json.toString());
    // Cria novo objeto
    JSONObject jsonNew = new JSONObject();
    jsonNew.put("placa", placa);
    jsonNew.put("linha", linha);    
    jsonNew.put("motorista", nome);
    jsonNew.put("cpf", cpf);    
    jsonNew.put("lat", lat);
    jsonNew.put("lng", lng );
    jsonNew.put("icon", "./icons/front-of-bus.png" );
    // Adiciona novo objeto no json
    json.accumulate("marcadores", jsonNew);
    putText(json.toString()); 

    

    }

    @FXML
    public void handleFinalizar(MouseEvent event) throws IOException {
        try {
            finalizarLocalizacao();
        } catch (Exception ex) {
            Logger.getLogger(UIMobileCorridaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Timestamp fim = new Timestamp(System.currentTimeMillis());
        simularCorrida();
        corrida.setFimCorrida(fim);
        corridaDAO.add(corrida);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileFinalizar.fxml"));

            UIMobileFinalizarController controller = new UIMobileFinalizarController();
            controller.setCorrida(corrida);
            fxmlLoader.setController(controller);
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            Stage stage = (Stage) buttonFinalizar.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
    
    @FXML
    public void handleVoltar(MouseEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/sistema/view/UIMobileEntrar.fxml"));         
            UIMobileEntrarController controller = new UIMobileEntrarController();
            controller.setLinha(corrida.getLinha());
            controller.setOnibus(corrida.getOnibus());
            controller.setMotorista(corrida.getMotorista());
            fxmlLoader.setController(controller);
            
            Parent root1 = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root1);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void handleIniciar(MouseEvent event) {
 
        Timestamp inicio = new Timestamp(System.currentTimeMillis());
        corrida.setInicioCorrida(inicio);
        // coordenadas do terminal
        corrida.setLatitude((float)-23.418590);
        corrida.setLongitude((float)-51.938117);

        System.out.println(inicio);

        buttonIniciar.setDisable(true);
        buttonIniciar.setVisible(false);

        buttonEmergencia.setDisable(false);
        buttonFinalizar.setDisable(false);
        buttonEmergencia.setVisible(true);
        buttonFinalizar.setVisible(true); 
        try {
            iniciarLocalizacao();
        } catch (Exception ex) {
            Logger.getLogger(UIMobileCorridaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleEmergencia(MouseEvent event) {
        Mediator.getInstance().handleEmergencia(corrida);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonEmergencia.setDisable(true);
        buttonFinalizar.setDisable(true);
        buttonEmergencia.setVisible(false);
        buttonFinalizar.setVisible(false);

        labelPlaca.setText(corrida.getOnibus().getPlaca());
        labelLinha.setText(corrida.getLinha().toString());
        labelMotorista.setText(corrida.getMotorista().getNome());
        
        Mediator.getInstance().registerControllerMobile(this);
    }

    public void startConnection() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
            clientSocket = new Socket(address, 2000);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

}