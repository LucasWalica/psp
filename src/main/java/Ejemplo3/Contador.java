package Ejemplo3;

public class Contador {
    private int counter;
    public Contador(int counter){
        this.counter=counter;
    }
    // synchronized anula la asincronia en este bloque de codigo:
    public synchronized void inc(){
        // synchronized (this) {}
        counter++;
    }
    public int getCounter(){
        return this.counter;
    }
}