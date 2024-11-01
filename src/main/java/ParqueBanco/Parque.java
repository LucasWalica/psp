package ParqueBanco;

import java.util.ArrayList;

public class Parque extends Thread {
    Banco b;
    ArrayList<Biandante> biandantes;

    public Parque(Banco b, ArrayList<Biandante> biandates){
        this.b = b;
        this.biandantes = biandates;
    }

    @Override
    public void run() {
        for(Biandante b: biandantes){
            b.start();
        }
    }
}
