package ParqueBanco2;

import ParqueBanco.Biandante;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("asientos banco \n >");
        int asiento = in.nextInt();
        Semaphore banco = new Semaphore(asiento, true);
        System.out.println("Numero de biandantes \n >");
        int nBiandantesInput = in.nextInt();
        ExecutorService nBiandantes = Executors.newFixedThreadPool(nBiandantesInput);
        for(int i=0; i<nBiandantesInput; i++){
            nBiandantes.submit(new Biandante(i, banco));
        }
    }


    public static class Biandante extends Thread{
        int id;
        Semaphore banco;
        Biandante(int id, Semaphore banco){
            this.id = id;
            this.banco = banco;
        }

        @Override
        public void run() {
            while(true){
                try {
                    banco.acquire();
                    System.out.println("El biandante "+ this.id+ " se ha sentado en el banco");
                    Thread.sleep(((int) ((Math.random()*2000)+1000)));
                    banco.release();
                    System.out.println(this.id +" se ha levantado para pasear");
                    Thread.sleep((int) ((Math.random()*2000)+1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
