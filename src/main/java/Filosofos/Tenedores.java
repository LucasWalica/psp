package Filosofos;

public class Tenedores {
    private int id;
    public boolean estaCogido=false;

    Tenedores(int id){
        this.id=id;
    }


    public synchronized void cogerTenedor() throws InterruptedException {
        while(estaCogido){
            wait();
        }
        this.estaCogido=true;
    }

    public synchronized void dejarTenedor(){
        this.estaCogido=false;
        notify();
    }
}
