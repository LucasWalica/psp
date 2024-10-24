package psp.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Tema1_act_1_2 {
    public static void main(String[] args) throws IOException {
        try{
            Process compilar = Runtime.getRuntime().exec("javac Tema1_act_1_1.java");
            compilar.waitFor();
            Process jarCreate = Runtime.getRuntime().exec("jar cfe Tema1_act_1_1.jar psp.dam.Tema1_act_1_1 -C . psp");
            jarCreate.waitFor();
            Process proceso = Runtime.getRuntime().exec("java -jar Tema1_act_1_1.jar");

            try (OutputStream os = proceso.getOutputStream()) {
                String numero1 = "5";
                String numero2 = "10";
                os.write((numero1 + System.lineSeparator()).getBytes());
                os.flush();
                os.write((numero2 + System.lineSeparator()).getBytes());
                os.flush();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        System.out.println(linea);
                    }
                }
            }
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}