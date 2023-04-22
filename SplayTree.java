/**
 * @author José Prince
 * @date 21/04/2023
 * Algoritmos y Estructura de Datos
 * Implementación de Splay Tree
 */
public class SplayTree implements iTree {

    //Clase de los nodos del árbol
    class SplayNode{
        SplayNode left, right, parent;
        String element;

        public SplayNode(){
            this("",null,null,null);
        }

        public SplayNode(String ele){
            this(ele, null, null, null);
        }

        public SplayNode(String ele, SplayNode left, SplayNode right, SplayNode parent){
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.element = ele;
        }
    }

    private SplayNode root;
    private int countN = 0;

    public SplayTree(){
        root = null;
    }

    public boolean isEmpty(){
        if (root == null)
            return true;
        else 
            return false;
    }

    public void clear(){
        root = null;
        countN = 0;
    }

    public void insert(String ele){
        SplayNode z = root;
        SplayNode p = null;
        while (z != null){
            p = z;
            if (ele.compareTo(p.element) > 0)
                z = z.right;
            else 
                z = z.left;
            }
            z = new SplayNode();
            z.element = ele;
            z.parent = p;
            if (p == null)
                root = z;
            else if (ele.compareTo(p.element) > 0)
                p.right = z;
            else 
                p.left = z;
            Splay(z);
            countN++;
    }

    public void makeLeftChildParent(SplayNode c, SplayNode p){
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
 
        if (p.parent != null){
            if (p == p.parent.left)
                p.parent.left = c;
            else 
                p.parent.right = c;
        } if (c.right != null)
            c.right.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

     public void makeRightChildParent(SplayNode c, SplayNode p){
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null){
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    private void Splay(SplayNode x)
     {
         while (x.parent != null)
         {
             SplayNode Parent = x.parent;
             SplayNode GrandParent = Parent.parent;
             if (GrandParent == null)
             {
                 if (x == Parent.left)
                     makeLeftChildParent(x, Parent);
                 else
                     makeRightChildParent(x, Parent);                 
             } 
             else
             {
                 if (x == Parent.left) {
                     if (Parent == GrandParent.left){
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    } else {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                 } else {
                    if (Parent == GrandParent.left){
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    } else {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    public void remove(String ele){
        SplayNode node = findNode(ele);
        remove(node);
    }

    private void remove(SplayNode node){
        if (node == null)
            return;

        Splay(node);
        if ((node.left != null) && (node.right != null)){
            SplayNode min = node.left;
            while(min.right != null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        } else if (node.right != null){
            node.right.parent = null;
            root = node.right;
        } else if (node.left != null) {
            node.left.parent = null;
            root = node.left;
        } else {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        countN--;
    }

    public int count() {
        return countN;
    }

    public boolean search(String val) {
        return findNode(val) != null;
    }

    private SplayNode findNode(String ele){
        SplayNode PrevNode = null;
        SplayNode z = root;
        while (z != null){
            PrevNode = z;
            String[] words = z.element.split(",");
            if (ele.compareTo(words[0]) > 0)
                z = z.right;
            else if (ele.compareTo(words[0]) < 0)
                z = z.left;
            else if (ele.compareTo(words[0]) == 0){
                Splay(z);
                return z;
            }
        }

        if (PrevNode != null){
            Splay(PrevNode);
            return null;
        }
        return null;
    }

    public String get(String ele){
        if (search(ele) == true){
            String[] palabras = getNode(ele).element.split(",");
            return palabras[1];
        }
        else 
            return "*"+ele+"*";
    }
    
    private SplayNode getNode(String ele){
        SplayNode PrevNode = null;
        SplayNode z = root;
        while (z != null){
            PrevNode = z;
            String[] words = z.element.split(",");
            if (ele.compareTo(words[0]) > 0)
                z = z.right;
            else if (ele.compareTo(words[0]) < 0)
                z = z.left;
            else if (ele.compareTo(words[0]) == 0){
                Splay(z);
                return z;
            }
        }

        if (PrevNode != null){
            Splay(PrevNode);
            return null;
        }
        return null;
    }
}


