package ActividadesRefuerzo.creacionDeHilos;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreacionDeHilos {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el numero de hilos que quiere crear: ");
        int n = in.nextInt();
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for(int i=0; i<n; i++){
            int taskId = i+1;
            Callable<String> task = new MiTareaCallable(taskId, (int)(Math.random()*1000));

            executorService.submit(() -> {
                try{
                    String result = task.call();
                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }


    static class MiTareaCallable implements Callable<String> {
        private final int taskId;
        private final int timeSleep;

        public MiTareaCallable(int taskId, int timeSleep){
            this.taskId = taskId;
            this.timeSleep = timeSleep;
        }

        @Override
        public String call() throws Exception {
            System.out.println("El hilo "+this.taskId+" ha sido inicializado y dormira "+this.timeSleep+" milisegundos");
            Thread.sleep(this.timeSleep);
            return "El hilo " + this.taskId + " ha completado su tarea";
        }
    }
}
