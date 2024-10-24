package Actividad11;

public class Contador2 {
    private int n;
    public Contador2(int n) {
        this.n = n;
    }
    public synchronized void inc() {
        n++;
    }
    public int getCounter(){
        return n;
    }
}