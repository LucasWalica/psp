package PinchaGlobos;

import java.util.ArrayList;

public class Container extends HiloInterrumpible{

    private ArrayList<Globo> deshinchados = new ArrayList<>();
    private ArrayList<Globo> hinchando = new ArrayList<>();
    private int maxGlobos;
    private int maxH;
    private int id;


    public synchronized void reponer(){
        while(maxGlobos==hinchando.size()+deshinchados.size()){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        Globo g  = new Globo(++id, 5);
        this.deshinchados.add(g);
        notifyAll();
    }


    public synchronized Globo getDesinchado(){
        while(deshinchados.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Globo g = deshinchados.removeFirst();
        hinchando.add(g);
        notifyAll();
        return g;
    }

    public synchronized Globo getHinchando(){
        while(deshinchados.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Globo g = hinchando.get((int)(Math.random()*hinchando.size()));
        notifyAll();
        return g;
    }

    @Override
    protected void tarea() {

    }

}