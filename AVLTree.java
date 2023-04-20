class AVLNode {
    AVLNode left, right;
    String data;
    int height;

    public AVLNode() {
        left = null;
        right = null;
        data = "";
        height = 0;
    }

    public AVLNode(String n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}

public class AVLTree implements iTree{
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public void insert (String data) {
        root = insert(data, root);
    }

    private int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    private AVLNode insert(String x, AVLNode t) {
        if (t == null)
            t = new AVLNode(x);
        else if (x.compareTo(t.data) < 0){
            t.left = insert(x, t.left);
            if (x.compareTo(t.left.data) < 0)
                t = rotateWithLeftChild(t);
            else 
                t = doubleWithLeftChild(t);
        } else if (x.compareTo(t.data) > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) 
                if(x.compareTo(t.right.data) > 0)
                    t = rotateWithRightChild(t);
                else 
                    t = doubleWithRightChild(t);
        } else 
            ;
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height ) + 1;
        return k1;
    }

    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k2.height) + 1;
        return k2;
    }

    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public int count() {
        return countNodes(root);
    }

    private int countNodes(AVLNode r) {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }

    public boolean search(String val) {
        return search(root, val);
    }

    private boolean search(AVLNode r, String val){
        boolean found = false;
        while ((r != null) && !found){
            String rval = r.data;
            if (val.compareTo(rval) < 0) 
                r = r.left;
            else if (val.compareTo(rval) > 0)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
}
