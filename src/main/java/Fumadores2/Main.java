package Fumadores2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {


    public static void main(String[] args) {

        ArrayList<Ingredientes> ings = new ArrayList<>(Arrays.asList(
                Ingredientes.CERILLAS,
                Ingredientes.PAPEL,
                Ingredientes.TABACO
        ));
        ExecutorService fumadores = Executors.newFixedThreadPool(3);
        Mesa mesa = new Mesa();


        mesa.start();
        for(int i=0; i<3; i++){
            fumadores.submit(new Fumador(i, ings.get(i), mesa));
        }

    }


    public static class Mesa extends Thread{

        ArrayList<Ingredientes> ings = new ArrayList<>(Arrays.asList(
                Ingredientes.CERILLAS,
                Ingredientes.PAPEL,
                Ingredientes.TABACO
        ));
        ArrayList<Ingredientes> ingsProv = new ArrayList<>(ings);
        Ingredientes ing1;
        Ingredientes ing2;
        public Mesa(){}

        @Override
        public synchronized void run() {
            while(true){
                this.ing1 = getRandomIng();
                this.ing2 = getRandomIng();
                System.out.println("Los ingredientes en la mesa son "+ this.ing1 + " y "+ this.ing2);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.ings = new ArrayList<>(ingsProv);
                notifyAll();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public Ingredientes getRandomIng(){
            Ingredientes ing = ings.get((int)(Math.random()*ings.size()));
            ings.remove(ing);
            return ing;
        }
    }


    public static class Fumador extends Thread{
        int id;
        Ingredientes ing;
        Mesa mesa;
        Fumador(int id, Ingredientes ing, Mesa mesa){
            this.id=id;
            this.ing = ing;
            this.mesa = mesa;
        }

        @Override
        public void run() {
            while(true){
                synchronized (mesa) {
                    if (checkIngs()) {
                        System.out.println("El fumador " + this.id + " está fumando");
                        mesa.notifyAll();

                        try {
                            mesa.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        public boolean checkIngs(){
            ArrayList<Ingredientes> ings = new ArrayList<>(Arrays.asList(ing, this.mesa.ing1, this.mesa.ing2));
            return ings.contains(Ingredientes.PAPEL) && ings.contains(Ingredientes.TABACO) && ings.contains(Ingredientes.CERILLAS);
        }
    }

    public enum Ingredientes {
        PAPEL,
        TABACO,
        CERILLAS
    }
}
