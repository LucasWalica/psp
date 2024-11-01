package PinchaGlobos;

public abstract class HiloInterrumpible extends Thread{

    private boolean pausado = false;

    @Override
    public synchronized void run() {
        while (!interrupted()) {
            if (pausado) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
            tarea();
        }
    }

    public synchronized void pausaOnOff(){
        pausado = !pausado;
    }

    protected abstract void tarea();
}