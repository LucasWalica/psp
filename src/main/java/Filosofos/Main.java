package Filosofos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        Tenedores[] tenedores = new Tenedores[5];
        Filosofos[] filosofos = new Filosofos[5];
        Sillas sillas = new Sillas();

        for(int i=0; i< tenedores.length; i++) {
            tenedores[i] = new Tenedores(i);
        }

        filosofos[0] = new Filosofos(0, tenedores[0], tenedores[1], sillas);
        filosofos[1] = new Filosofos(1, tenedores[1], tenedores[2], sillas);
        filosofos[2] = new Filosofos(2, tenedores[2], tenedores[3], sillas);
        filosofos[3] = new Filosofos(3, tenedores[3], tenedores[4], sillas);
        filosofos[4] = new Filosofos(4, tenedores[4], tenedores[0], sillas);

        for(Filosofos f: filosofos){
            f.start();
        }
    }
}