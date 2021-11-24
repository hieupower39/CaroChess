/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstracts;

import shared.models.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
    * @author hieup
    * This class handles the receive and send data between the client and server.
    * Using on client side.
 */
public class ClientHandling {
    protected Socket socket; //The socket of the client
    protected ObjectInputStream input; //The input reader
    protected ObjectOutputStream output; //The output writer
    
    //Constructor method
    public ClientHandling(String host, int port) throws IOException {
        socket = new Socket(host, port); //Create a new socket using two parameters host and port
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }
    
    //The sendRequest method help client send the request to the server
    public void sendRequest(Request request) throws IOException{
        output.writeObject(request);
    }
    
    //The receiveResult method help client receive the server return result.
    public Object receiveResult() throws IOException, ClassNotFoundException{
        return input.readObject();
    }
    
    //The close method help client close the connection with the server.
    public void close() throws IOException{
        sendRequest(new Request(Request.CLOSE));
        input.close();
        output.close();
        socket.close();
    }
    
    //Get method
    public Socket getSocket() {
        return socket;
    }
}
