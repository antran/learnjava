package com.antt.dsa;

/**
 * Created by antt on 4/12/2017.
 */
public class BST {
    public static class BSTNode {
        public int value;
        public BSTNode left;
        public BSTNode right;
        public BSTNode parent;

        public BSTNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public String toString() {
            return "BSTNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public BSTNode root = null;
    public int size = 0;

    public void insert(int value) {
        root = insert1(root, value);
    }

    private BSTNode insert1(BSTNode start, int val) {
        if (start == null) {
            start = new BSTNode(val);
            return start;
        } else {
            if (start.value > val) {
                start.left = insert1(start.left, val);
            } else if (start.value < val) {
                start.right = insert1(start.right, val);
            }
            return start;
        }
    }

    private BSTNode insert(BSTNode start, int value, BSTNode parent) {
        if (start == null) {
            start = new BSTNode(value);
            start.parent = parent;
            this.size++;
            return start;
        } else if (start.value > value) {
            start.left = insert(start.left, value, start);
        } else if (start.value < value) {
            start.right = insert(start.right, value, start);
        }
        return start;
    }

    public BSTNode find(int value) {
        return find(root, value);
    }


    private BSTNode findMin(BSTNode start) {
        if (start == null) return  null;
        if (start.left == null) return start;
        return findMin(start.left);
    }

    public BSTNode find(BSTNode start, int value) {
        if (start == null) return null;
        if (start.value == value) return start;
        if (start.value > value) return find(start.left, value);
        return find(start.right, value);
    }

    public void deleteMin() {
        root = deleteMinv1(root);
    }

    public BSTNode deleteMinv1(BSTNode start) {
        if (start == null) return  null;
        if (start.left == null) {
            return start.right;
        } else {
            start.left = deleteMinv1(start.left);
            return start;
        }
    }

    public BSTNode deleteMin(BSTNode start) {
        if (start == null) return null;
        if (start.left != null)
            return deleteMin(start.left);

        BSTNode parent = start.parent;
        if (parent == null) {
            start = start.right;
        } else {
            parent.left = start.right;
        }
        start.parent = null;
        size--;
        return start;
    }

    public BSTNode deleteMax(BSTNode start) {
        return null;
    }

    public void delete2(int value) {
        root = delete2(root, value);
    }

    private BSTNode delete2(BSTNode node, int value) {
        if (node == null) return null;
        if (node.value > value) node.left = delete2(node.left, value);
        if (node.value < value) node.right = delete2(node.right, value);
        if (node.value == value) {
            if (node.left == null && node.right == null) return null;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BSTNode min = findMin(node.right);
            node.right = deleteMinv1(node.right);
            node.value = min.value;
        }
        return node;
    }

    public BSTNode delete(int value) {
//        BSTNode target = find(root, value);
        return deletev1(root,value, null);
    }

    private BSTNode deletev1(BSTNode start, int val, BSTNode parent) {
        if (start == null) return null;

        if (start.value > val) {
            return deletev1(start.left, val, start);
        } else if (start.value < val) {
            return deletev1(start.right, val, start);
        } else {
            if (start.left == null && start.right == null) {
                if (parent != null && parent.left == start) {
                    parent.left = null;
                } else if (parent != null && parent.right == start) {
                    parent.right = null;
                } else if (parent == null) {
                    root = null;
                }
            } else if (start.left == null || start.right == null) {
                BSTNode rs = start.left == null ? start.right : start.left;
                if (parent == null) {
                    root = rs;
                } else {
                    if (parent.left == start) parent.left = rs;
                    if (parent.right == start) parent.right = rs;
                }
            } else {
                // left and right is not null
                BSTNode min = findMin(start.right);
                BSTNode newS = new BSTNode(min.value);
                newS.left = start.left;
                newS.right = deleteMinv1(start.right);
                if (parent == null) {
                    root = newS;
                } else {
                    if (parent.left == start) parent.left = newS;
                    if (parent.right == start) parent.right = newS;
                }
            }
        }
        return start;
    }

    private void delete(BSTNode start) {
        // root, non root
        BSTNode parent = start.parent;

        if (start.left == null && start.right == null) {
            if (start.parent != null) {
                if (parent.left == start) parent.left = null;
                if (parent.right == start) parent.right = null;
                start.parent = null;
            } else {
                root = null;
            }
            this.size--;
        }
        else if ((start.left != null && start.right == null)
                  || (start.right != null && start.left == null)) {
            BSTNode re = start.left == null ? start.right : start.left;
            if (parent != null) {
                if (parent.left == start) parent.left = re;
                if (parent.right == start) parent.right = re;
                start.parent = null;
            } else {
                root = re;
            }
            this.size--;
        } else if (start.left != null && start.right != null) {
            BSTNode delMin = deleteMin(start.right);
            start.value = delMin.value;
        }
    }

    public void display(BSTNode root){
        if(root!=null){
            display(root.left);
            System.out.print(" " + root.value);
            display(root.right);
        }
    }

    public static void main(String arg[]){
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        System.out.println("Original Tree : ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Check whether Node with value 4 exists : " + b.find(4));
        System.out.println("Delete Node with no children (2) : " + b.delete(2));
        b.display(b.root);
        System.out.println("\n Delete Node with one child (4) : " + b.delete(4));
        b.display(b.root);
        System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));
        b.display(b.root);
    }
}
