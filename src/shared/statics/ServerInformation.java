/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.statics;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;



/**
    * @author hieup
 */
public class ServerInformation {
    
        
    //This class only have static function
    private ServerInformation(){
        
    }
    /*
        getServerPort function return the defaut port 2207 to server side.
        You can change the port at here if this port isn't available on your network.
    */
    public static int getServerPort(){
        return 2207;
    }
    public static int getDatagramPort(){
        return 2206;
    }
    /*
        getServerHost function return the host name of the server.
        You can change the host name if you host the server on another hosting.
    */
    public static String getServerHost() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }
    
    //getClientPort function return the open TCP port.
    public static int getClientPort() throws IOException{
        int port;
        for(port = 2208; port <= 65535; port++){
            try{
                ServerSocket serverTCP = new ServerSocket(port);
                serverTCP.close();
                return port;
            } catch(SocketException ex){
            }
        }
                
        return -1;
    }
}
