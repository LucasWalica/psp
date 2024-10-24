package Fumadores;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Mesa mesa = new Mesa();
        Ingredientes ingredientes = new Ingredientes();
        ArrayList<Fumador> fumadores = new ArrayList<>(Arrays.asList(
                new Fumador(1, "tabaco", mesa),
                new Fumador(2, "papel", mesa),
                new Fumador(3, "pipa", mesa)
        ));
        Agente agente = new Agente(ingredientes, mesa, fumadores);
        agente.start();

    }
}
