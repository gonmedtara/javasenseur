/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snifc;

import java.util.logging.Level;
import java.util.logging.Logger;
import snifc.sensor.IOPortsIfc;

/**
 *
 * @author mdeclercq
 */
public class Link implements LinkIfc {

    public static int LINK_NUMBER=0;
    private int id;
    /*2 packets and 2 ports are created */
    private PacketIfc fromPacket;
    private PacketIfc toPacket;
    private IOPortsIfc fromPort;
    private IOPortsIfc toPort;

    
    
    Link(IOPortsIfc fromPort , IOPortsIfc toPort){
        try {

            this.toPort = toPort;
            this.fromPort = fromPort;
            LINK_NUMBER++;
            this.id = LINK_NUMBER;
            fromPort.addLink(this);
            toPort.addLink(this);
        } catch (Exception ex) {
            Logger.getLogger(Link.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void transmit(PacketIfc p, IOPortsIfc from) {
        System.out.println("Transmission en cours");
        System.out.println("Paquet: "+p);
        System.out.println("Emetteur: "+from);
        Packet.NB_TRANS++;
        if ( this.fromPort == from){
            this.fromPacket = p;
        }else if(this.toPort== from){
            this.toPacket = p;    
        }        
     
    }

    public PacketIfc getPendingPacket(IOPortsIfc s) {
       PacketIfc returnPacket=null;
        if ( this.fromPort == s){
         returnPacket= this.fromPacket;  
       }else if (this.toPort ==s){
         returnPacket= this.toPacket;  
       }
       return returnPacket;
    }

    public int getId() {
        
        return this.id;
        
        
    }

}
