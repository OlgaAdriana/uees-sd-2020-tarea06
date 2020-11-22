/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adria
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here}
         final int PUERTO = 5000;
        byte[] buffer = new byte[1024];
        byte[] buffer2 = new byte[1024];
        int posicion1 = 0,posicion2=0;
        String mensaje2="";
                
     try {
         
               
            System.out.println("****************Iniciado el servidor UDP*********");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
           
            
            //Siempre atendera peticiones
            while (true) {
                 
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                 
                //Recibo el datagrama
                socketUDP.receive(peticion);
                System.out.println("Datos Recibidos\n");
                 
                //Convierto lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData());
             for (int x=0;x<mensaje.length();x++)
                {
                    char letra = mensaje.charAt(x);
                    if(letra=='/'){
                        /* System.out.println("Posicion:"+x+"\n");*/
                         posicion1=x;
                          break;
                    }
                   
                }
                         
                
                System.out.println(mensaje.substring(0,posicion1)+"\n");
                System.out.println(mensaje.substring(posicion1+1,mensaje.length())+"\n");
                
		
                
 
               /* System.out.println(mensaje+"\n");*/
               
                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
 
                mensaje2 = "¡Mensaje recibido OK!\n";
                buffer2 = mensaje2.getBytes();
 
                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer2, buffer2.length, direccion, puertoCliente);
 
                //Envio la información
                System.out.println("Envio la informacion del cliente\n");
                socketUDP.send(respuesta);
                 
            }
 
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
 
     
    }
    
}
