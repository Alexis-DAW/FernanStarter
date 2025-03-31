package inversor;
import inversion.Inversion;
import usuario.*;

import java.util.ArrayList;

public final class Inversor extends Usuario {

    private float saldo;
    private ArrayList<Inversion> inversiones;

    public Inversor(String nombre, String contrasena, String correo) {
        super(nombre, contrasena, correo, Tipo.INVERSOR);
        this.saldo = 0;
        this.inversiones = new ArrayList<>();
    }

    public String toString() {
        return "Inversor " + super.getNombre() + "\nSaldo disponible: "+ saldo;
    }

    public void invertir(Inversion inversion) {
        inversiones.add(inversion);
    }

}
