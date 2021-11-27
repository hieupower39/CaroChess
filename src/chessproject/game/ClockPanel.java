/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessproject.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieup
 */
public class ClockPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClockPanel
     */
    private int time;
    private String name;
    private Thread thread;
    private CaroFrame parent;
    boolean checkTime;
    
    public ClockPanel(int time, String name, CaroFrame parent, boolean checkTime) {
        initComponents();
        this.time = time;
        this.name = name;
        this.nameLabel.setText(name); 
        this.parent = parent;
        this.checkTime = checkTime;
        setClock(time);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minuteLabel = new javax.swing.JLabel();
        secondLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();

        minuteLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        minuteLabel.setText("00");

        secondLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        secondLabel.setText("00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel4.setText(":");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nameLabel.setText("NAME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(minuteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(secondLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minuteLabel)
                    .addComponent(jLabel4)
                    .addComponent(secondLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel minuteLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel secondLabel;
    // End of variables declaration//GEN-END:variables

    public void setClock(int time) {
        int second = time;
        int minute = second/60;
        second = second%60;
        setMinute(minute);
        setSecond(second);
    }
    private void setMinute(int minute){
        String minuteText="";
        if(minute<10){
            minuteText = "0"+minuteText;
        }
        minuteText += minute;
        this.minuteLabel.setText(minuteText);
    }
    
    private void setSecond(int second){
        String secondText="";
        if(second<10){
            secondText = "0"+secondText;
        }
        secondText += second;
        this.secondLabel.setText(secondText);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
    
    
    
    public void start(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(time>0){
                    try {
                        Thread.sleep(1000);
                        time--;
                        setClock(time);
                    } catch (InterruptedException ex) {
                    }
                }
                if(checkTime){
                    try {
                        parent.outTime();
                    } catch (IOException ex) {
                        
                    }
                }
            }
        });
        thread.start();
    }
    
    public void stop(){
        try{
           thread.stop();
        }catch(Exception e){
            
        }
    }
}
