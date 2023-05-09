package model;


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