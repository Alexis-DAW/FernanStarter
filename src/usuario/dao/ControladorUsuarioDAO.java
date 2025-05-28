package usuario.dao;

import usuario.Tipo;
import usuario.Usuario;
import usuario.UsuarioVista;
import utilidades.DAOManager;

import java.util.ArrayList;

import static utilidades.FuncionesVarias.logBBDD;

public class ControladorUsuarioDAO {

    private DAOUsuarioSQL modelo;
    private UsuarioVista vista;
    private DAOManager daoManager;
    private ArrayList<Usuario> listaUsuarios;

    public ControladorUsuarioDAO(DAOUsuarioSQL modelo, UsuarioVista vista, DAOManager daoManager){
        this.modelo = modelo;
        this.vista = vista;
        this.daoManager = daoManager;
        this.listaUsuarios = new ArrayList<>();
    }

    public void insert(Usuario usuario){
        if(modelo.insert(usuario, daoManager)){
            vista.operacionExitosa();
            logBBDD("Inserción", "usuario");
            listaUsuarios.add(usuario); // también lo añado a la lista local
        } else {
            vista.operacionErronea();
        }
    }

    public void delete(String correo){
        if(modelo.delete(correo, daoManager)){
            vista.operacionExitosa();
            logBBDD("Eliminación", "usuario");
            listaUsuarios.removeIf(u -> u.getCorreo().equalsIgnoreCase(correo));
        } else {
            vista.operacionErronea();
        }
    }

    public void update(Usuario usuario){
        if(modelo.update(usuario, daoManager)){
            vista.operacionExitosa();
            logBBDD("Actualización","usuario");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                    listaUsuarios.set(i, usuario);
                    break;
                }
            }
        } else {
            vista.operacionErronea();
        }
    }

    public void read(String correo){
        if(modelo.read(correo, daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "usuario");
        } else {
            vista.operacionErronea();
        }
    }

    public void readAll(){
        if(modelo.readAll(daoManager)!= null){
            vista.operacionExitosa();
            logBBDD("Acceso", "usuario");
        } else {
            vista.operacionErronea();
        }
    }

    public void cargarUsuarios(){
        ArrayList<Usuario> cargados = modelo.readAll(daoManager);
        if (cargados != null) {
            this.listaUsuarios = cargados;
            vista.operacionExitosa();
        } else {
            vista.operacionErronea();
        }
    }

    public void guardarUsuarios(ArrayList<Usuario> listaUsuarios){
        if (modelo.guardarUsuarios(listaUsuarios, daoManager)) {
            vista.operacionExitosa();
            this.listaUsuarios = listaUsuarios;
        } else {
            vista.operacionErronea();
        }
    }

    // MÉTODOS NUEVOS -----------------------------

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuarioPorNombre(String nombre) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) return u;
        }
        return null;
    }

    public String getContrasena(Usuario usuario){
        return usuario.getContrasena();
    }

    public void cambiarEstadoUsuario(String nombreUsuario){
        Usuario usuario = modelo.buscaPorNombre(nombreUsuario, daoManager);
        if (usuario == null) {
            System.err.println("❌ Error: Usuario no encontrado o error de conexión.");
            return;
        }

        if(usuario.estaBloqueado()) {
            modelo.desbloquearUsuario(usuario, daoManager);
            System.out.println("✅ Usuario desbloqueado");
        } else {
            modelo.bloquearUsuario(usuario, daoManager);
            System.out.println("✅ Usuario bloqueado");
        }
    }



}
