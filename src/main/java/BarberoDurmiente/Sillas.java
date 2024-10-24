package BarberoDurmiente;

public class Sillas {
    private final int numSillas;
    private int sillasOcupadas = 0;

    Sillas(int numSillas){
        this.numSillas = numSillas;
    }

    public synchronized boolean ocupar(){
        if(this.sillasOcupadas>=numSillas){
            return false;
        }else{
            sillasOcupadas++;
            notify();
            return true;
        }
    }

    public synchronized boolean estanVacias(){
        if(sillasOcupadas>=numSillas){
            return false;
        }
        return true;
    }

    public synchronized  void liberarsilla() {
        sillasOcupadas--;
    }
}
