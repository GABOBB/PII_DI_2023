/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *  esta clase funciona para almacenar los platillos 
 * @author Gabriel
 */
public class Platillo {
    //Contiene información sobre el ID del usuario que lo creó, el ID del platillo,las calorías, el tiempo de preparación y el precio del platillo.
    private String id;
    private String id_user;
    private int calorias;
    private int tiempo;
    private int precio;
    
    public Platillo(String id_u, String id, int c, int t, int p){
        // Crea un nuevo objeto Platillo con el ID de usuario, ID de platillo, calorías, tiempo de preparación y precio proporcionados.
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

    @Override
    public int hashCode() { //Genera el código hash del objeto Platillo.
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) { 
        //Compara este objeto Platillo con otro objeto para determinar si son iguales.
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Platillo other = (Platillo) obj;
        if (this.calorias != other.calorias) {
            return false;
        }
        if (this.tiempo != other.tiempo) {
            return false;
        }
        if (this.precio != other.precio) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    public void setIdu(String idu){this.id_user = idu;}// Establece el ID del usuario que creó el platillo
    public void setId(String id){this.id = id;}//Establece el ID del platillo
    public void setTiempo(int tiempo){this.tiempo = tiempo;}//Establece el tiempo de preparación del platillo.
    public void setPrecio(int precio){this.precio = precio;}//Establece el precio del platillo.
    public void setCalorias(int calorias){this.calorias = calorias;}//Establece las calorías del platillo.
    
    public String getIdu(){return this.id_user;}//Devuelve el ID del usuario que creó el platillo.
    public String getId(){return this.id;}//Devuelve el ID del platillo.
    public int getTiempo(){return this.tiempo;}//Devuelve el tiempo de preparación del platillo.
    public int getPrecio(){return this.precio;}//Devuelve el precio del platillo.
    public int getCalorias(){return this.calorias;}//Devuelve las calorías del platillo.
  
}
