/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import java.io.Serializable;
/**
    * @author hieup
    * This class stores the information of a room and handle some action in a room
 */
public class Room implements Serializable{
    private Person host; //The player hosted the room.
    private Person client; //The client joined in the room.
    private boolean status; //The status of the room (true is available, false is full).
    private int port; //The port that player want to host the room.
    private int roomID; //A number for identification the room.
    private static int id = 100; //Some static to increment.

    //Constructor method
    public Room(Person host, int port) {
        this.host = host;
        this.status = true;
        this.port = port;
        this.roomID=id++;
    }
    
    //The join method help the client join this room
    public void join(Person client) {
        this.client = client; //Set the client information
        this.status = false; //Set the status to false that mean the room is full
    } 
    
    //The out method help client which in this room to out
    public void exit(){
        this.client = null; //Remove the client information was joined in this room
        this.status = true; //Set the status to true that mean the room is availible
    }
    
    //Get method
    public static int getId() {
        return id;
    }

    public int getRoomID() {
        return roomID;
    }

    public Person getHost() {
        return host;
    }

    public Person getClient() {
        return client;
    }

    public int getPort() {
        return port;
    }
    
    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "<room>"
                + "<id>"+roomID+"</id>"
                + "<host>"+host.getName()+"</host>"
                + "<status>" + status +"</status>"
                + "</room>";
    }
    
    
}
