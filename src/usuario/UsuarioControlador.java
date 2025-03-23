package usuario;


public class UsuarioControlador {
    GestionUsuarios modelo;
    UsuarioVista vista;

    public UsuarioControlador(GestionUsuarios modelo, UsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void muestraUsuarios(){
        vista.mostrarUsuarios(modelo.getUsuarios());

    }

    public void bloquearUsuario(String nombre){
        if(modelo.bloquearUsuario(nombre)) vista.operacionExitosa();
        else vista.operacionErronea();
    }

    public void desbloquearUsuario(String nombre){
        if(modelo.desbloquearUsuario(nombre)) vista.operacionExitosa();
        else vista.operacionErronea();
    }
}
