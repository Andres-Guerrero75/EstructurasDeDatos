package guerradecartas;

public class NodoArbol {

    String decision;
    NodoArbol izquierda; // rama no
    NodoArbol derecha; // rama si

    public NodoArbol(String decision) {
        this.decision = decision;
        this.izquierda = null;
        this.derecha = null;
    }
}
