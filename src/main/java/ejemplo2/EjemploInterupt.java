package ejemplo2;

public class EjemploInterupt extends Thread {


    public void run(){
        while (!isInterrupted()){
            System.out.println("en el Hilo:");
            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Interrumpido");
                interrupt();
                System.out.println(this.isInterrupted());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        EjemploInterupt h = new EjemploInterupt();
        h.start();

        Thread.sleep(200);
        h.interrupt();
        h.join();
        System.out.println("hilo finalizado");
    }
}