/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class N_AVL {
    private String id;
    private N_AVL RT;
    private N_AVL LT;
    private int altura;
    private Platillo platillo;
    
    public N_AVL(String id){
        this.id= id;
    } 

    public Platillo getPlatillo(){return this.platillo;}
    public String getId() {return id;}
    public N_AVL getRt() {return RT;}
    public N_AVL getLT() {return LT;}
    public int getAltura() {return altura;}
    
    public void setPlatillo(Platillo p){this.platillo = p;}
    public void setId(String id) {this.id = id;}
    public void setRt(N_AVL RT) {this.RT = RT;}
    public void setLT(N_AVL LT) {this.LT = LT;}
    public void setAltura(int height) {this.altura = height;}
    
    
}
