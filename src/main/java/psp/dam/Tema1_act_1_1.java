package psp.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tema1_act_1_1 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("Programa para sumar dos numeros: ");
        double n1 = isNumeric();
        double n2 = isNumeric();
        in.close();

        System.out.println(n1+n2);
    }

    public static double isNumeric() throws IOException {
       while(true) {
           System.out.println(">");
           String n = in.readLine();
           try {
               return Double.parseDouble(n);
           } catch (NumberFormatException e) {
               System.out.println("Not a number.");
           }
       }
    }
}