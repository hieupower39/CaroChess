/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import java.io.Serializable;

/**
    * @author hieup
    * This class stores the information of the request.
 */
public class Request implements Serializable{    
    private final int request; //The request type
    private String data; //The string data of the request
    public final static int HOST = 1000;
    public final static int JOIN = 1001;
    public final static int CLOSEROOM = 1002;
    public final static int CLOSE = 1003;
    public final static int EXIT = 1004;
    public final static int REFRESH = 1005;
    public final static int SETNAME = 1006;
    public final static int STARTGAME = 1007;
    public final static int MOVE = 1008;
    public final static int YOULOST = 1009;
    //Constructor method
    public Request(int request) {
        this.request = request;
    }

    public Request(int request, String data) {
        this.request = request;
        this.data = data;
    }
    
    //Get method   

    public int getRequest() {
        return request;
    }

    public String getData() {
        return data;
    }
    
    
}
