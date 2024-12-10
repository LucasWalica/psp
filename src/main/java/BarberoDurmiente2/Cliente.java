package BarberoDurmiente2;

public class Cliente implements Runnable{

    Barbería barbería;

    public Cliente(Barbería barbería){
        this.barbería = barbería;
    }

    @Override
    public void run() {
        try{
            if(barbería.entrarEnSalaDeEspera()){
                System.out.println("Cliente espera en la sala.");

                barbería.ocuparSillon();
                barbería.salirDeLaSalaDeEspera();
                barbería.despertarBarbero();
                System.out.println("Cliente esta siendo atendido.");

            }else{
                System.out.println("La barberia esta llena.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
