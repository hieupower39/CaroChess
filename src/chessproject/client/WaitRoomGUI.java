/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessproject.client;

import chessproject.game.CaroFrame;
import java.awt.Color;
import shared.models.Request;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import shared.statics.Convertor;
/**
 *
 * @author hieup
 */
public class WaitRoomGUI extends javax.swing.JFrame implements WindowListener{ 
    private ServerSocket server; //The server if this is a host room.
    private final RoomListGUI parent; //The parent will help you in some action of room.
    private ClientJoinHandling join; //The handling if you are client.
    private ClientHostHandling host; //The handling if you are host.
    private CaroFrame game;
    
    /**
     * Creates new form WaitRoomGUI
     */
    
    public WaitRoomGUI(RoomListGUI parent, String name, int port) throws IOException {
        //Start a host room
        this.parent=parent;
        initHostRoom(port);
        initComponents();
        this.addWindowListener(this);
    }
    
    public WaitRoomGUI(RoomListGUI parent, String host, String name, int port) throws IOException{
        //Join a room
        this.parent=parent;
        initJoinRoom(host, name, port);
        initComponents();
        this.addWindowListener(this);
        this.remove(startButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        player1Name = new javax.swing.JFormattedTextField();
        player2Name = new javax.swing.JFormattedTextField();
        startButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        player1Name.setText("Player 1");

        player2Name.setText("Player 2");
        player2Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2NameActionPerformed(evt);
            }
        });

        startButton.setText("Bắt đầu");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Thoát");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jButton4.setText("Sẵn sàng");

        jButton5.setText("Khán giả");

        jButton6.setText("X");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setText("Số phòng: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(player1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(player2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4)))))
                        .addGap(0, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addComponent(player1Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player2Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(startButton))
                .addContainerGap())
        );

        exitButton.getAccessibleContext().setAccessibleName("Exit");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Action Event handling
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            // TODO add your handling code here:
            host.sendData(new Request(Request.STARTGAME));
            startGame(true);
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void player2NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player2NameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //When user click on Exit button
        try{
            //Try to close the host if you are hosting and this room have a client in side.
            host.close();
        } catch (Exception ex) {    
        }
        try {
            //Try to close the room if you are the hosting and close server
            server.close();
            parent.closeRoom();
        } catch (Exception ex) {   
        } 
        try{
            //Try to out the room if you are client.
            join.exit();
            parent.exitRoom();
        } catch (Exception ex) {  
        }
        //Finally display the parent was disbled (RoomListGUI) and close this window.
        parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    //Set method
    public void setName1(String name) {
        player1Name.setText(name);
    }
    public void setName2(String name) {
        player2Name.setText(name);
    }
    
    //Private method
    private void initHostRoom(int port) throws IOException{
        /*
            This method will be called if you are the hosting:
                First, open the server with the port parameter.
                Then, wait for a client joined this room.
                Finally, wait for the client's requests util the client disconnects.
        */
        server = new ServerSocket(port);
        Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            host = new ClientHostHandling(server, server.accept());
                            //Take and display the player 2 name from the client first.
                            Request request = (Request) host.receiveData(); 
                            if(request.getRequest() == Request.SETNAME){
                                player2Name.setText(request.getData()); 
                            }
                            setVisible(true);
                            while(!host.getIsOut()){
                                requestHandling(host); //Wait and handle all client's requests.
                            }
                        }
                    } catch (IOException | ClassNotFoundException ex) {   
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(WaitRoomGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(WaitRoomGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        thread.start();
    }
    
    private void initJoinRoom(String host, String name, int port) throws IOException{
        /*
            This method will be called if you are client:
                First, make a connection with the server of the room which you just joined.
                Then, send your name to the hosting.
                Finally, wait for the server's requests util the room is closed.
        */
        join = new ClientJoinHandling(host, port);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    join.join(name);
                    while(true){
                        requestHandling(join);
                    }
                } catch (IOException | ClassNotFoundException e) {    
                } catch (ParserConfigurationException | SAXException ex) {
                    Logger.getLogger(WaitRoomGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        thread.start();
    }
     
     private void requestHandling(ClientHostHandling host) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        //This method will take the request from the client, check what type it is and handles it by the ClientHostHandling.
        Request request = (Request) host.receiveData();
        //System.out.println(request);
        switch(request.getRequest()){
            case Request.EXIT:
                //If request is EXIT remove name of that client and wait for a new connection.
                player2Name.setText("Player 2");
                if(game!=null){
                    game.dispose();
                    game = null;
                }
                setVisible(true);
                host.setIsOut(true);
            case Request.MOVE:
                String abscissa = Convertor.getValueFromXMLTag(request.getData(), "abscissa");
                String ordinate = Convertor.getValueFromXMLTag(request.getData(), "ordinate");
                moveExcute(Integer.parseInt(abscissa), Integer.parseInt(ordinate));
                break;
            case Request.YOULOST:
                JOptionPane.showMessageDialog(this, "You lost");
                gameOver(false);
                break;
            
         }
     }
     
     private void requestHandling(ClientJoinHandling join) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
         //This method will take the request from the hosting, check what type it is and handles it by the ClientJoinHandling.
        Request request = (Request) join.receiveResult();
        System.out.println(request);
        switch(request.getRequest()){
            case Request.CLOSEROOM:
                /*
                    If the request is CLOSEROOM you the join will handle to close the connection with this room.
                    The parent will handle to exit this room, close this window and display the parent (RoomListGUI).
                */
                join.exit();
                parent.exitRoom("Phòng đã đóng");
                parent.setVisible(true);
                if(game!=null){
                    game.dispose();
                    game = null;
                }
                this.dispose();
                break;
            case Request.STARTGAME:
                startGame(false);
                break;
            case Request.MOVE:
                String abscissa = Convertor.getValueFromXMLTag(request.getData(), "abscissa");
                String ordinate = Convertor.getValueFromXMLTag(request.getData(), "ordinate");
                moveExcute(Integer.parseInt(abscissa), Integer.parseInt(ordinate));
                break;
            case Request.YOULOST:
                JOptionPane.showMessageDialog(this, "You lost");
                gameOver(false);
                break;
                
         }
     }
     
     private void startGame(boolean isTurn){
        game = new CaroFrame(this, isTurn);
        game.setVisible(true);
        this.setVisible(false);
     }
     
     public void moveCheck(int abscissa, int ordinate) throws IOException{
         String xmlData = "<coordinate>"
                 +"<abscissa>"+ abscissa + "</abscissa>"
                 +"<ordinate>"+ ordinate + "</ordinate>"
                 +"</coordinate>";
         Request request = new Request(Request.MOVE, xmlData);
         if(join != null){
             join.sendRequest(request);
         }
         else{
             host.sendData(request);
         }
     }
     
     private void moveExcute(int abscissa, int ordinate) throws IOException {
        if(game!=null){
            game.play(abscissa, ordinate);
        }
    }
     
     public void gameOver(boolean isWin) throws IOException{
         if(isWin){
            Request request = new Request(Request.YOULOST);
            if(join != null){
                join.sendRequest(request);
            }
            else{
                 host.sendData(request);
            }
            JOptionPane.showMessageDialog(this, "You won");
         }
         
         game.dispose();
         this.setVisible(true);
     }
     
    //Override method of the WindowListener
    @Override
    public void windowClosing(WindowEvent e) {
        //When you close this window
        try{
            //Try to close the host if you are hosting and this room have a client in side.
            host.close();
        } catch (Exception ex) {    
        }
        try {
            //Try to close the room if you are the hosting and close server
            parent.close();
            server.close();
        } catch (Exception ex) {   
        } 
        try{
            //Try to out the room if you are client.
            join.exit();
            parent.exitRoom();
        } catch (Exception ex) {  
        }
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) { 
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField player1Name;
    private javax.swing.JFormattedTextField player2Name;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    
}
