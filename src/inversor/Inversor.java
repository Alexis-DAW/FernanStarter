package inversor;
import inversion.Inversion;
import usuario.*;
import java.io.Serializable;
import java.util.ArrayList;

public final class Inversor extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private double saldo;
    private ArrayList<Inversion> inversiones;

    public Inversor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo, Tipo.INVERSOR);
        this.saldo = 0;
        this.inversiones = new ArrayList<>();
    }

    public String toString() {
        return "Inversor " + super.getNombre() + "\nSaldo disponible: "+ saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Inversion> getInversiones() {
        return inversiones;
    }

    public void invertir(Inversion inversion) {
        inversiones.add(inversion);
    }

    public boolean recargarSaldo (double cantidad){
        if (cantidad > 0){
            saldo += cantidad;
            return true;
        }
        return false;
    }

    public boolean aumentaInversion(double cantidad, Inversion inversion) {
        return inversion.aumentaInversion(cantidad);
    }

    public boolean disminuyeInversion(double cantidad, Inversion inversion) {
        return inversion.disminuyeInversion(cantidad);
    }
}
