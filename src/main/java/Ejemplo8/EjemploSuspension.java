package Ejemplo8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploSuspension extends Thread {
    static volatile boolean suspendido = false;
    public volatile boolean ejecutando = true;
    public synchronized void suspender() {
        suspendido = true;
    }
    public synchronized void reanudar() {
        suspendido = false;
        notify();
    }
    public void detener(){
        ejecutando = false;
        interrupt();
    }
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    if (suspendido) {
                        System.out.println("suspendido");
                        wait();
                    }
                }
                System.out.println("en ejecución");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                if(!ejecutando){
                    System.out.println("Hilo interrumpido y detenido.");
                    return;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        EjemploSuspension hilo = new EjemploSuspension();
        hilo.start();
        boolean salir = false;
        do {
            System.out.println("s -> suspender / r -> reanudar / x -> detener");
            String opcion = in.readLine();
            switch (opcion.toLowerCase()) {
                case "s":
                    hilo.suspender();
                    break;
                case "r":
                    hilo.reanudar();
                    break;
                case "x":
                    hilo.detener();
                    salir = true;
                    break;
            }
        } while(!salir);
        System.out.println("Programa finalizado");
    }
}