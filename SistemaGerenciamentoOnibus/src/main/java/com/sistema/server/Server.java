/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author vini
 */
public class Server {
    private ServerSocket serverSocketMotoristas;
    private static final int PORT_MOTORISTA = 5000, PORT_ADMIN = 5001;
    private static ArrayList<ClientHandler> clients = new ArrayList();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public void start() {
        try{
            serverSocketMotoristas = new ServerSocket(PORT_MOTORISTA);
        } catch (IOException e){
            e.printStackTrace();
        }
        while (true)
            try {
                System.out.println("a");
                Socket client = serverSocketMotoristas.accept();
                ClientHandler clientThread = new ClientHandler(client);
                clients.add(clientThread);
                pool.execute(clientThread);
                System.out.println("b");
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
    }

    public void stop() {
        try{
            serverSocketMotoristas.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
