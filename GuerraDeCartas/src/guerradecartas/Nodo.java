package guerradecartas;

public class Nodo {

    Tarjeta data;
    Nodo next;

    public Nodo(Tarjeta data) {
        this.data = data;
        this.next = null;
    }
}
