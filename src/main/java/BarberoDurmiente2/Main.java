package BarberoDurmiente2;

public class Main {
    public static void main(String[] args) {
        Barbería barbería = new Barbería(3);

        Barbero barbero = new Barbero(barbería);
        Thread hiloBarbero = new Thread(barbero);
        hiloBarbero.start();
        for(int i=0; i<=10; i++){
            Cliente cliente = new Cliente(barbería);
            Thread hiloCliente = new Thread(cliente);
            hiloCliente.start();

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
