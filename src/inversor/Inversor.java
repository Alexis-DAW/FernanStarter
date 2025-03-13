package inversor;
import usuario.*;

public final class Inversor extends Usuario {

    private float saldo;

    public Inversor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo);
        this.saldo = 0;
    }

    public String toString() {
        return "Inversor " + super.getNombre() + "\nSaldo disponible: "+ saldo;
    }
}
