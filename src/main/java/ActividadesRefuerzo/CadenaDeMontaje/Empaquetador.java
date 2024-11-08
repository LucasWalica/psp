package ActividadesRefuerzo.CadenaDeMontaje;

public class Empaquetador extends  Thread{

    private int numEmpaquetados = 0;
    private int tipoEmpaquetador;
    private CadenaDeMontaje c;
    public Empaquetador(int tipoEmpaquetador, CadenaDeMontaje c){
        this.tipoEmpaquetador = tipoEmpaquetador;
        this.c = c;
    }

    @Override
    public void run() {
        while(true){
            try {
                empaquetar();
                System.out.println("Se han colocado en total "+ this.numEmpaquetados+ " del producto "+ this.tipoEmpaquetador);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void empaquetar() throws InterruptedException {
        if(c.checkForProduct(tipoEmpaquetador)){
            System.out.println("El robot empaquetador tipo "+this.tipoEmpaquetador+ " esta empaquetando un producto");
            this.numEmpaquetados++;
            sleep(500);
            notifyAll();
        }else{
            sleep(1000);
        }
    }
}