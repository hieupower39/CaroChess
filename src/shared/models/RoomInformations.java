/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import java.io.IOException;
import java.io.Serializable;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import shared.statics.Convertor;

/**
 *
 * @author hieup
 */
public class RoomInformations implements Serializable{
    private final String roomID;
    private final String host;
    private final String status;
    private final String size;
    private final String mode;
    private final String undo;
    private final String note;

    public RoomInformations(String xmlData) throws ParserConfigurationException, SAXException, IOException {
        this.roomID = Convertor.getValueFromXMLTag(xmlData, "id");
        this.host = Convertor.getValueFromXMLTag(xmlData, "host");
        this.status = Convertor.getValueFromXMLTag(xmlData, "status");
        this.size = Convertor.getValueFromXMLTag(xmlData, "size");
        this.mode = Convertor.getValueFromXMLTag(xmlData, "mode");
        this.undo = Convertor.getValueFromXMLTag(xmlData, "undo");
        this.note = Convertor.getValueFromXMLTag(xmlData, "note");
    }

    public String getRoomID() {
        return roomID;
    }

    public String getHost() {
        return host;
    }

    public String getStatus() {
        return status;
    }

    public String getSize() {
        return size;
    }

    public String getMode() {
        return mode;
    }

    public String getUndo() {
        return undo;
    }

    public String getNote() {
        return note;
    }
    
    
}
