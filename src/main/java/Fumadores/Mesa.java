package Fumadores;

public class Mesa extends Thread {
    public String ing1;
    public String ing2;

    Mesa(){}

    public synchronized void repartirIngredientes() throws InterruptedException {
        System.out.println("El agente está repartiendo ingredientes \n --------------------");
    }

}
