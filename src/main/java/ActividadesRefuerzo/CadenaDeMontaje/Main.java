package ActividadesRefuerzo.CadenaDeMontaje;

public class Main {
    public static void main(String[] args) {
        CadenaDeMontaje c = new CadenaDeMontaje(10);
        c.rellenarCadena();
        Colocador colocador = new Colocador(c);
        Empaquetador e1 = new Empaquetador(1, c);
        Empaquetador e2 = new Empaquetador(2, c);
        Empaquetador e3 = new Empaquetador(3, c);
        colocador.start();
        e1.start();
        e2.start();
        e3.start();

    }
}
