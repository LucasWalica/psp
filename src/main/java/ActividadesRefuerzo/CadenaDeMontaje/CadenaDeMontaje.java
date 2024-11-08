package ActividadesRefuerzo.CadenaDeMontaje;

public class CadenaDeMontaje {
    private int n;
    public int[] cadena;

    public CadenaDeMontaje(int n){
        this.n=n;
        this.cadena = new int[n];
    }

    public void rellenarCadena(){
        for(int i=0; i<cadena.length; i++){
            cadena[i]=0;
        }
    }

    public synchronized int checkEmptySpace(){
        for(int i=0; i<this.cadena.length; i++){
            if(cadena[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public synchronized boolean checkForProduct(int productType){
        for(int i=0; i<this.cadena.length; i++){
            if(cadena[i]==productType){
                cadena[i]=0;
                return true;
            }
        }return false;
    }
}