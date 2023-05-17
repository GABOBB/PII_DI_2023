/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *esta clase funciona para guardar los usuarios adminoistradores
 * @author Gabriel
 */
public class Admin {
    String user;
    String pssw;
// Crea un nuevo objeto Admin con el usuario y la contraseña proporcionados.
    public Admin(String user, String pssw) {
        this.user = user;
        this.pssw = pssw;
    }

    public String getUser() { //Devuelve el nombre de usuario del administrador
        return user;
    }

    public void setUser(String user) { // Establece el nombre de usuario del administrador.
        this.user = user;
    }

    public String getPssw() { // Devuelve la contraseña del administrador.
        return pssw;
    }

    public void setPssw(String pssw) { // Establece la contraseña del administrador.
        this.pssw = pssw;
    }

    /*    @Override
    public int hashCode() {
    int hash = 5;
    return hash;
    }*/

    @Override
    public boolean equals(Object obj) {
        //Compara este objeto Admin con otro objeto para determinar si son iguales.
        //Dos objetos Admin son iguales si tienen el mismo nombre de usuario.
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        return Objects.equals(this.user, other.user);
    }
    
    
}
