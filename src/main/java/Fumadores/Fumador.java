package Fumadores;

import java.util.ArrayList;
import java.util.Arrays;

public class Fumador extends Thread{
    private final int id;
    private final String ingrediente;
    private final Mesa m;
    private Agente a;

    Fumador(int id, String ingrediente, Mesa m){
        this.id=id;
        this.ingrediente = ingrediente;
        this.m = m;
    }


    @Override
    public void run() {
        while(true) {

            if(checkMesa()) {
                try {
                    intentarFumar();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void intentarFumar() throws InterruptedException {
        if(checkMesa()){
            System.out.println("\n El fumador "+ this.getId()+ " se ha liado un cigarrillo y esta fumando \n");
            sleep(1000);
        }
    }

    public synchronized boolean checkMesa(){
        String ing = this.ingrediente;
        ArrayList<String> ingsMesa = new ArrayList<>(Arrays.asList(m.ing2, m.ing1));
        int count=0;
        for(String ingMesa : ingsMesa){
            if(ingsMesa.contains(ing)){
                return false;
            }
        }
        return true;
    }



}
