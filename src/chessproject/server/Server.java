/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chessproject.server;

import shared.models.Room;
import shared.models.Request;
import shared.statics.ServerInformation;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
    * @author hieup
    * Main server run
 */
public class Server {
    static ArrayList <Socket> clients = new ArrayList();// A list of clients is availible
    static Map <Integer, Room> rooms = new HashMap<Integer, Room>();// A list of rooms which clients hosted
    static ArrayList <String> roomsInformations = new ArrayList();
    static ArrayList <DatagramPacket> packets = new ArrayList();
    static ServerSocket server;
    static DatagramSocket udp;
    
    
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(ServerInformation.getServerPort());
        udp = new DatagramSocket(ServerInformation.getDatagramPort());
        while(true){
            //Server always listen and accept client each client server create a new thread to handle it
            createClientThread(server.accept());
        }
    }

    //Static method for server
    
    private static void createClientThread(Socket client) throws IOException {
        //This method create and a new thread to handling requests from client
        ServerRoomHandling roomHandling = new ServerRoomHandling (server, client);
        clients.add(client);
        byte[] buf = new byte[1024];
        DatagramPacket dpReq = new DatagramPacket(buf, buf.length);
        udp.receive(dpReq);
        packets.add(dpReq);
        Thread process;
        process = new Thread(new Runnable(){
            @Override
            public void run() {
                /*
                If the connection to the client is closed the loop will end.
                Else call the requestHandling to handle the request from client.
                */
                while(true){
                    try {
                        requestHandle(roomHandling, dpReq);
                    } catch (ClassNotFoundException ex) {
                    } catch (IOException ex) {
                        break;
                    } catch (ParserConfigurationException ex) {
                        
                    } catch (SAXException ex) {
                        
                    }
                }
                packets.remove(dpReq);
                clients.remove(client);
                
            }
            
        });
        process.start();
    }
    
    //requestHandle function will take the request from the client, check what type it is and handles it by the roomHandling.
    private static void requestHandle(ServerRoomHandling roomHandling,DatagramPacket dpReq) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException{
        Request request = (Request) roomHandling.receiveData(); //Get request from the client
        System.out.println(request.getRequest());
        switch(request.getRequest()){
            case Request.HOST: 
                roomHandling.hostRoom(rooms, request.getData());
                updateAll(dpReq);
                break;
            case Request.JOIN:
                roomHandling.joinRoom(rooms ,request.getData());
                updateAll(dpReq);
                break;
            case Request.CLOSEROOM:
                roomHandling.closeRoom(rooms);
                updateAll();
                break;
            case Request.EXIT:
                roomHandling.exitRoom();
                updateAll();
                break;
            case Request.CLOSE:
                roomHandling.close(clients, rooms);
                updateAll(dpReq);
                break;
            case Request.REFRESH:
                roomHandling.refreshRoom(roomsInformations);
                break;
            
        }
        updateRoomsInformations();
    }
    
    private static void updateAll(){
        updateRoomsInformations();
        for(DatagramPacket packet : packets){
            try {
                String strRes = "OK";
                DatagramPacket dpRes = new DatagramPacket(strRes.getBytes(), strRes.length(), packet.getAddress(), packet.getPort());
                udp.send(packet);
            } catch (IOException ex) {
            }
        }
    }

    private static void updateAll(DatagramPacket dpReq) {
        updateRoomsInformations();
        try{
            for(DatagramPacket packet : packets){
                if(packet!=dpReq){
                    String strRes = "OK";
                    DatagramPacket dpRes = new DatagramPacket(strRes.getBytes(), strRes.length(), packet.getAddress(), packet.getPort());
                    udp.send(packet);
                }
            }
        } catch (IOException ex) {   
        }
    }
    
    private static void updateRoomsInformations(){
        roomsInformations = new ArrayList();
        for(Room room : rooms.values()){
            roomsInformations.add(room.toString());
        }
    }
}
