package model;

import com.sun.nio.sctp.SctpChannel;


public class L_d_e {
    private N_d_e first;
    private N_d_e last;
    private int size;
    private String id;

    public L_d_e(String id) {
        this.id = id;
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public void prntL(){
        N_d_e act = this.getFirst();
        while(act != null){
            System.out.println(act.getId() + " - ");
            act = act.getN();
        }
    
    }
    public void add_N(N_d_e newNode) {
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setN(newNode);
            newNode.setP(last);
            last = newNode;
        }
        size++;
    }
    
    public void add_last(N_d_e N){
        if(this.last != null){
            this.last.setN(N);
            N.setP(this.last);
            this.last = N;
        }else{
            this.first = N;
            this.last = N;
            this.first.setN(N);
            this.last.setP(N);         
        }
        this.size++;
    }
    
    public void remove_first(){
        N_d_e act = this.first.getN();
        this.first.setN(null);
        act.setP(null);
        this.first = act;
        this.size--;
    }

    public void remove_N(N_d_e nodeToRemove) {
        if (nodeToRemove == first) {
            first = first.getN();
            if (first != null) {
                first.setP(null);
            } else {
                last = null;
            }
        } else if (nodeToRemove == last) {
            last = last.getP();
            if (last != null) {
                last.setN(null);
            } else {
                first = null;
            }
        } else {
            N_d_e prevNode = nodeToRemove.getP();
            N_d_e nextNode = nodeToRemove.getN();
            prevNode.setN(nextNode);
            nextNode.setP(prevNode);
        }
        size--;
    }

    public N_d_e search(String id) {
        N_d_e current = first;
        while (current != null && !current.getId().equals(id)) {
            current = current.getN();
        }
        return current;
    }

    public N_d_e getFirst() {
        return first;
    }

    public N_d_e getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }
    
}