/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class A_AVL{
    private String id;
    private N_AVL root;
    private int size;
    private L_d_e elements;
    
    
    public A_AVL(String id){
        this.id=id;
        this.root=null;
        this.size=0;
        this.elements=new L_d_e(id);
    }
    
    public boolean insertar(N_AVL node) {
        N_d_e nl = new N_d_e(node.getId(),node.getPlatillo());
        this.elements.add_N(nl);
        if(this.root==null){
            this.root = node;
            this.size++;
            return true;
        }else{
            N_AVL act = this.root;
            
            while(true){
                if(node.getId().compareTo(act.getId()) < 0){
                    
                    if(act.getLT() == null){
                        act.setLT(node);
                        return true;
                    }else{
                        act = act.getLT();
                    }
                }else if(node.getId().compareTo(act.getId()) > 0){
                    
                    if(act.getRT() == null){
                        act.setRT(node);
                        return true;
                    }else{
                        act = act.getRT();
                    }  
                    
                }else{
                    return false;
                }
            }
        
        }
    }
    
    private N_AVL rotateLeft(N_AVL node) {
        N_AVL x = node.getRT();
        N_AVL T2 = x.getLT();

        x.setLT(node);
        node.setRT(T2);

        node.setAltura(1 + Math.max(node.getLT().getAltura(), node.getRT().getAltura()));
        x.setAltura(1 + Math.max(x.getLT().getAltura(), x.getRT().getAltura()));

        node.setBalance(1 + node.getLT().getBlance() + node.getRT().getBlance());
        x.setBalance(1 + x.getLT().getBlance() + x.getRT().getBlance());

        return x;
    }

    private N_AVL rotateRight(N_AVL node) {
        N_AVL x = node.getLT();
        N_AVL T2 = x.getRT();

        x.setRT(node);
        node.setLT(T2);

        node.setAltura(1 + Math.max(node.getLT().getAltura(), node.getRT().getAltura()));
        x.setAltura(1 + Math.max(x.getLT().getAltura(), x.getRT().getAltura()));

        node.setBalance(1 + node.getLT().getBlance() + node.getRT().getBlance());
        x.setBalance(1 + x.getLT().getBlance() + x.getRT().getBlance());

        return x;
    }
    
    public L_d_e getElements(){return this.elements;}
    public String getId(){return this.id;}
}
