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
    
    public void insertLevelOrder(String name, String managerType, String department) {
        Node newNode = new Node(name, managerType, department);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left == null) {
                temp.left = newNode;
                break;
            } else {
                queue.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = newNode;
                break;
            } else {
                queue.add(temp.right);
            }
        }
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp + " ");
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        System.out.println();
    }

    public int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public int getNodeCount(Node node) {
        if (node == null) return 0;
        return 1 + getNodeCount(node.left) + getNodeCount(node.right);
    }
}
