package BarberoDurmiente2;

public class Barbero implements Runnable{

    private Barbería barbería;

    public Barbero(Barbería barbería){
        this.barbería = barbería;
    }

    @Override
    public void run() {
        while(true){
            try{
                barbería.esperarCliente();
                System.out.println("Barbero esta cortando el pelo");
                Thread.sleep(5000);
                barbería.liberarSillon();

                System.out.println("Barbero termino de cortar el pelo");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
