/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.servidor;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import model.A_B_B;
import model.N_B_B;

/**
 *
 * @author Gabriel
 */
public class Servidor {
    A_B_B admns;
    A_B_B clnts;
    public Servidor(){
        try {
            this.admns = new A_B_B("admns");
            this.clnts = new A_B_B("clnts");
            load_Admins();
            load_Clnts();
        } catch (Exception e) {
        
        }
        try{
        
            this.on_server();
       
        }catch(IOException e){
            System.err.println(e);
        }
        
    }
    
    private void load_Admins(){
        try{
            File admins= new File("src/main/java/com/mycompany/servidor/Admins.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB= dBF.newDocumentBuilder();
            Document doc = dB.parse(admins);
            doc.getDocumentElement().normalize();
            
            NodeList nL = doc.getElementsByTagName("admin");
            for(int i =0; i<nL.getLength();i++){
                Node node = nL.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element =(Element)node;
                    N_B_B new_n = new N_B_B(element.getElementsByTagName("user").item(0).getTextContent(),node);
                    this.admns.add_N(new_n);
                }
            }
            
        }catch (Exception e) {
            System.out.println(e);
            return;
        }
    } 
    
    private void load_Clnts(){
        try{
            File clientes = new File("src/main/java/com/mycompany/servidor/Clientes.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB= dBF.newDocumentBuilder();
            Document doc = dB.parse(clientes);
            doc.getDocumentElement().normalize();
            
            NodeList nL = doc.getElementsByTagName("client");
            for(int i =0; i<nL.getLength();i++){
                Node node = nL.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element =(Element)node;
                    N_B_B new_n = new N_B_B(element.getElementsByTagName("user").item(0).getTextContent(),node);
                    this.clnts.add_N(new_n);
                }
            }
            
        }catch (Exception e) {
            System.out.println(e);
            return;
        }
    } 
    
    public static void main(String[] args) {
        new Servidor();
    }
    
    private void on_server() throws IOException{
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream in;
        DataOutputStream out;
        final int PORT = 5000;
        final boolean FLAG = true;
        
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is On!");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client [1] Connected");

                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String message = in.readUTF();
                System.out.println("i recive:" + message);
                if(message.equals("hola server")){
                    out.writeUTF("Hola Cliente");
                }
                
                if(message.startsWith("Buscar")){
                    String user=message.split(" ")[1];
                    String password=message.split(" ")[2];
                    if(this.buscarAdmin(user,password)){
                        out.writeUTF("Admin");
                    }else if(this.buscarCliente(user,password)){
                        out.writeUTF("Cliente");
                    }else{
                        out.writeUTF("No encontrado");
                    }
                }
                clientSocket.close();
                System.out.println("Client [1] Disconnected");
            }
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    public boolean buscarAdmin(String user, String password){
        N_B_B temp = this.admns.srch_id(user);
        if(temp == null){
            return false;
        }else{
            Element element = (Element) temp.getData();
            if(password.equals(element.getElementsByTagName("password").item(0).getTextContent())){
                return true;
            }else{
                return false;
            }
            
        }
        /*try{
            File admins= new File("src/main/java/com/mycompany/servidor/Admins.xml");
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB= dBF.newDocumentBuilder();
            Document doc = dB.parse(admins);
            doc.getDocumentElement().normalize();

            NodeList nL = doc.getElementsByTagName("admin");
            for(int i =0; i<nL.getLength();i++){
            Node node = nL.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element =(Element)node;
                    if(user.equals(element.getElementsByTagName("user").item(0).getTextContent())){
                        if(password.equals(element.getElementsByTagName("password").item(0).getTextContent())){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
        System.out.println(e);
        return false;
        }*/
    }
    
    public boolean buscarCliente(String user, String password){
        
        N_B_B temp = this.clnts.srch_id(user);
        if(temp == null){
            return false;
        }else{
            Element element = (Element) temp.getData();
            if(password.equals(element.getElementsByTagName("password").item(0).getTextContent())){
                return true;
            }else{
                return false;
            }
            
        }
        
        /*try {
        File clientes= new File("src/main/java/com/mycompany/servidor/Clientes.xml");
        DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder dB= dBF.newDocumentBuilder();
        Document doc = dB.parse(clientes);
        doc.getDocumentElement().normalize();
        
        NodeList nL = doc.getElementsByTagName("client");
        for(int i =0; i<nL.getLength();i++){
        Node node = nL.item(i);
        if(node.getNodeType()==Node.ELEMENT_NODE){
        Element element =(Element)node;
        if(user.equals(element.getElementsByTagName("user").item(0).getTextContent())){
        if(password.equals(element.getElementsByTagName("password").item(0).getTextContent())){
        return true;
        }else{
        return false;
        }
        }
        }
        }
        return false;
        } catch (Exception e) {
        System.out.println(e);
        return false;
        }*/
    }
}
