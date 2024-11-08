package ActividadesRefuerzo.TICTAC;

public class TICTAC {
    public static void main(String[] args) {
        Hilo Tic = new Hilo("TIC");
        Hilo Tac = new Hilo("TAC");

        Tic.start();
        Tac.start();
    }

    public static class Hilo extends Thread{
        private final String outPut;


        public Hilo(String outPut){
            this.outPut = outPut;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    sleep(1000);
                    System.out.println(outPut);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
