package ActividadesRefuerzo.ContadorLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        int n = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Contador c = new Contador(0);
        for(int i=0; i<n; i++){
            executorService.submit(new Tarea(c));
        }
        executorService.shutdown();
    }
}

class Tarea extends Thread{
    Contador c;
    Tarea(Contador c){
        this.c = c;
    }
    @Override
    public void run() {
        while(!this.isInterrupted()){
            c.inc();
            System.out.println(c.get());
            if(c.get()>=100){
                this.interrupt();
            }
            try {
                sleep(250);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}