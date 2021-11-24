/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessproject.server;

import shared.models.Person;
import shared.models.Room;
import shared.statics.Convertor;
import shared.exceptions.FullRoomException;
import shared.exceptions.NotFoundRoomException;
import abstracts.ServerHandling;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
    * @author hieup
    * This class handles the action of client which is related to the rooms.
 */
public class ServerRoomHandling extends ServerHandling{
    private Room host; //This is the room which client create. 
    private Room join; //This is the room which client join in
    
    //Construct method extends from class ServerHandling
    public ServerRoomHandling(ServerSocket server, Socket socket) throws IOException {
        super(server, socket);
    }
    
    /*
        The hostRoom method help player to host a room
        Input parameters include:
            listRoom - a list to store rooms
            player - who want to host the room
            port - the server port which player want to host
    */
    public void hostRoom(Map <Integer, Room> rooms, String data) throws ParserConfigurationException, SAXException, IOException{
        //System.out.println(data);
        host = new Room(new Person(Convertor.getValueFromXMLTag(data, "name"),
                Convertor.getValueFromXMLTag(data, "address")), 
                Integer.parseInt(Convertor.getValueFromXMLTag(data, "port"))); //Create a new room
        rooms.put(host.getRoomID(), host);//Store that room to the listRoom
    }
    
    /*
        The joinRoom method help player to join in a room
        Input parameters include:
            listRoom - a list to store rooms
            player - who want to join in the room
            roomIndex - the index of the room which player want to join in
    */
    public void joinRoom(Map <Integer, Room> rooms, String data) throws IOException, ParserConfigurationException, SAXException{
        /*
            If the room does not exist, return "Not found".
            Else check the status of the room:
                If the status of room is false, someone else is already in the room, return "Full".
                Else let player join the room and send that player the room which that player just joined.
        */
        int roomID = Integer.parseInt(Convertor.getValueFromXMLTag(data, "id"));
        if(rooms.containsKey(roomID)){
            join = rooms.get(roomID);
            System.out.println(join.getRoomID());
            if(join.isStatus()){
                join.join(new Person(Convertor.getValueFromXMLTag(data, "name"),
                    Convertor.getValueFromXMLTag(data, "address")));
                System.out.println(join.getClient().getName());
                this.sendData(join);
                //System.out.println("ok");
            }
            else {
                join = null;
                this.sendData(new FullRoomException());
            }
        }
        else{
            this.sendData(new NotFoundRoomException());
        }
    }
    
    
    //The refreshRoom method return to client a listRoom
    public void refreshRoom(ArrayList <String> roomsInformations) throws IOException {
        this.sendData(roomsInformations);
    }
    
    //The closeRoom  method help client close the room which they hosted;
    public void closeRoom(Map<Integer, Room> rooms){
        rooms.remove(host.getRoomID()); //remove the room was closed in the list
        host = null; //remove all information of the room was closed
    }
    
    //The method outRoom help client out the room which they joined in;
    public void exitRoom(){
        join.exit(); //clear the player joined in information
        join = null; //remove all information of the room which player was out;
    }
    
    /*
        The close method help client close connection with the server
        Input parameters include:
             listRoom - a list to store rooms
             listClient - a list to store the client
    */
    public void close(ArrayList<Socket> clients, Map <Integer,Room> rooms) throws IOException, SocketException {
        clients.remove(socket); //remove the client in the listClient
        //System.out.println(host.getRoomIndex());
        if(host!=null){
            closeRoom(rooms);//remove the room which this client hosted in the listRoom
        }
        //close all input output and connection
        socket.close();
    }

    //Get method
    public Room getHost() {
        return host;
    }

    public Room getJoin() {
        return join;
    }
   
}
