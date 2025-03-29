package utilidades;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class FuncionesCorreos {
    public static void autentificacionDeUsuario() {

        Scanner s = new Scanner(System.in);
        System.out.println("Verificación necesaria. Introduzca su correo electrónico");
        String correo = s.nextLine();

        int codigo = (int) (Math.random() * 8999 + 1000);
        String asunto = "Código de autentificación";
        String cuerpo = "<h1>Su código es el siguiente: <h1>" + codigo;

        enviarConGMail(correo, asunto, cuerpo);

        System.out.printf("Introduzca el código enviado a %s ", correo);
        int codigoUsuario = Integer.parseInt(s.nextLine());
        do {
            if (codigoUsuario == codigo) {
                System.out.println("Ha iniciado sesión correctamente.");
                break;
            } else {
                System.out.println("Código incorrecto. Por favor, introduzca el código recibido.");
                System.out.printf("Correo electrónico -> %s", correo);
                codigoUsuario = Integer.parseInt(s.nextLine());
            }
        } while (codigoUsuario != codigo);
    }

    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        final String remitente = "alexis.lopez.1506@fernando3martos.com";
        final String clave = "gphj dsbt qryu ugvs";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html; charset=utf-8");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}
