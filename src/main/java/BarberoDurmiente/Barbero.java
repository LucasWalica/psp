package BarberoDurmiente;

import java.util.ArrayList;

public class Barbero extends Thread {

    private boolean ocupado = false;
    private boolean durmiendo = false;
    private ArrayList<Clientes> clientes;
    private Sillas sillas;

    Barbero(ArrayList<Clientes> clientes, Sillas sillas){
        this.clientes = clientes;
        this.sillas = sillas;
    }

    @Override
    public void run() {
        try{
            while(true){
                trabajoBarbero();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void trabajoBarbero() throws InterruptedException{
        int counter = 0;
        while(true){
            if(llegaCliente()){
                this.clientes.add(new Clientes(counter));
                counter++;
                for(Clientes c: clientes){
                    if(this.sillas.ocupar()){
                        System.out.println("El cliente " + c.getId() + " se ha sentado");
                    }else{
                        clientes.remove(c);
                        System.out.println("El cliente "+ c.getId() + " se ha ido");
                    }
                }
                if(!this.ocupado && !this.durmiendo){
                    Clientes c = this.clientes.get(0);
                    System.out.println("El cliente "+ c.getId() + " esta siendo atendido por el barero");
                    this.clientes.remove(0);
                    this.ocupado=true;
                    this.sillas.liberarsilla();
                    sleep((int) Math.random()*3000);
                    this.ocupado=false;
                }
            }
            if(!this.ocupado && sillas.estanVacias()){
                this.durmiendo=true;
                System.out.println("El Barbero esta durmiendo");
                sleep((int) Math.random() * 3000);
                this.durmiendo=false;
            }
        }
    }
    public boolean llegaCliente(){
        int n = (int) Math.round(Math.random());
        if(n==1){
            return true;
        }else{
            return false;
        }
    }
}