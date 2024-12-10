package BarberoDurmiente2;
import java.util.concurrent.Semaphore;

public class Barbería {

    Semaphore salaEspera;
    Semaphore sillon;
    Semaphore barbero;

    Barbería(int n){

        salaEspera = new Semaphore(n);
        sillon = new Semaphore(1);
        barbero = new Semaphore(0);
    }

    public synchronized boolean entrarEnSalaDeEspera(){
        return salaEspera.tryAcquire();
    }
    public void salirDeLaSalaDeEspera(){
        salaEspera.release();
    }
    public void ocuparSillon() throws InterruptedException{
        sillon.acquire();
    }
    public void liberarSillon(){
        sillon.release();
    }

    public void despertarBarbero(){
        barbero.release();
    }
    public void esperarCliente() throws  InterruptedException{
        barbero.acquire();
    }
}