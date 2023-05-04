/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class A_B_B {
    private String id;
    private N_B_B first;
    private int elements;
    
    public A_B_B(String id){
        this.id = id;
        this.first = null;
        this.elements = 0;
    }
    
    public boolean add_N(N_B_B new_N){
        if(this.first==null){
            this.first = new_N;
            this.elements++;
            return true;
        }else{
            N_B_B act = this.first;
            
            while(true){
                if(new_N.getId().compareTo(act.getId()) < 0){
                    
                    if(act.getLt() == null){
                        act.setLt(new_N);
                        return true;
                    }else{
                        act = act.getLt();
                    }
                }else if(new_N.getId().compareTo(act.getId()) > 0){
                    
                    if(act.getRt() == null){
                        act.setRt(new_N);
                        return true;
                    }else{
                        act = act.getRt();
                    }  
                    
                }else{
                    return false;
                }
            }
        
        }
    }
    
    public N_B_B srch_id(String id){
        if(this.first == null){
            
            return this.first;
            
        }else{
            N_B_B act = this.first;
            while(true){
                if(act == null){
                    
                    return act;
                    
                }else if(id.compareTo(act.getId()) < 0){
                    
                    act = act.getLt();
                    
                }else if(id.compareTo(act.getId()) > 0){
                
                    act = act.getRt();
                    
                }else{
                
                    return act;
                    
                }
            }
            
        }
    
    }
    
   public boolean remove_N(String id) {
    if (this.first == null) {
        return false;
    } else {
        N_B_B pdr = null;
        N_B_B act = this.first;
        boolean isLt = false;
        while (true) {
            if (id.compareTo(act.getId()) < 0) {
                pdr = act;
                act = act.getLt();
                isLt = true;
            } else if (id.compareTo(act.getId()) > 0) {
                pdr = act;
                act = act.getRt();
                isLt = false;
            } else {
                if (act.getLt() == null && act.getRt() == null) {
                    if (pdr == null) {
                        this.first = null;
                    } else {
                        if (isLt) {
                            pdr.setLt(null);
                        } else {
                            pdr.setRt(null);
                        }
                    }
                } else if (act.getLt() == null) {
                    if (pdr == null) {
                        this.first = act.getRt();
                    } else {
                        if (isLt) {
                            pdr.setLt(act.getRt());
                        } else {
                            pdr.setRt(act.getRt());
                        }
                    }
                } else if (act.getRt() == null) {
                    if (pdr == null) {
                        this.first = act.getLt();
                    } else {
                        if (isLt) {
                            pdr.setLt(act.getLt());
                        } else {
                            pdr.setRt(act.getLt());
                        }
                    }
                } else {
                    N_B_B minParent = act;
                    N_B_B min = act.getRt();
                    while (min.getLt() != null) {
                        minParent = min;
                        min = min.getLt();
                    }
                    act.setId(min.getId());
                    if (minParent == act) {
                        act.setRt(min.getRt());
                    } else {
                        minParent.setLt(min.getRt());
                    }
                }
                this.elements--;
                return true;
            }
            if (act == null) {
                return false;
            }
        }
    }
}

}
