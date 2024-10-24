package Actividad11;

import Ejemplo3.Main;

public class Contador2Main {
    private static Contador2 c = new Contador2(100);
    private static void run() {
        for (int i = 0; i < 100; i++) {
            c.inc();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Main::run);
        Thread t2 = new Thread(Main::run);
        // la prioridad no es necesaria en este caso, ya que no se necesita ejecutar un hilo antes que otro.
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Contador = "+ c.getCounter());
    }
}