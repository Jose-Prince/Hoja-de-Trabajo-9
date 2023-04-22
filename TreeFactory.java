/**
 * @author José Prince
 * @date 21/04/2023
 * Algoritmos y Estructura de Datos
 * Clase faxtory para los arboles
 */
public class TreeFactory {
    
    
    /** 
     * @param num
     * @return iTree
     * Función que crea la instancia del árbol
     */
    public iTree getInstance(int num){
        iTree ArbolUsado = null;

        switch (num) {
            case 1:
                ArbolUsado = new SplayTree();
                break;
        
            case 2:
                ArbolUsado = new RedBlackTree();
                break;

            case 3:
                ArbolUsado = new AVLTree();
                break;
        }

        return ArbolUsado;
    }
}