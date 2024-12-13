package ActividadesRefuerzo.Piscina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore piscina = new Semaphore(8, true);
        Scanner in = new Scanner(System.in);
        ArrayList<genero> generos = new ArrayList<>(Arrays.asList(
                genero.ninio,
                genero.ninia,
                genero.mujer,
                genero.hombre
        ));
        ArrayList<Nadador> personas = new ArrayList<>();
        System.out.println("Ingrese el numero de buzos: ");
        int nBuzos = in.nextInt();
        System.out.println("Ingrese el numero de nadadores: ");
        int nNadadores = in.nextInt();


        ExecutorService buzos = Executors.newFixedThreadPool(nBuzos);
        for(int i=0; i<nBuzos; i++){
            Nadador nad = new Nadador(i, 2, tipo.submarinista, generos.get((int)(Math.random()*generos.size())), piscina);
            personas.add(nad);
            buzos.submit(nad);
        }
        ExecutorService nadadores = Executors.newFixedThreadPool(nNadadores);
        for(int i=0; i<nNadadores; i++){
            Nadador nad = new Nadador(i, 1, tipo.nadador, generos.get((int)(Math.random()*generos.size())), piscina);
            personas.add(nad);
            nadadores.submit(nad);
        }

        Checker checker = new Checker(personas);
        checker.run();
    }


    public static class Checker extends Thread{
        ArrayList<Nadador> nads;
        Checker(ArrayList<Nadador> nads){
            this.nads = nads;
        }

        @Override
        public void run() {
            while(true){
                try {
                    checkSwimmers();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void checkSwimmers(){
            ArrayList<Nadador> nadsBor = new ArrayList<>();
            for(int i=0; i<this.nads.size(); i++){
                if(this.nads.get(i).nadando){
                    nadsBor.add(this.nads.get(i));
                }
            }
            System.out.println("\n Swimmers in the swimming pool: \n");
            for(Nadador n : nadsBor){
                System.out.println(n);
            }
            System.out.println("\n \n");
        }
    }
    public static class Nadador extends Thread{

        int id;
        tipo tipo;
        genero gender;
        int lineasOcupadas;
        int largosNadados=0;
        boolean nadando = false;
        Semaphore piscina;

        Nadador(int id, int lineasOcupadas, tipo tipo, genero gender, Semaphore piscina){
            this.id = id;
            this.lineasOcupadas = lineasOcupadas;
            this.tipo = tipo;
            this.gender =gender;
            this.piscina = piscina;
        }

        @Override
        public void run() {
            while(true){
                try {
                    this.piscina.acquire(lineasOcupadas);
                    this.nadando = true;
                    Thread.sleep(1000);
                    System.out.println("El "+ this.tipo + " está en el agua.");
                    this.largosNadados++;
                    this.piscina.release(lineasOcupadas);
                    System.out.println("El "+ this.tipo + " ha salido del agua." + "\n Ha nadado "+ this.largosNadados+ " largos.");
                    this.nadando=false;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public String toString() {
            return this.tipo + " , es "+ this.gender+ " y ha nadado " + this.largosNadados;
        }
    }

    enum tipo{
        nadador,
        submarinista
    }
    enum genero{
        hombre,
        ninio,
        ninia,
        mujer
    }
}