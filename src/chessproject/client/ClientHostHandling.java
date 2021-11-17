/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessproject.client;

import shared.models.Request;
import abstracts.ServerHandling;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
    * @author hieup
    * This class handles the action of hosting which is related to the host room.
 */
public class ClientHostHandling extends ServerHandling{
    private Boolean isOut = false; //A checker if the client was out the room
    
    //Construct method extends from class ServerHandling
    public ClientHostHandling(ServerSocket server, Socket socket) throws IOException {
        super(server, socket);
    }
    
    //This method help send request to client that the room is closing.
    public void close() throws IOException {
        this.sendData(new Request(Request.CLOSEROOM));
    }
    
    //Get method
    public Boolean getIsOut() {
        return isOut;
    }

    //Set method
    public void setIsOut(Boolean isOut) {
        this.isOut = isOut;
    }
    
}
