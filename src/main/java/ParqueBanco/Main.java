package ParqueBanco;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el numero de asientos del banco: ");
        int capacidad = in.nextInt();
        Banco b = new Banco(capacidad);
        ArrayList<Biandante> biandantes = new ArrayList<Biandante>();
        System.out.println("Ingrese la cantidad de biandantes: ");
        int numBiandantes = in.nextInt();
        for(int i=0; i<numBiandantes; i++){
            biandantes.add(new Biandante(b));
        }
        Parque p = new Parque(b, biandantes);
        p.start();
    }
}
