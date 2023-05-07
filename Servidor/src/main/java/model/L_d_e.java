package model;

public class L_d_e{
    private N_d_e first;
    private N_d_e last;
    private int size;
    private String id;

    public L_d_e(String id){
        this.id = id;
    }

    public void add_N(N_d_e NN){
        if(this.first == null){
            this.first = NN;
            this.last = NN;
            this.first.setN(this.last);
            this.last.setP(this.first);
            this.size++;
        }else if(this.first == this.last){
            this.last.setN(NN);
            NN.setP(this.last);
            this.last = NN;
            this.first.setN(this.last);
            this.last.setP(this.first);
            this.size++;
        }else{
            this.last.setN(NN);
            NN.setP(this.last);
            this.last = NN;
            this.first.setP(this.last);
            this.last.setN(this.first);
            this.size++;
        }
    }
    
    public void remove_N(N_d_e NN){
        if(this.first == NN){
            this.first = NN.getN();
            this.first.setP(this.last);
            this.last.setN(this.first);
            this.size--;
        }else if(this.last == NN){
            this.last = NN.getP();
            this.last.setN(this.first);
            this.first.setP(this.last);
            this.size--;
        }else{
            NN.getP().setN(NN.getN());
            NN.getN().setP(NN.getP());
            this.size--;
        }
    }

    public N_d_e search(String id) {
        N_d_e current = this.first;
        while (current != null && !current.getId().equals(id)) {
            current = current.getN();
        }
        return current;
    }
    

    public N_d_e getFirst(){
        return this.first;
    }

    public N_d_e getLast(){
        return this.last;
    }

    public int getSize(){
        return this.size;
    }

    public String getId(){
        return this.id;
    }
}
