package BarberoDurmiente2;
import java.util.concurrent.Semaphore;

public class Barbería {

    Semaphore salaEspera;
    Cliente sillon;

    Barbería(int n){
        salaEspera = new Semaphore(n);
    }

    public synchronized boolean entrarEnSalaDeEspera(){
        if(this.salaEspera.availablePermits() == 0){
            return  false;
        }else{
            if(salaEspera.tryAcquire()){
                return false;
            }else{
                return true;
            }
        }
    }

    public void solicitarCorteDePelo(){

    }
}