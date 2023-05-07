package model;

/**
 *
 * @author Gabriel
 */
public class N_d_e{
    private N_d_e N;
    private N_d_e P;
    private String id;
    
    private Object data;

    public N_d_e(String id){
        this.id = id;
    }

    public N_d_e(String id, Object data){
        this.id = id;
        this.data = data;
    }

    public N_d_e getP(){return this.P;}

    public N_d_e getN(){return this.N;}
    
    public String getId(){return this.id;}

    public Object getData(){return this.data;}

    public void setN(N_d_e N){this.N = N;}

    public void setP(N_d_e N){this.P = N;}
}