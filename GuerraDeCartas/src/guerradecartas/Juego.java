package guerradecartas;

import java.util.Scanner;

public class Juego {

    private Jugador jugador1;
    private Jugador jugador2;
    private Pila historialBatalla;
    private ArbolDecision arbolGuerra;
    private TablaHash estadosJugadores;
    private int ronda;
    private Scanner scanner;

    public Juego(String nombreJugador1, String nombreJugador2) {
        ListaEnlazada baraja = crearBaraja();
        baraja.barajar();
        ListaEnlazada[] mazos = baraja.dividir();
        jugador1 = new Jugador(nombreJugador1, mazos[0]);
        jugador2 = new Jugador(nombreJugador2, mazos[1]);
        historialBatalla = new Pila();
        arbolGuerra = new ArbolDecision();
        estadosJugadores = new TablaHash();
        estadosJugadores.poner(nombreJugador1, jugador1);
        estadosJugadores.poner(nombreJugador2, jugador2);
        ronda = 0;
        scanner = new Scanner(System.in);
    }

    private ListaEnlazada crearBaraja() {
        ListaEnlazada baraja = new ListaEnlazada();
        String[] palos = {"Corazones", "Picas", "Treboles", "Diamantes"};
        String[] valores = {"2", "3", "4", "5", "6", "7"};
        int[] numeros = {2, 3, 4, 5, 6, 7}; // Reducido para juegos más cortos

        for (String palo : palos) {
            for (int i = 0; i < valores.length; i++) {
                int valorAjustado = numeros[i] * 10;
                baraja.agregar(new Tarjeta(palo, valores[i], valorAjustado));
            }
        }
        return baraja;
    }

    public void iniciar() {
        System.out.println("Bienvenido a Guerra de Cartas");
        System.out.println("Jugador 1: " + jugador1.getNombre());
        System.out.println("Jugador 2: " + jugador2.getNombre());

        while (!jugador1.getMazo().estaVacia() && !jugador2.getMazo().estaVacia()) {
            System.out.print("\nPresiona Enter para jugar la siguiente ronda...");
            scanner.nextLine(); // Espera que el usuario presione Enter
            jugarRonda();
        }

        System.out.println("\n--- Juego Terminado ---");
        if (jugador1.getMazo().estaVacia()) {
            System.out.println(jugador2.getNombre() + " gana el juego con " + jugador2.getMazo().tamano() + " cartas");
        } else {
            System.out.println(jugador1.getNombre() + " gana el juego con " + jugador1.getMazo().tamano() + " cartas");
        }
    }

    private void jugarRonda() {
        ronda++;
        System.out.println("\n-- Ronda " + ronda + " --");
        Tarjeta tarjeta1 = jugador1.getMazo().desencolar();
        Tarjeta tarjeta2 = jugador2.getMazo().desencolar();

        if (tarjeta1 == null || tarjeta2 == null) {
            System.out.println("Juego terminado por falta de cartas");
            return;
        }

        System.out.println(jugador1.getNombre() + " juega: " + tarjeta1);
        System.out.println(jugador2.getNombre() + " juega: " + tarjeta2);
        historialBatalla.apilar(tarjeta1);
        historialBatalla.apilar(tarjeta2);

        if (tarjeta1.getNumero() > tarjeta2.getNumero()) {
            System.out.println("Ganador: " + jugador1.getNombre());
            jugador1.getMazo().encolar(tarjeta1);
            jugador1.getMazo().encolar(tarjeta2);
            jugador1.incrementarRondasGanadas();
        } else if (tarjeta2.getNumero() > tarjeta1.getNumero()) {
            System.out.println("Ganador: " + jugador2.getNombre());
            jugador2.getMazo().encolar(tarjeta1);
            jugador2.getMazo().encolar(tarjeta2);
            jugador2.incrementarRondasGanadas();
        } else {
            System.out.println("Empate. Se inicia la guerra...");
            jugador1.incrementarEmpates();
            jugador2.incrementarEmpates();
            jugarGuerra(tarjeta1, tarjeta2);
        }

        System.out.println("\n-- Estado actual --");
        System.out.println(jugador1.getNombre() + ": " + jugador1.getMazo().tamano() + " cartas");
        System.out.println(jugador2.getNombre() + ": " + jugador2.getMazo().tamano() + " cartas");
    }

    private void jugarGuerra(Tarjeta tarjeta1, Tarjeta tarjeta2) {
        Cola cartasGuerra1 = new Cola();
        Cola cartasGuerra2 = new Cola();
        cartasGuerra1.encolar(tarjeta1);
        cartasGuerra2.encolar(tarjeta2);

        if (jugador1.getMazo().tamano() < 2 || jugador2.getMazo().tamano() < 2) {
            System.out.println("No hay suficientes cartas para guerra. Fin del juego.");
            if (jugador1.getMazo().tamano() < 2) {
                while (!jugador1.getMazo().estaVacia()) {
                    jugador2.getMazo().encolar(jugador1.getMazo().desencolar());
                }
            } else {
                while (!jugador2.getMazo().estaVacia()) {
                    jugador1.getMazo().encolar(jugador2.getMazo().desencolar());
                }
            }
            return;
        }

        Tarjeta bocaAbajo1 = jugador1.getMazo().desencolar();
        Tarjeta bocaAbajo2 = jugador2.getMazo().desencolar();
        cartasGuerra1.encolar(bocaAbajo1);
        cartasGuerra2.encolar(bocaAbajo2);

        System.out.println("Ambos colocan una carta boca abajo...");

        Tarjeta bocaArriba1 = jugador1.getMazo().desencolar();
        Tarjeta bocaArriba2 = jugador2.getMazo().desencolar();
        cartasGuerra1.encolar(bocaArriba1);
        cartasGuerra2.encolar(bocaArriba2);

        System.out.println(jugador1.getNombre() + " coloca: " + bocaArriba1 + " (boca arriba)");
        System.out.println(jugador2.getNombre() + " coloca: " + bocaArriba2 + " (boca arriba)");

        historialBatalla.apilar(bocaAbajo1);
        historialBatalla.apilar(bocaAbajo2);
        historialBatalla.apilar(bocaArriba1);
        historialBatalla.apilar(bocaArriba2);

        if (bocaArriba1.getNumero() > bocaArriba2.getNumero()) {
            System.out.println("Ganador de la guerra: " + jugador1.getNombre());
            while (!cartasGuerra1.estaVacia()) {
                jugador1.getMazo().encolar(cartasGuerra1.desencolar());
            }
            while (!cartasGuerra2.estaVacia()) {
                jugador1.getMazo().encolar(cartasGuerra2.desencolar());
            }
        } else if (bocaArriba2.getNumero() > bocaArriba1.getNumero()) {
            System.out.println("Ganador de la guerra: " + jugador2.getNombre());
            while (!cartasGuerra1.estaVacia()) {
                jugador2.getMazo().encolar(cartasGuerra1.desencolar());
            }
            while (!cartasGuerra2.estaVacia()) {
                jugador2.getMazo().encolar(cartasGuerra2.desencolar());
            }
        } else {
            System.out.println("Empate nuevamente. Se repite la guerra...");
            jugarGuerra(bocaArriba1, bocaArriba2); // Recursivamente se repite la guerra
        }
    }
}
