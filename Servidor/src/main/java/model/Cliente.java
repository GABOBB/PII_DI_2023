/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author Gabriel
 */
public class Cliente extends Admin {
    private int num_pedidos;
    private L_d_e LDP;

    public Cliente(String user, String pssw){
        super(user, pssw);
        this.LDP = new L_d_e("pedidos");
    }
    
    public void load_L(){
    
    }
    
    public L_d_e get_list(){return this.LDP;}
}
