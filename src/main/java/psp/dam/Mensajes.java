package psp.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mensajes{

    public static void main(String[] args)  {
        Hilo hilo = new Hilo();
        Hilo hilo2 = new Hilo();
        hilo2.setName("lucas");
        hilo.setName("pepito");

        hilo.start();
        hilo2.start();
        // No usar metodo .run() directamente
    }
}