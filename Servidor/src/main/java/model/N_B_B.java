/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class N_B_B {
    
    private String id;
    
    private N_B_B lt;
    private N_B_B rt;
    
    private Object data;

    public N_B_B(String id){
        this.id = id;
    }
    
    public N_B_B(String id, Object data){
        this.id = id;
        this.data = data;
    }
    
    public Object getData() {return data;}
    public String getId() {return id;}
    public N_B_B getRt() {return rt;}
    public N_B_B getLt() {return lt;}
    
    public void setData(Object data) {this.data = data;}
    public void setRt(N_B_B rt) {this.rt = rt;}
    public void setLt(N_B_B lt) {this.lt = lt;}
    public void setId(String id) {this.id = id;}
    
    
}
