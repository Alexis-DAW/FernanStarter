package utilidades;
public final class FuncionesCadenas {

    //Comprueba que una contraseña creada cumpla con los requisitos de seguridad
    public static boolean fortalezaContrasena(String contrasena){
        if (contrasena.length()<8) return false;

        int contMinusculas=0;
        int contMayusculas=0;
        int contSimbolos=0;
        for (int i=0; i< contrasena.length(); i++){
            if (contrasena.charAt(i)=='+' || contrasena.charAt(i)=='-' || contrasena.charAt(i)=='_' ||
                    contrasena.charAt(i)=='*' || contrasena.charAt(i)=='.' || contrasena.charAt(i)==',' ||
                    contrasena.charAt(i)=='@' || contrasena.charAt(i)=='¡' || contrasena.charAt(i)=='!'){
                contSimbolos++;
            }else{
                if (contrasena.charAt(i)==contrasena.toUpperCase().charAt(i)) contMayusculas++;
                else contMinusculas++;
            }
        }

        if (contMinusculas > 0 && contMayusculas > 0 && contSimbolos > 0) return true;
        else return false;
    }

    //Compara que dos contraseñas sean iguales (para el inicio de sesión)
    public static boolean compararContrasenas(String contrasenaUsuario, String contrasenaUsuario2){
        for (int i=0; i< contrasenaUsuario.length(); i++){
            if (contrasenaUsuario.charAt(i)!=contrasenaUsuario2.charAt(i)) return false;
        }
        return true;
    }

    //Comprueba la longitud de un texto, se usa para las descripciones de los proyectos, por ejemplo
    public static boolean comprobarLongitud(String texto){
        if (texto.length()<5){
            System.out.println("Por favor, introduzca un mínimo de 5 carácteres");
            return false;
        }
        if (texto.length()>50){
            System.out.println("Por favor, introduzca un máximo de 50 carácteres");
            return false;
        }
        return true;
    }

    //Comprueba que haya un simbolo de euro en distintos campos, como cuando se invierte dinero
    public static boolean simboloMoneda(String cantidadIntroducida){
        if(!cantidadIntroducida.endsWith("€")){
            System.out.println("Usted debe introducir € al final de la cantidad)");
            return false;
        }
        return true;
    }
}
