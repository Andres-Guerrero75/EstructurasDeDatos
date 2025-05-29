package guerradecartas;

public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public void agregar(Tarjeta tarjeta) {
        Nodo nuevoNodo = new Nodo(tarjeta);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevoNodo;
        }
    }

    public void barajar() {
        Tarjeta[] arreglo = aArreglo();
        for (int i = arreglo.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Tarjeta temp = arreglo[i];
            arreglo[i] = arreglo[j];
            arreglo[j] = temp;
        }
        cabeza = null;
        for (Tarjeta tarjeta : arreglo) {
            agregar(tarjeta);
        }
    }

    public ListaEnlazada[] dividir() {
        ListaEnlazada lista1 = new ListaEnlazada();
        ListaEnlazada lista2 = new ListaEnlazada();
        Nodo actual = cabeza;
        int indice = 0;
        while (actual != null) {
            if (indice % 2 == 0) {
                lista1.agregar(actual.data);
            } else {
                lista2.agregar(actual.data);
            }
            actual = actual.next;
            indice++;
        }
        return new ListaEnlazada[]{lista1, lista2};
    }

    public int tamano() {
        int conteo = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            conteo++;
            actual = actual.next;
        }
        return conteo;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    private Tarjeta[] aArreglo() {
        int tamano = tamano();
        Tarjeta[] arreglo = new Tarjeta[tamano];
        Nodo actual = cabeza;
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = actual.data;
            actual = actual.next;
        }
        return arreglo;
    }
}