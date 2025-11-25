package modulopsicologia.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
// Load application-mail.properties if present so mail settings are picked up without activating a profile
@PropertySource(value = "classpath:application-mail.properties", ignoreResourceNotFound = true)
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        String host = env.getProperty("spring.mail.host");
        String port = env.getProperty("spring.mail.port");
        String username = env.getProperty("spring.mail.username");
        String password = env.getProperty("spring.mail.password");

        if (host != null) mailSender.setHost(host);
        if (port != null) {
            try {
                mailSender.setPort(Integer.parseInt(port));
            } catch (NumberFormatException ex) {
                // ignore and let default
            }
        }
        if (username != null) mailSender.setUsername(username);
        if (password != null) mailSender.setPassword(password);

        Properties props = new Properties();
        // copy known mail.* properties from environment if present
        String[] keys = new String[]{"mail.smtp.auth","mail.smtp.starttls.enable","mail.smtp.starttls.required","mail.smtp.ssl.trust"};
        for (String k : keys) {
            String v = env.getProperty("spring.mail.properties." + k);
            if (v != null) props.put(k, v);
        }
        // sensible defaults for Gmail if not provided
        props.putIfAbsent("mail.smtp.auth", "true");
        props.putIfAbsent("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
