package BarberoDurmiente;

public class Clientes {
    private int id;
    public boolean siendoAtentido =  false;
    public boolean estaSentado = false;

    Clientes(int id){
        this.id = id;
    }

    public void atentiendo() throws InterruptedException {
        System.out.println("El cliente "+ this.id + " esta siendo atendido por el barbero");
        this.siendoAtentido=true;
    }

    public int getId(){
        return this.id;
    }

}