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
import model.A_AVL;
import model.A_B_B;
import model.Admin;
import model.Cliente;
import model.Cola;
import model.N_B_B;

/**
 *
 * @author Gabriel
 */
public class Servidor {
    A_B_B admns;
    A_B_B clnts;
    A_AVL platillos;
    Cola pedidos;
    
    Object usruario;
    
    public Servidor(){
        try{
            this.admns = new A_B_B("admns");
            this.clnts = new A_B_B("clnts");
            this.platillos= new A_AVL("platillos");
            this.pedidos = new Cola(25);
            
            load_Platillos();
            load_Admins();
            load_Clnts();
            this.on_server();
        }catch(Exception e){}        
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
                    
                    String user = element.getElementsByTagName("user").item(0).getTextContent();
                    String pssw = element.getElementsByTagName("password").item(0).getTextContent();
                    
                    Admin new_AD = new Admin(user,pssw);
                    
                    N_B_B new_n = new N_B_B(user,new_AD,element);
                    
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
                    
                    String user = element.getElementsByTagName("user").item(0).getTextContent();
                    String pssw = element.getElementsByTagName("password").item(0).getTextContent();
                    
                    Cliente new_CL = new Cliente(user,pssw);
                    
                    N_B_B new_n = new N_B_B(user,new_CL,element);
                    
                    this.clnts.add_N(new_n);
                }
            }
            
        }catch (Exception e) {
            System.out.println(e);
            return;
        }
    } 
    
    public void load_Platillos(){
        String JSON = leer("src/main/java/com/mycompany/servidor/Platillos.json");
        
        
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
                System.out.println("Client Connected");

                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String message = in.readUTF();
                System.out.println("i recive:" + message);
                if(message.equals("hola server")){
                    out.writeUTF("Hola Cliente");
                    
                    
                }else if(message.startsWith("Buscar")){
                    String u=message.split(" ")[1];
                    String p=message.split(" ")[2];
                    if(this.buscarAdmin(u,p)){
                        this.usruario = this.admns.srch_id(u);
                        out.writeUTF("Admin");
                    }else if(this.buscarCliente(u,p)){
                        this.usruario = this.clnts.srch_id(u);
                        out.writeUTF("Cliente");
                    }else{
                        out.writeUTF("No encontrado");
                        
                    }
                }else if(message.equals("lista de administradores")){
                    out.writeUTF(this.admns.get_eA());
                }else if(message.startsWith("Actualizar_Administradores")){
                    String total[] = message.split("###");
                    String temp[] = new String[total.length - 1];
                    System.arraycopy(total, 1, temp, 0, temp.length);
                    this.admns.clear();
                    for(String aux : temp){
                        String u = aux.split(";")[0];
                        String p = aux.split(";")[1];
                        Admin a = new Admin(u,p);
                        
                        N_B_B N = new N_B_B(u,a);
                        this.admns.add_N(N);
                        
                    }
                    this.admns.getList().prntL();
                    out.writeUTF(this.admns.get_eA());
                }else if(message.equals("get_pedidos")){
                    String r = this.pedidos.get_elemts();
                    
                    if(r != null){
                        out.writeUTF(r);
                    }
                }else if(message.startsWith("nuevo_pedido")){
                    String[] platillos = message.split("###");
                    for(String platillo : platillos){
                        
                        String p = platillo.split(";")[0];
                        
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
            Admin adm = (Admin) temp.getData();
            if(adm.getP().equals(password)){
                return true;
            }else{
                return false;
            }
            
        }
    }
    
    public boolean buscarCliente(String user, String password){
        N_B_B temp = this.clnts.srch_id(user);
        if(temp == null){
            return false;
        }else{
            Cliente clt = (Cliente) temp.getData();
            if(clt.getP().equals(password)){
                return true;
            }else{
                return false;
            }
            
        }
    }
}
