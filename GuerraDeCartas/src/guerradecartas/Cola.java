package guerradecartas;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public void encolar(Tarjeta tarjeta) {
        Nodo nuevoNodo = new Nodo(tarjeta);
        if (fin == null) {
            frente = fin = nuevoNodo;
        } else {
            fin.next = nuevoNodo;
            fin = nuevoNodo;
        }
    }

    public Tarjeta desencolar() {
        if (estaVacia()) {
            return null;
        }
        Tarjeta tarjeta = frente.data;
        frente = frente.next;
        if (frente == null) {
            fin = null;
        }
        return tarjeta;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamano() {
        int conteo = 0;
        Nodo actual = frente;
        while (actual != null) {
            conteo++;
            actual = actual.next;
        }
        return conteo;
    }
}