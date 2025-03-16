package usuario;
public final class GestionUsuarios {
    private Usuario[] usuarios;
    private int numUsuarios;

    public GestionUsuarios(int max) {
        usuarios = new Usuario[max];
        numUsuarios = 0;
    }

    public int getNumUsuarios() { return numUsuarios; }


    public String toString() {
        String cadena = "";
        for (int i=0; i<numUsuarios; i++){
            cadena += (usuarios[i]+"\n");
        }
        return cadena;
    }

    public boolean agregarUsuario(Usuario usuario){
        if (numUsuarios < usuarios.length){
            usuarios[numUsuarios++] = usuario;
            return true;
        }
        return false;
    }

    public int buscarUsuario(String nombreUsuario){
        for (int i = 0; i < numUsuarios; i++){
            if (usuarios[i].getNombre().equals(nombreUsuario)) return i;
        }
        return -1;
    }

    public boolean eliminarUsuario(String nombreUsuario){
        int posicion= buscarUsuario(nombreUsuario);
        if (posicion>=0 ){
            usuarios[posicion] = null;
            numUsuarios--;
            return true;
        }
        return false;
    }

    public Usuario devuelveUsuario(String nombreUsuario){
        for (int i = 0; i < numUsuarios; i++){
            if (usuarios[i].getNombre().equals(nombreUsuario)) return usuarios[i];
        }
        return null;
    }

}
