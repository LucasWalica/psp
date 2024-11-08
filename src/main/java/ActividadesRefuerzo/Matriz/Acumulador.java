package ActividadesRefuerzo.Matriz;

public class Acumulador extends Thread{


    private double sumaTotal = 0;

    public synchronized void acumular(double sumaParcial){
        sumaTotal+= sumaParcial;
    }

    public double getSumaTotal(){
        return sumaTotal;
    }

}