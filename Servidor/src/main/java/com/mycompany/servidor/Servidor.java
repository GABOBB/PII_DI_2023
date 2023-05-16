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
import model.L_d_e;
import model.N_AVL;
import model.N_B_B;
import model.N_d_e;
import model.Platillo;

/**
 *
 * @author Gabriel
 */
public class Servidor {
    A_B_B admns;
    A_B_B clnts;
    A_AVL platillos;
    Cola pedidos;
    
    Cliente usruario;
    
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
        //String JSON = leer("src/main/java/com/mycompany/servidor/Platillos.json");
        
        
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
                        out.writeUTF("Admin");
                    }else if(this.buscarCliente(u,p)){
                        this.usruario = (Cliente) this.clnts.srch_id(u).getData();
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
                    if(this.pedidos.getSize()>0){
                        String r = this.pedidos.get_elemts();
                        System.out.println(r);
                        out.writeUTF(r);
                    }else{
                        out.writeUTF("");
                    }
                }else if(message.startsWith("nuevo_pedido")){
                    String[] platillos = message.split("###");
                    if(platillos.length>=1){
                        for(int I = 1;I<platillos.length; I++){
                            String P = platillos[I];
                            Platillo NEW_P = new Platillo(this.usruario.getU(),
                                                          P.split(";")[0],
                                                          Integer.parseInt(P.split(";")[1]),
                                                          Integer.parseInt(P.split(";")[2]),
                                                          Integer.parseInt(P.split(";")[3]));
                            N_d_e newN = new N_d_e(NEW_P.getId(),NEW_P);
                            this.usruario.get_list().add_N(newN); 
                            this.pedidos.push(P.split(";")[0], NEW_P);
                          
                        }
                        System.out.println("@@@");
                        System.out.println(this.pedidos.get_elemts());
                        System.out.println("@@@");
                    }
                }else if(message.startsWith("actualizar platillos")){
                    this.platillos = new A_AVL(this.platillos.getId());
                    String mesage[] = message.split("###");
                    String platillos[] = new String[mesage.length-1];
                    System.arraycopy(mesage,1,platillos,0,platillos.length);
                    for(String platillo : platillos){
                        String info[] = platillo.split(";");
                        if(info.length == 4){
                            Platillo P = new Platillo(info[0],
                                                  Integer.parseInt(info[1]),
                                                  Integer.parseInt(info[2]),
                                                  Integer.parseInt(info[3]));
                            N_AVL new_nodo = new N_AVL(info[0],P);
                            this.platillos.insertar(new_nodo);
                        }
                    }
                }else if(message.equals("get_platillos")){
                    L_d_e L = this.platillos.getElements();
                    N_d_e n = L.getFirst();
                    String total = "";
                    while(n != null){
                        Platillo p = (Platillo) n.getData();
                        String temp = "###";
                        temp += p.getId() + ";"; 
                        temp += p.getCalorias() + ";";
                        temp += p.getTiempo() + ";";
                        temp += p.getPrecio();
                        total += temp;
                        System.out.println(total);
                        n = n.getN();
                    }
                    if(total != null){
                        out.writeUTF(total);
                    }else{
                        out.writeUTF("");
                    }
                }else if(message.equals("get_activos")){
                    N_d_e act = this.pedidos.get_list().getFirst();
                    String total = "";
                    while(act != null){
                        Platillo P = (Platillo) act.getData();
                        String user = P.getUID();
                        if(user.equals(this.usruario.getU())){
                            String temp = "###";
                            temp += P.getId() + ";"; 
                            temp += P.getCalorias() + ";";
                            temp += P.getTiempo() + ";";
                            temp += P.getPrecio();
                            total += temp;
                        }
                        act = act.getN();
                    }
                    out.writeUTF(total);
                }else if(message.equals("get_historial")){
                    L_d_e list = this.usruario.get_list();
                    N_d_e act = list.getFirst();
                    String total = "";
                    while(act != null){
                        Platillo P = (Platillo) act.getData();
                            String temp = "###";
                            temp += P.getId() + ";"; 
                            temp += P.getCalorias() + ";";
                            temp += P.getTiempo() + ";";
                            temp += P.getPrecio();
                            total += temp;
                        
                        act = act.getN();
                    }
                    out.writeUTF(total);
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
