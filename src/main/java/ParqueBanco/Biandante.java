package ParqueBanco;

public class Biandante extends Thread {

    Banco b;
    public Biandante(Banco b){
        this.b=b;
    }


    @Override
    public void run() {
        while(true){
            try {
                pasear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sentarse();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void pasear() throws InterruptedException {
        System.out.println("El biandante "+ this.getName() + " está paseando" );
        sleep((int) ((Math.random()*2000)+1000));
    }

    private synchronized void sentarse() throws  InterruptedException{
        if(b.getCapacidad()>0) {
            b.ocuparSitio();
            System.out.println("El biandante " + this.getName() + " esta sentado");
            System.out.println("el banco tiene " + this.b.getCapacidad());
            sleep((int) ((Math.random() * 600) + 100));
            b.lenvantarse();
            System.out.println("El biandante "+ this.getName() + " se ha levantado");
            notifyAll();
        }else{
            System.out.println("El biandante "+ this.getName() + " está esperando un sitio libre...");
            System.out.println("el banco tiene " + this.b.getCapacidad());
            wait();
        }
    }
}
