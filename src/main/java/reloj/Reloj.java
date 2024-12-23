package reloj;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// reloj 1 y reloj 2 -> reloj dos no runnable

public class Reloj extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JLabel hora;

    public Reloj() {
        super("Reloj");
        hora = new JLabel(formatter.format(LocalDateTime.now()));
        hora.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(70, 70, 70, 70),
                hora.getBorder()
        ));
        hora.setFont(hora.getFont().deriveFont(30f));
        hora.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(hora);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void run() {
        Runnable actualizarHora = new Runnable() {
            public void run() {
                hora.setText(formatter.format(LocalDateTime.now()));
            }
        };
        while (true) {
            SwingUtilities.invokeLater(actualizarHora);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void iniciar() {
        setVisible(true);
        new Thread(this, "segundero").start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Reloj().iniciar();
            }
        });
    }
}