package BarberoDurmiente3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Barbero barbero = new Barbero();

        for(int i=0; i<10; i++){
            executor.submit(new Cliente(i, barbero));
        }


    }


    public static class Barbero extends Thread{
        Semaphore sillas = new Semaphore(4);
        Semaphore sillaBarbero = new Semaphore(1);
        Barbero(){}

        @Override
        public synchronized void run() {
            while(true){

            }
        }
    }

    public static class Cliente extends Thread{
        int id;
        boolean atendido = false;
        Barbero barbero;
        Cliente(int id, Barbero barbero){
            this.id = id;
            this.barbero = barbero;
        }

        @Override
        public void run() {
            synchronized (barbero) {
                while (!atendido) {
                    try {
                        if(this.barbero.sillas.tryAcquire()){
                            System.out.println("El cliente "+ this.id + " se ha sentado.");
                            sleep(500);
                            System.out.println("El cliente "+ this.id+ " está siendo atendido");
                            this.barbero.sillaBarbero.acquire();
                            sleep((int)(Math.random()*1000+1000));
                            this.barbero.sillas.release();
                            this.barbero.sillaBarbero.release();
                            this.atendido = true;
                            notifyAll();
                        }else{
                            System.out.println("El cliente "+ this.id + " se ha ido a pasear");
                            this.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                interrupt();
            }
        }
    }



}
