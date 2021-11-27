/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessproject.client;

import shared.exceptions.FullRoomException;
import shared.exceptions.NotFoundRoomException;
import shared.models.Request;
import shared.models.Room;
import shared.models.RoomInformations;
import abstracts.ClientHandling;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
    * @author hieup
    * This class handle send requests which is related to the rooms to the server.
 */
public class ClientRoomHandling extends ClientHandling{
    
    //Construct method extends from class ClientHandling
    public ClientRoomHandling(String host, int port) throws IOException {
        super(host, port);
    }
    
    //The roomRefresh method help send request and return a current list of rooms.
    public ArrayList <RoomInformations> roomRefresh() throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException{
        this.sendRequest(new Request(Request.REFRESH));
        ArrayList<RoomInformations> rooms = new ArrayList();
        ArrayList <String> datas = (ArrayList <String>) this.receiveResult();
        for(String data : datas){
            rooms.add(new RoomInformations(data));
        }
        return rooms;
    }
    
    //The roomHost method help send request to host a room.
    public Room roomHost(String name, int port, int size, int mode, boolean isUndo, String note) throws IOException, ClassNotFoundException {
        String data = "<room>"
                + "<person>"
                + "<name>"+name+"</name>"
                + "<address>"+this.socket.getInetAddress().getHostAddress()+"</address>"
                + "</person>"
                + "<port>"+port+"</port>"
                + "<size>" + size + "</size>"
                + "<mode>" + mode + "</mode>"
                + "<undo>" + isUndo + "</undo>"
                + "<note>" + note + "</note>"
                + "</room>";
        this.sendRequest(new Request(Request.HOST, data));
        return (Room) this.receiveResult();
    }
    
    //The roomJoin method help join a room you want if it available and return the room you just joined.
    public Room roomJoin(String name, int roomID) throws IOException, ClassNotFoundException, FullRoomException, NotFoundRoomException, Exception{
        String data = "<join><person>"
                + "<name>"+name+"</name>"
                + "<address>"+this.socket.getInetAddress().getHostAddress()+"</address>"
                + "</person>"
                + "<id>"+roomID+"</id></join>";
        this.sendRequest(new Request(Request.JOIN, data));
        System.out.println("waiting");
        Object object = this.receiveResult();
        if(!(object instanceof Room)){
            throw (Exception) object;
        }
        return (Room) object;
    }
    
    //The roomClose method help close a room you have hosted by send to the server a CLOSEROOM request.
    public void roomClose() throws IOException{
        this.sendRequest(new Request(Request.CLOSEROOM));
    }
    
    //The roomOut method help exit a room you have joined by send to the server a OUT request.
    public void roomOut() throws IOException {
        this.sendRequest(new Request(Request.EXIT));
    }
    
}


