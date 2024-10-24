package Filosofos;

public class Sillas {
    private final int totalSillas=4;
    private int sillasOcupadas = 0;

    public Sillas() {
    }


    public synchronized void ocuparSilla() throws InterruptedException {
        while (sillasOcupadas >= totalSillas) {
            wait();
        }
        sillasOcupadas++;
        System.out.println("Una silla ha sido ocupada. Sillas disponibles: " + (totalSillas - sillasOcupadas));
    }


    public synchronized void liberarSilla() {
        sillasOcupadas--;
        System.out.println("Una silla ha sido liberada. Sillas disponibles: " + (totalSillas - sillasOcupadas));
        notify();
    }
}
