package CA_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree to represent the employee hierarchy.
 */
public class BinaryTree {
    
    public static class Node {
        String name;
        String managerType;
        String department;
        Node left, right;

        public Node(String name, String managerType, String department) {
            this.name = name;
            this.managerType = managerType;
            this.department = department;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return String.format("[%s | %s | %s]", name, managerType, department);
        }
    }

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() { return root; }
    
    // Base structure for now, insertion will be in the next commit
}
