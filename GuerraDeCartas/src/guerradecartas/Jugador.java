package guerradecartas;

public class Jugador {

    private String nombre;
    private Cola mazo;
    private int rondasGanadas;
    private int empates;

    public Jugador(String nombre, ListaEnlazada mazo) {
        this.nombre = nombre;
        this.mazo = new Cola();
        Nodo actual = mazo.getCabeza();
        while (actual != null) {
            this.mazo.encolar(actual.data);
            actual = actual.next;
        }
        this.rondasGanadas = 0;
        this.empates = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Cola getMazo() {
        return mazo;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public void incrementarRondasGanadas() {
        rondasGanadas++;
    }

    public void incrementarEmpates() {
        empates++;
    }

    public int getEmpates() {
        return empates;
    }
}
