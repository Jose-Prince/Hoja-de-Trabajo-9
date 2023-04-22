import java.io.File;
import java.util.Scanner;

public class DiccionarioMain {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int rpa;
        TreeFactory factory = new TreeFactory();
        String traduccion = "";

        System.out.println("Escoja la implementación de Arbol: \n1:Splay Tree\n2:Red Black Tree\n3:AVLTree");
        try {
            rpa = teclado.nextInt();
            teclado.nextLine();
            iTree treeIngles = factory.getInstance(rpa);
            
            File vocabulario = new File("Spanish.txt");
            Scanner lector = new Scanner(vocabulario);
    
            while(lector.hasNextLine()) {
                String data = lector.nextLine().toLowerCase();
                treeIngles.insert(data);
            }
            lector.close();

            File oraciones = new File("texto.txt");
            lector = new Scanner(oraciones);

            while (lector.hasNextLine()){
                String sentence = lector.nextLine().toLowerCase();
                System.out.println("Oración original: " + sentence);
                String[] palabras = sentence.split(" ");
                for (String palabra : palabras) {
                    traduccion = traduccion + treeIngles.get(palabra) + " ";
                }
                System.out.println("Oración traducida: " + traduccion);
            }
            lector.close();
        } catch (Exception e) {
            teclado.nextLine();
            System.out.println("An error ocurred");
            // TODO: handle exception
        }
    }
}
