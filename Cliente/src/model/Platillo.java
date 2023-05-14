/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class Platillo {
    private String id;
    private String id_user;
    private int calorias;
    private int tiempo;
    private int precio;
    
    public Platillo(String id_u, String id, int c, int t, int p){
        this.id_user = id_u;
        this.id = id;
        this.calorias = c;
        this.tiempo = t;
        this.precio = p;
    }
    
    public Platillo( String id, int c, int t, int p){
        this.id = id;
        this.calorias = c;
        this.tiempo = t;
        this.precio = p;
    }
    public void setIdu(String idu){this.id_user = idu;}
    public void setId(String id){this.id = id;}
    public void setTiempo(int tiempo){this.tiempo = tiempo;}
    public void setPrecio(int precio){this.precio = precio;}
    public void setCalorias(int calorias){this.calorias = calorias;}
    
    public String getIdu(){return this.id_user;}
    public String getId(){return this.id;}
    public int getTiempo(){return this.tiempo;}
    public int getPrecio(){return this.precio;}
    public int getCalorias(){return this.calorias;}
    
    
    

    
}
