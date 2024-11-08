package ActividadesRefuerzo.Matriz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int numFilas = 1000;
        int numColumnas = 1000;
        int numHilos = 5;

        Matriz matriz = new Matriz(numFilas, numColumnas);
        matriz.rellenarMatriz();

        Acumulador acumulador = new Acumulador();

        long tiempoInicio = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(numHilos);

        int filasPorHilo = numFilas / numHilos;
        int filasRestantes = numFilas%numHilos;

        int filaActual = 0;
        for(int i=0; i<numHilos; i++){
            int filaAsignadas = filasPorHilo + (i<filasRestantes ? 1 : 0);
            int filaFin = filaActual + filaAsignadas;
            executorService.submit(new SumaHilo(matriz.getMatriz(), filaActual, filaFin, acumulador));
            filaActual = filaFin;
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){

        }
        long tiempoFin = System.currentTimeMillis();
        System.out.println("Suma total de la matriz: "+ acumulador.getSumaTotal());
        System.out.println("Tiempo de ejecución: "+ (tiempoFin- tiempoInicio) +" ms");
    }
}
