/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessproject.client;

import shared.models.Request;
import abstracts.ClientHandling;
import java.io.IOException;

/**
    * @author hieup
    * This class handles some action with the room you have joined.
 */
public class ClientJoinHandling extends ClientHandling{
    
    //Construct method extends from class ClientHandling
    public ClientJoinHandling(String host, int port) throws IOException {
        super(host, port);
    }
    
    //The sendData method help to send a string data to the host room.
    public void join(String data) throws IOException{
        this.sendRequest(new Request(Request.SETNAME, data));
    }
    
    //The out method help send a request to exit this room to the host room and close the connection with it.
    public void exit() throws IOException{
        this.sendRequest(new Request(Request.EXIT) );
        this.socket.close();
    }
    
}
