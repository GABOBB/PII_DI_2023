/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gabriel
 */
public class A_AVL extends A_B_B{
    
    
    private N_AVL rotateLeft(N_AVL node) {
        N_AVL x = node.getRt();
        N_AVL T2 = x.getLt();

        x.setLt(node);
        node.setRt(T2);

        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        node.size = 1 + size(node.left) + size(node.right);
        x.size = 1 + size(x.left) + size(x.right);

        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        Node T2 = x.right;

        x.right = node;
        node.left = T2;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        node.size = 1 + size(node.left) + size(node.right);
        x.size = 1 + size(x.left) + size(x.right);

        return x;
    }
}
