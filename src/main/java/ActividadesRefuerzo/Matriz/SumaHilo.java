package ActividadesRefuerzo.Matriz;

public class SumaHilo implements  Runnable{
    private int[][] matriz;
    private int filaInicio;
    private int filaFin;
    private Acumulador acumulador;

    public SumaHilo(int[][] matriz, int filaInicio, int filaFin, Acumulador acumulador){
        this.matriz = matriz;
        this.filaInicio = filaInicio;
        this.filaFin = filaFin;
        this.acumulador = acumulador;
    }

    @Override
    public void run() {
        double sumaParcial = 0;
        for(int i= filaInicio; i<filaFin; i++){
            for(int j = 0; j<matriz[i].length; j++){
                sumaParcial += matriz[i][j];
            }
        }
        acumulador.acumular(sumaParcial);
    }
}
