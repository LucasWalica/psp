package ParqueBanco;

public class Banco extends Thread {

    private int capacidad;

    Banco(int capacidad){
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public synchronized void ocuparSitio() throws InterruptedException {
        if(this.capacidad>0){
            this.capacidad--;

        }else{
            wait();
        }
    }
    public synchronized void lenvantarse() {
        this.capacidad++;
    }




}