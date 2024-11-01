package PinchaGlobos;

public class Globo {
    private EstadoGlobo estado = EstadoGlobo.Deshinchado;
    private int volumenMaximo;
    private int volumen = 0;
    private int id;

    public Globo(int id, int volumenMaximo){
        this.id = id;
        this.volumenMaximo = volumenMaximo;
    }
    public synchronized void hinchar(){
        this.volumen++;
        if(volumen>volumenMaximo){
            estado=EstadoGlobo.explotado;
            System.out.println("Globo "+ this.id + " Explotado");
        }
    }
    public synchronized void pinchar(){
        volumen = 0;
        estado = EstadoGlobo.pinchado;
        System.out.println("Globo "+ this.id + " pinchado");
    }
}