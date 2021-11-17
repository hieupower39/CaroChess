/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessproject.client;

import chessproject.client.RoomListGUI;
import shared.statics.ServerInformation;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieup
 */
public class AutoUpdateHandling extends Thread{
    private DatagramSocket socket; 
    private DatagramPacket dpReq;
    private RoomListGUI parent;

    public AutoUpdateHandling(RoomListGUI parent) throws SocketException, UnknownHostException, IOException {
        String req = "AUTOUPDATE";
        this.parent = parent;
        this.socket = new DatagramSocket();
        this.dpReq = new DatagramPacket(req.getBytes(), req.length(), InetAddress.getByName(ServerInformation.getServerHost()), ServerInformation.getDatagramPort());
        this.socket.send(dpReq);
    }

    @Override
    public void run() {
        while(true){
            try {
                byte[] buf = new byte[1024];
                DatagramPacket dpRes = new DatagramPacket(buf, buf.length);
                socket.receive(dpRes);
                parent.refreshData();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }
    
    
    
}
