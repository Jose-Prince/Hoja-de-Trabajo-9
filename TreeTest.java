/**
 * @author José Prince
 * @date 21/04/2023
 * Algoritmos y Estructura de Datos
 * Pruebas unitarias de las implementaciones de los árboles utilizados
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TreeTest {

    iTree splay = new SplayTree();
    iTree rbt = new RedBlackTree();
    iTree avl = new AVLTree();
    
    @Test
    public void SplayInsertTest(){
        splay.insert("word,palabra");
        assertEquals("palabra", splay.get("word"));

    }

    @Test
    public void RBTInsertTest() {
        splay.insert("word,palabra");
        assertEquals("palabra", splay.get("word"));
    }

    @Test 
    public void AVLInsertTest() {
        splay.insert("word,palabra");
        assertEquals("palabra", splay.get("word"));
    }

    @Test 
    public void SplaySearchTest() {
        splay.insert("word,palabra");
        assertEquals(true, splay.search("word"));
    }

    @Test
    public void RBTSearchTest() {
        splay.insert("word,palabra");
        assertEquals(true, splay.search("word"));
    }

    @Test
    public void AVLSearchTest() {
        splay.insert("word,palabra");
        assertEquals(true, splay.search("word"));
    }
}
