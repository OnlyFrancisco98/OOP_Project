package modulopsicologia.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import modulopsicologia.model.Cita;
import modulopsicologia.model.Paciente;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String fromAddress;

    public void enviarConfirmacionCita(Paciente paciente, Cita cita, String contrasenaTemporal){
        String to = paciente.getEmail();
        String subject = "Confirmación de cita";
        String html = buildConfirmacionHtml(paciente.getNombre(), cita.getHorario().getFechaHoraInicio().toString(), cita.getMotivoConsulta(), paciente.getEmail(), contrasenaTemporal);
        sendHtmlEmail(to, subject, html);
    }

    public void enviarReprogramacionCita(Paciente paciente, Cita cita, String contrasenaTemporal){
        String to = paciente.getEmail();
        String subject = "Reprogramación de cita";
        String html = buildReprogramacionHtml(paciente.getNombre(), cita.getHorario().getFechaHoraInicio().toString(), cita.getMotivoConsulta(), paciente.getEmail(), contrasenaTemporal);
        sendHtmlEmail(to, subject, html);
    }

    private void sendHtmlEmail(String to, String subject, String html){
        if (mailSender == null) {
            // Mail sender not configured — log and return without failing the operation
            log.warn("[EmailService] JavaMailSender no disponible — correo no enviado a: {} asunto: {}", to, subject);
            return;
        }

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(html, true);
            helper.setTo(to);
            helper.setSubject(subject);
            if (fromAddress != null && !fromAddress.isBlank()) {
                helper.setFrom(fromAddress);
            }
            mailSender.send(message);
        } catch (MessagingException e){
            // don't throw to avoid breaking the booking flow
            log.error("[EmailService] Error al enviar correo a {} : {}", to, e.getMessage(), e);
        }
        log.info("[EmailService] Envío (intento) completado para: {} asunto: {}", to, subject);
    }

    private String buildConfirmacionHtml(String nombre, String fecha, String motivo, String correo, String contrasena){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Hola ").append(nombre).append(",</p>");
        sb.append("<p>Tu cita se ha confirmado exitosamente.</p>");
        sb.append("<p>Fecha de la cita: <strong>").append(fecha).append("</strong></p>");
        sb.append("<p>Motivo de la consulta: <strong>").append(motivo == null ? "(no especificado)" : motivo).append("</strong></p>");
        sb.append("<p>Has ingresado con el siguiente correo: <strong>").append(correo).append("</strong></p>");
        sb.append("<p>Tu contraseña temporal para acceder a la plataforma es: <strong>").append(contrasena).append("</strong></p>");
        sb.append("<p>Por favor, utiliza esta contraseña para iniciar sesión.<br/>Gracias por confiar en nosotros.</p>");
        return sb.toString();
    }

    private String buildReprogramacionHtml(String nombre, String fecha, String motivo, String correo, String contrasena){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Hola ").append(nombre).append(",</p>");
        sb.append("<p>Tu cita ha sido reagendada exitosamente.</p>");
        sb.append("<p>Nueva fecha de la cita: <strong>").append(fecha).append("</strong></p>");
        sb.append("<p>Motivo de la consulta: <strong>").append(motivo == null ? "(no especificado)" : motivo).append("</strong></p>");
        sb.append("<p>Recuerda que tu correo de acceso es: <strong>").append(correo).append("</strong></p>");
        sb.append("<p>Tu contraseña temporal sigue siendo: <strong>").append(contrasena).append("</strong></p>");
        sb.append("<p>Si necesitas realizar más cambios, puedes hacerlo desde tu perfil en la plataforma.<br/>Gracias por tu preferencia.</p>");
        return sb.toString();
    }
}
