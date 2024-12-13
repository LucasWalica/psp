package ActividadesRefuerzo.CadenaDeMontaje2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        ArrayList<Productos> prods = new ArrayList<>(Arrays.asList(
                Productos.Drones,
                Productos.Misiles,
                Productos.Tanques
        ));
        CadenaDeMontaje cad = new CadenaDeMontaje();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Colocador colocador = new Colocador(cad);

        for(int i=0; i<3; i++){
            executor.submit(new Paletizador(i, prods.get(i), cad));
        }
        colocador.run();
    }

    public static class Colocador extends Thread{
        ArrayList<Productos> prods = new ArrayList<>(Arrays.asList(
                Productos.Drones,
                Productos.Misiles,
                Productos.Tanques
        ));
        CadenaDeMontaje cad = new CadenaDeMontaje();
        Colocador(CadenaDeMontaje cad){
            this.cad = cad;
        }

        @Override
        public void run() {
            while(true){
                    try {
                        this.cad.ocupado.acquire();
                        System.out.println("Se ha colocado un nuevo item en la cadena de montaje");
                        this.selectRandomProd();
                        this.cad.ocupado.release();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }

        public void selectRandomProd(){
            this.cad.prods.add(this.prods.get((int)(Math.random()*this.prods.size())));
        }
    }

    public static class Paletizador extends Thread{

        int id;
        int numPaletizados = 0;
        Productos prod;
        CadenaDeMontaje cad;

        Paletizador(int id, Productos prod, CadenaDeMontaje cad){
            this.id= id;
            this.prod = prod;
            this.cad = cad;
        }

        @Override
        public void run() {
            while(true){
                if(checkForProduct()){
                    try {
                        this.cad.ocupado.acquire();
                        this.numPaletizados++;
                        System.out.println("El paletizador "+ this.id + " ha recogido un "+this.prod);
                        System.out.println("Lleva recogidos "+this.numPaletizados+ " productos");
                        Thread.sleep(500);
                        this.cad.ocupado.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public boolean checkForProduct(){
            for(int i=0; i<this.cad.prods.size(); i++){
                if(this.cad.prods.get(i).equals(this.prod)){
                    return true;
                }
            }
            return false;
        }
    }

    public static class CadenaDeMontaje {
        Semaphore ocupado = new Semaphore(2, true);
        ArrayList<Productos> prods = new ArrayList<>();

        CadenaDeMontaje(){}


    }

    enum Productos{
        Drones,
        Tanques,
        Misiles
    }
}
