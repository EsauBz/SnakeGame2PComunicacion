/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClienteC;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;
// importar la libreria java.io


/**
 *
 * @author Monitor
 */
public class ClienteC extends Thread{

     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Socket socket;
    byte[] mensaje_bytes = new byte[256];
    String mensaje="";
    String mensajeIn="";
    DataOutputStream out;
    DataInputStream In;
    JTextArea mensajeE;
    JTextArea TextAreaIn;
    
    int bandera=0;
    public ClienteC(JTextArea cTexto,JTextArea cTexto2){
        mensajeE=cTexto;
        TextAreaIn=cTexto2;
    }
    
    public void conexion()
    {
        try {
            if(bandera==0){
               socket = new Socket("192.168.1.250",6000);
               bandera=1;
            }              
        out = new DataOutputStream(socket.getOutputStream());
        In = new DataInputStream(socket.getInputStream());
        }
        catch (Exception e) {
             System.err.println(e.getMessage());
             System.exit(1);
        }
    }
    
    public void run(){
        try{
         do {
             mensaje=mensajeE.getText();
             out.writeUTF(mensaje);
             mensajeIn = In.readUTF();
             TextAreaIn.setText(mensajeIn);

            } while (1>0);
        }
        catch (Exception e) {
             System.err.println(e.getMessage());
             System.exit(1);
        }
    }
}
