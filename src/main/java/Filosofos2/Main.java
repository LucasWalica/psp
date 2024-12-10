package Filosofos2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore tenedores = new Semaphore(5, true);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++){
            executorService.submit(new Filosofo(i, tenedores));
        }
        

    }

    public static class Filosofo extends Thread{
        boolean comiendo = false;
        boolean pensando = true;
        int id;
        Semaphore tenedores;

        Filosofo(int id, Semaphore tenedores){
            this.id = id;
            this.tenedores = tenedores;
        }

        @Override
        public void run() {
            while(true){
                try {
                    tenedores.acquire(2);
                    System.out.println("El filosofo "+ this.id + " está comiendo");
                    Thread.sleep(200);
                    tenedores.release(2);
                    System.out.println("El filosofo "+ this.id + " está pensando");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
