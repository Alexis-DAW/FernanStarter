package inversor;
import inversion.Inversion;
import usuario.*;

public final class Inversor extends Usuario {

    private float saldo;
    private Inversion[] inversiones;
    private int numInversiones;

    public Inversor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo);
        this.saldo = 0;
        this.inversiones = new Inversion[10];
        this.numInversiones = 0;
    }

    public String toString() {
        return "Inversor " + super.getNombre() + "\nSaldo disponible: "+ saldo;
    }

    /* todo metodo para invertir
    public void invertir(Proyecto proyecto, double cantidad) {
    }
    */
}
