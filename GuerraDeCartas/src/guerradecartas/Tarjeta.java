package guerradecartas;

public class Tarjeta {

    private String palo; // tipo de carta, como Corazones o Picas
    private String valor; // valor, como 2, 3, ..., J, Q, K, A
    private int numero; // valor numerico para comparar (2-14, As=14)

    public Tarjeta(String palo, String valor, int numero) {
        this.palo = palo;
        this.valor = valor;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String toString() {
        return valor + " de " + palo;
    }
}
