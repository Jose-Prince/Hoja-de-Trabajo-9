public class TreeFactory {
    
    public iTree getInstance(int num){
        iTree ArbolUsado = null;

        switch (num) {
            case 1:
                ArbolUsado = new SplayTree();
                break;
        
            case 2:
                ArbolUsado = new RedBlackTree("");
                break;

            case 3:
                ArbolUsado = new AVLTree();
                break;
        }

        return ArbolUsado;
    }
    
}
