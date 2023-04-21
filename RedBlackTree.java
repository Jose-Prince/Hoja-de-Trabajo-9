public class RedBlackTree implements iTree {
    class RedBlackNode {
        RedBlackNode left, right;
        String element;
        int color;

        public RedBlackNode(String theElement){
            this(theElement, null, null);
        }

        public RedBlackNode(String theElement, RedBlackNode lt, RedBlackNode rt) {
            left = lt;
            right = rt;
            element = theElement;
            color = 1;
        }
    }

    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;

    static final int BLACK = 1;
    static final int RED = 0;

    public RedBlackTree(String negInf) {
        header = new RedBlackNode(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }

    public boolean isEmpty() {
        return header.right == nullNode;
    }

    public void clear() {
        header.right = nullNode;
    }

    public void insert(String item) {
        current = parent = grand = header;
        nullNode.element = item;
        while (current.element != item) {
            great = grand;
            grand = parent;
            parent = current;
            current = item.compareTo(current.element) < 0 ? current.left : current.right;

            if (current.left.color == RED && current.right.color == RED)
                handleReorient(item);
        }

        if (item.compareTo(parent.element) < 0)
            parent.left = current;
        else 
            parent.right = current;
        handleReorient(item);
    }

    private void handleReorient(String item) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            grand.color = RED;
            if (item.compareTo(grand.element) < 0 != item.compareTo(parent.element) < 0)
                parent = rotate(item, grand);
            current = rotate(item, great);
            current.color = BLACK;
        }
    }

    private RedBlackNode rotate(String item, RedBlackNode parent){
        if(item.compareTo(parent.element) < 0)
            return parent.left = item.compareTo(parent.left.element) < 0 ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left);
        else
            return parent.right = item.compareTo(parent.right.element) < 0 ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
    }

    private RedBlackNode rotateWithLeftChild(RedBlackNode k2){
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    private RedBlackNode rotateWithRightChild(RedBlackNode k1){ 
        RedBlackNode k2 = k1.left;
        k1.right = k2.left;
        k2.left = k1;
        return k2; 
    }

    public int count(RedBlackNode r){
        if (r == nullNode)
            return 0;
        else {
            int l = 1;
            l += count(r.left);
            l += count(r.right);
            return l;
        }
    }

    public boolean search(String val){
        return search(header.right, val);
    }

    private boolean search(RedBlackNode r, String val){
        boolean found = false;
        while ((r != nullNode) && !found){
            String rval = r.element;
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

    public String get(String val){
        return get(header.right, val);
    }

    private String get(RedBlackNode r, String val){
        boolean found = false;
        while ((r != nullNode) && !found){
            String rval = r.element;
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
        if (found != false){
            String[] palabras = r.element.split(",");
            return palabras[1];
        }

        else 
            return "*"+val+"*";
    }
}
