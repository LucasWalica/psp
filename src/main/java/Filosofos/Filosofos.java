package Filosofos;

public class Filosofos extends Thread{
    private int id;
    private Tenedores tenedorIzquierda;
    private Tenedores tenedorDerecha;
    public boolean sentado = false;
    private boolean tieneTenedores =false;
    private Sillas sillas;

    Filosofos(int id, Tenedores tenedorIzquierda, Tenedores tenedorDerecha,Sillas sillas){
        this.id = id;
        this.tenedorDerecha = tenedorDerecha;
        this.tenedorIzquierda = tenedorIzquierda;
        this.sillas = sillas;
    }

    public void tenedoresCogidos() {
        if (this.tenedorIzquierda.estaCogido && this.tenedorDerecha.estaCogido) {
            this.tieneTenedores = true;
        } else {
            this.tieneTenedores = false;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {

                    pensar();
                    comer();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("El filósofo " + id + " está pensando.");
        Thread.sleep((int) (Math.random() * 1000));
    }
    private void comer() throws InterruptedException {
        sillas.ocuparSilla();
        this.sentado = true;
        tenedorIzquierda.cogerTenedor();
        tenedorDerecha.cogerTenedor();
        System.out.println("El filósofo " + id + " está comiendo.");
        Thread.sleep((int) (Math.random() * 1000));
        tenedorDerecha.dejarTenedor();
        tenedorIzquierda.dejarTenedor();
        sillas.liberarSilla();
        this.sentado=false;
    }
}