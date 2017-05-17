/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.net.*;
 
import java.io.*;
import javax.swing.JTextArea;
 

public class Hilo_servidor extends Thread{
    
    // Se declara un objeto ServerSocket para realizar la comunicación
    ServerSocket socket;
    Socket socket_cli;
    DataInputStream in;
    DataOutputStream out;
    String mensaje ="";
    String mensajeO = "";
    JTextArea mensajeE;
    JTextArea mensajeS;
    
    boolean fin = false;
    
    public Hilo_servidor(JTextArea tTexto,JTextArea tTexto2){
        mensajeE=tTexto;
        mensajeS=tTexto2;
    }
    public void conexion(){
        try{ 
             socket = new ServerSocket(6000);
             socket_cli = socket.accept();
             in = new DataInputStream(socket_cli.getInputStream());
             out = new DataOutputStream(socket_cli.getOutputStream()); 
        }catch (Exception e) {
           System.err.println(e.getMessage());
           System.exit(1);
        }
    }
    public void run(){
        try{ 
             do {
                  mensajeE.setText(mensaje);
                  mensaje = in.readUTF();
                  out.writeUTF(mensajeO);
                  mensajeO= mensajeS.getText();
                 } while (1>0);
            
        }catch (Exception e) { // " 20 15 55  
           System.err.println(e.getMessage());
           System.exit(1);
        }
    }
    
}
