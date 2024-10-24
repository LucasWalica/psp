package Ejemplo11;

public class Main {

    public static void main(String[] args) {

        Almacen almacen = new Almacen(100);
        Productor productor = new Productor(almacen, 100);
        Consumidor consumidor = new Consumidor(almacen, 1000);
        Productor p2 = new Productor(almacen, 200);
        Productor p3 = new Productor(almacen, 300);
        Productor p4 = new Productor(almacen, 400);
        Consumidor c2 = new Consumidor(almacen, 1000);
        Consumidor c3 = new Consumidor(almacen, 3000);
        Consumidor c4 = new Consumidor(almacen, 4000);
        productor.start();
        consumidor.start();
        p2.start();
        p3.start();
        p4.start();
        c2.start();
        c3.start();
        c4.start();
    }
}