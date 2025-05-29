package guerradecartas;

public class Pila {
    private Nodo cima;

    public Pila() {
        cima = null;
    }

    public void apilar(Tarjeta tarjeta) {
        Nodo nuevoNodo = new Nodo(tarjeta);
        nuevoNodo.next = cima;
        cima = nuevoNodo;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}