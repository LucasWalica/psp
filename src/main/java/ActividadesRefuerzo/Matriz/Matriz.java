package ActividadesRefuerzo.Matriz;

import java.util.Random;

public class Matriz {

    private int sumaTotal = 0;
    private int numFilas;
    private int numCols;
    private int[][] matriz;

    public Matriz(int numCols, int numFilas){
        this.numCols = numCols;
        this.numFilas = numFilas;
        this.matriz = new int[numFilas][numCols];
    }

    public void rellenarMatriz(){
        Random r = new Random();
        for(int i=0; i<this.matriz.length; i++){
            for(int j=0; j<this.matriz[i].length; j++){
                this.matriz[i][j] = r.nextInt(1000);
            }
        }
    }

    public int[][] getMatriz(){
        return this.matriz;
    }

    public int getNumFilas(){
        return numFilas;
    }
}

