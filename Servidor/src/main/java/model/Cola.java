/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class Cola {
    private L_d_e elements;
    private int max;
    
    public Cola(int m){
        this.elements = new L_d_e("cola de platillos");
        this.max = m;
    }
    
    public boolean push(String i,Platillo p){    
        if(this.elements.getSize() < this.max){
            this.elements.add_last(new N_d_e(i,p));
            System.out.println("si se pudo agregar");
            return true;
        } 
        System.out.println("no se pudo agregar");
        return false;
    }
    
    public N_d_e peek(){
        return this.elements.getFirst();
    }
    
    public N_d_e pop(){
        if(this.elements.getSize()>0){
            N_d_e temp = this.elements.getFirst();
            this.elements.remove_first();
            return temp;
        }
        return null;
    }
    
    public String get_elemts(){
        String total = "###";
        N_d_e N_act = this.elements.getFirst();
        while(N_act != null){
            String temp = "";
            temp += N_act.getId() + ";";
            Platillo p = (Platillo) N_act.getData();
            temp += p.getId() + ";";
            temp += p.getCalorias() + ";";
            temp += p.getTiempo() + ";";
            temp += p.getPrecio();
            total += "###" + temp; 
            
            N_act = N_act.getN();
        } 
        if(total.equals("###")){
            return null;
        }else{
            return total;
        }
    }
    
    public L_d_e get_list(){return this.elements;}
    public int getSize(){return this.elements.getSize();}
}
