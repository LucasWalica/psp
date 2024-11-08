package ActividadesRefuerzo.CadenaDeMontaje;

import java.util.Random;

public class Colocador extends Thread {

    CadenaDeMontaje c;
    public Colocador(CadenaDeMontaje c){
        this.c = c;
    }

    @Override
    public void run() {
        while(true){
            try {
                checkForEmptySpace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void checkForEmptySpace() throws InterruptedException {
        int emptySpace = c.checkEmptySpace();
        if(emptySpace==-1){
            System.out.println("No hay espacio vacio, esperando");
            wait();
            return;
        }
        Random r = new Random();
        int productoNuevo = r.nextInt(3)+1;
        c.cadena[emptySpace] = productoNuevo;
        System.out.println("El robot colocador esta colocando un producto "+ productoNuevo);
        sleep(1000);
        notifyAll();
    }
}
