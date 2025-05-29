package guerradecartas;

public class TablaHash {
    private static class Entrada {
        String clave;
        Jugador valor;
        Entrada siguiente;

        Entrada(String clave, Jugador valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Entrada[] tabla;
    private int tamano = 10; // Tamano chico para dos jugadores

    public TablaHash() {
        tabla = new Entrada[tamano];
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode() % tamano);
    }

    public void poner(String clave, Jugador jugador) {
        int indice = hash(clave);
        Entrada entrada = new Entrada(clave, jugador);
        if (tabla[indice] == null) {
            tabla[indice] = entrada;
        } else {
            Entrada actual = tabla[indice];
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = entrada;
        }
    }

    public Jugador obtener(String clave) {
        int indice = hash(clave);
        Entrada actual = tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}