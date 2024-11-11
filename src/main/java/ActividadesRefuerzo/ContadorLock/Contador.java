package ActividadesRefuerzo.ContadorLock;

import java.util.concurrent.locks.ReentrantLock;

public class Contador {
    ReentrantLock lock = new ReentrantLock();
    private int n;
    public Contador(int n) {
        this.n = n;
    }
    public void inc() {
        lock.lock();
        try {
            n++;
        } finally {
            lock.unlock();
        }
    }
    public int get() {
        return n;
    }
}