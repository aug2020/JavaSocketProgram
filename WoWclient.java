import java.io.*;
import java.net.*;
import java.util.*;

public class WoWclient{
    private DatagramSocket clientSocket;
    private String WoWmessage;
    public Timer time;
    
    
    public WoWclient() throws SocketException {
        clientSocket = new DatagramSocket();
        time = new Timer();
        
       
        
    }
    public static void main(String[] args){
     
    String hostname = args[0];
    int port = Integer.parseInt(args[1]);
    
    try {
            WoWclient client = new WoWclient();
            
            client.service(hostname,port);
            
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
        private void service(String hostname,int port) throws IOException {
          
           
          while (true) 
          {
            try {
                 InetAddress IP = InetAddress.getByName(hostname); // ip of the server
           
           int portnumber = port; // port number 
           
           byte[] receiveData = new byte[1024]; // data received from the server
           
         
           DatagramPacket sendPacket = new DatagramPacket(new byte[1], 1, IP, portnumber);
          
           clientSocket.send(sendPacket);
           
 
           DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
  
           clientSocket.receive(receivePacket);
    
           WoWmessage= new String (receivePacket.getData());
           
           System.out.println("Words of wisdom from server: "+ WoWmessage+"\n"); // message that prints from the server
              
                Thread.sleep(5000); // applications runs then it waits 5 seconds before sending a new datagram
            } 
            catch (InterruptedException e) {
                System.out.println("InterruptedException Exception" + e.getMessage());
            }
            
           }
              //clientSocket.close(); // cannot close the socket or else the programm doesnt not print ever 5 seconds 

           
           }
        

  
}