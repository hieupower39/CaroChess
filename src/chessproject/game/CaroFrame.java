/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessproject.game;

import chessproject.client.WaitRoomGUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author hieup
 */
public class CaroFrame extends javax.swing.JFrame implements WindowListener{

    /**
     * Creates new form CaroFrame
     */
    private static final String SECOND_MOVE = "art/tickO.png";
    private static final String FIRST_MOVE = "art/tickX.png";
    private boolean isTurn;    
    private static final int AREA = 16;
    private WaitRoomGUI parent;
    private ArrayList<ArrayList<CaroTile>> tiles;
    private String myTick, opponentTick;
    public CaroFrame(WaitRoomGUI parent,boolean isTurn) {
        initComponents();
        initCaroTile();
        this.addWindowListener(this);
        this.parent = parent;
        this.isTurn = isTurn;
        if(isTurn){
            this.myTick = FIRST_MOVE;
            this.opponentTick = SECOND_MOVE;
        }
        else{
            this.myTick = SECOND_MOVE;
            this.opponentTick = FIRST_MOVE;
        } 
            
    }
    
    public CaroFrame() {
        initComponents();
        initCaroTile();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        caroPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        caroPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        caroPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        caroPanel.setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(caroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(caroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CaroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaroFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel caroPanel;
    // End of variables declaration//GEN-END:variables
    
    private void movePlay(CaroTile tile) throws IOException{
        if(!isTurn){
            JOptionPane.showMessageDialog(this, "Đợi đối thủ đánh");
            return;
        }
        boolean isTick = tile.tick(myTick, true);
        this.setVisible(true);
        if(!isTick){
            try {
                parent.moveCheck(tile.getAbscissa(), tile.getOrdinate());
                isTurn = false;
                if(isWin(tile.getAbscissa(), tile.getOrdinate())){
                    parent.gameOver(true);
                }
            } catch (IOException ex) {
         
            }
        }
        
    }
    
    public void play(int abscissa, int ordinate) throws IOException{
        tiles.get(abscissa).get(ordinate).tick(opponentTick, false);
        isTurn = true;
        this.setVisible(true);
    }
    
    private boolean isWin(int abscissa, int ordinate){
        final CaroTile tile =  tiles.get(abscissa).get(ordinate);
        return isRow(abscissa, ordinate) || isColumn(abscissa, ordinate) ||
                isCrossLeft(abscissa, ordinate) || isCrossRight(abscissa, ordinate);
    }
    
    private void initCaroTile() {
        tiles = new ArrayList();
        caroPanel.setLayout(new GridLayout(16,16));
        for(int i = 0; i < AREA; i++){
            ArrayList<CaroTile> row =  new ArrayList();
            for(int j = 0; j<AREA; j++){
                final CaroTile tile = new CaroTile(i, j);
                row.add(tile);
                tile.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            movePlay(tile);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                    }
                });
                caroPanel.add(tile);
            }
            tiles.add(row);
            
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {
        parent.windowClosing(e);
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

    private boolean isColumn(int abscissa, int ordinate) {
        int count = 0;
        //left stack
        int tmp = abscissa;
        while (tmp>0){
            tmp--;
            if(!tiles.get(tmp).get(ordinate).isMy()){
                
                break;
            }
            count++;
        }
        //right stack
        tmp = abscissa;
        while(tmp<15){
            tmp++;
            if(!tiles.get(tmp).get(ordinate).isMy()){
                break;
            }
            count++;
        }
        return count >= 4;
    }

    private boolean isRow(int abscissa, int ordinate) {
        int count = 0;
        //left stack
        int tmp = ordinate;
        while (tmp>0){
            tmp--;
            if(!tiles.get(abscissa).get(tmp).isMy()){
                
                break;
            }
            count++;
        }
        //right stack
        tmp = ordinate;
        while(tmp<15){
            tmp++;
            if(!tiles.get(abscissa).get(tmp).isMy()){
                break;
            }
            count++;
        }
        return count >= 4;
    }

    private boolean isCrossLeft(int abscissa, int ordinate) {
        int count = 0;
        int x = abscissa;
        int y = ordinate;
        while(x>0&&y<15){
            x--;
            y++;
            if(!tiles.get(x).get(y).isMy()){
                break;
            }
            count++;
        }
        x = abscissa;
        y = ordinate;
        while(y>0&&x<15){
            x++;
            y--;
            if(!tiles.get(x).get(y).isMy()){
                break;
            }
            count++;
        }
        return count >= 4;
    }

    private boolean isCrossRight(int abscissa, int ordinate) {
        int count = 0;
        int x = abscissa;
        int y = ordinate;
        while(x>0&&y>0){
            x--;
            y--;
            if(!tiles.get(x).get(y).isMy()){
                break;
            }
            count++;
        }
        x = abscissa;
        y = ordinate;
        while(y<15&&x<15){
            x++;
            y++;
            if(!tiles.get(x).get(y).isMy()){
                break;
            }
            count++;
        }
        return count >= 4;
    }
}
