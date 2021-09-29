package cubahomes.services.mail;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements EmailSender{

	@Autowired
	private JavaMailSender mailSender;
	private Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Override
	@Async
	public void send(String to, String email) {
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setSubject("Confirme su correo");
			helper.setTo(to);
			helper.setFrom("steve.rzag@gmail.com");
			mailSender.send(mimeMessage);
		}catch(MessagingException e) {
			LOGGER.error("Error al enviar el correo", e);
			throw new IllegalStateException(e);
		}
		
	}

}
