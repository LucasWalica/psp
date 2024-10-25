package Fumadores;

import java.util.ArrayList;

public class Agente extends Thread{

    Ingredientes ingredientes;
    Mesa mesa;
    ArrayList<Fumador> fumadores = new ArrayList<>();
    public boolean fumadoresIniciados = false;


    Agente(Ingredientes ingredientes, Mesa mesa, ArrayList<Fumador> fumadores){
        this.ingredientes = ingredientes;
        this.mesa = mesa;
        this.fumadores = fumadores;
    }


    @Override
    public void run() {
        while(true){
            this.colocarEnMesa();
            startFumadores();
            try {
                mesa.repartirIngredientes();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ingredientes.initializeIngredientes();
        }
    }

    public void startFumadores(){
        if(this.fumadoresIniciados==false){
            for(Fumador f : this.fumadores){
                f.start();
            }
        }
        this.fumadoresIniciados=true;
    }

    public void colocarEnMesa(){
        String ingre1 = ingredientes.getRandomIngrediente();
        String ingre2 = ingredientes.getRandomIngrediente();
        while(ingre2.equals(ingre1)){
            ingre2 = ingredientes.getRandomIngrediente();
        }
        System.out.println("El agente ha colocado "+ ingre1 + " y " + ingre2);
        this.mesa.ing1=ingre1;
        this.mesa.ing2=ingre2;
    }

}