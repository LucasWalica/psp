package BarberoDurmiente;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        Sillas sillas = new Sillas(4);
        ArrayList<Clientes> clientes = new ArrayList<Clientes>();
        Barbero b = new Barbero(clientes, sillas);
        b.start();
    }
}