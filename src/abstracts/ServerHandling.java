/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstracts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
    * @author hieup
    * This class handles the receive and send data between the client and server. 
    * Using on server side.
*/
public abstract class ServerHandling {
    protected ServerSocket server; //The server need to handling
    protected ObjectInputStream input; //The input reader
    protected ObjectOutputStream output; //The input writer
    protected Socket socket; //The client which this server accepted

    //Constructor method
    public ServerHandling(ServerSocket server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }

    //The sendData method help send the object to the accepted client
    public void sendData(Object object) throws IOException{
        output.writeObject(object);
        output.reset();
    }
    
    //The receiveData method help return the object which the accepted client sent
    public Object receiveData() throws IOException, ClassNotFoundException{
        return input.readObject();
    }

    
    //Get method
    public ServerSocket getServer() {
        return server;
    }

    public Socket getSocket() {
        return socket;
    }
    
    
}
