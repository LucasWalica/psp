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

        System.out.println("Ingrese el numero de buzos: ");
        int nBuzos = in.nextInt();
        System.out.println("Ingrese el numero de nadadores: ");
        int nNadadores = in.nextInt();

        ExecutorService buzos = Executors.newFixedThreadPool(nBuzos);
        for(int i=0; i<nBuzos; i++){
            buzos.submit(new Nadador(i, 2, tipo.submarinista, generos.get((int)(Math.random()*generos.size())), piscina));
        }
        ExecutorService nadadores = Executors.newFixedThreadPool(nNadadores);
        for(int i=0; i<nNadadores; i++){
            nadadores.submit(new Nadador(i, 1, tipo.nadador, generos.get((int)(Math.random()*generos.size())), piscina));
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