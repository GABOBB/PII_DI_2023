/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class Arduino {
    private SerialPort ardn_prt;
    
    private OutputStream outP;
    private InputStream inP;
    
    private boolean reading;
        
    SerialPort[] AvailablePorts = SerialPort.getCommPorts();
    
    public Arduino(){
        this.ardn_prt = SerialPort.getCommPort("COM6");
        this.ardn_prt.setBaudRate(9600);

        if(this.ardn_prt.openPort(1000)){
            System.out.println("el puerto se abrio correctamente :)");
            this.reading = true;
        }else{
            System.err.println("el puerto no se pudo abrir :(");
            return;
        }

        this.inP = this.ardn_prt.getInputStream();
        this.outP = this.ardn_prt.getOutputStream();
        
    }
    
    public void close(){
        this.ardn_prt.closePort();
        if(!this.ardn_prt.isOpen()){
            System.out.println("se logro cerrar el puerto correctamente");
        }
    }
    
    public void send_s(String mensaje) {
        try {
            this.outP.write(mensaje.getBytes());//envia un string de manera serail
            this.outP.flush();//limpia la salida 
            System.out.println("Mensaje enviado: " + mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}