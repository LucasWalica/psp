package Ejemplo11;

public class Almacen {

    private Integer almacenados = 0;
    private String [] productos;
    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }
    public void almacenar(String producto) {
        synchronized (this) {
            while (almacenados == productos.length) // almacén lleno
                try {
                    wait();
                } catch (InterruptedException e) {
                }

            productos[almacenados++] = producto;
            notify();
        }
    }
    public synchronized String retirar() {
        synchronized (this) {
            while (almacenados == 0) // almacén vacío
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            String producto = productos[--almacenados];
            notify();
            return producto;
        }
    }
}