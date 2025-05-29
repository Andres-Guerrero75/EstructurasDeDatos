package guerradecartas;

public class ArbolDecision {
    private NodoArbol raiz;

    public ArbolDecision() {
        // Arma el arbol para decidir en guerras
        raiz = new NodoArbol("Hay empate");
        raiz.izquierda = new NodoArbol("Continuar ronda");
        raiz.derecha = new NodoArbol("Quedan cartas");
        raiz.derecha.izquierda = new NodoArbol("Perder");
        raiz.derecha.derecha = new NodoArbol("Continuar Guerra");
    }

    public String decidir(boolean esEmpate, boolean hayCartas) {
        NodoArbol actual = raiz;
        if (esEmpate) {
            actual = actual.derecha;
            return hayCartas ? actual.derecha.decision : actual.izquierda.decision;
        }
        return actual.izquierda.decision;
    }
}