package cubahomes.services.registration;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cubahomes.model.Confirmacion;
import cubahomes.model.Usuario;
import cubahomes.services.bussines.ConfirmacionService;
import cubahomes.services.bussines.UsuarioService;
import cubahomes.services.mail.EmailSender;

@Service
public class RegistrationService {

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private EmailValidator emailValidator;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ConfirmacionService confirmacionService;
//	@Autowired
//	BCryptPasswordEncoder encoder;
	
	public String register(Usuario usuario) {
		
		boolean isValid = emailValidator.test(usuario.getContacto());
		if(!isValid) {
			throw new IllegalStateException(String.format("email %s no es valido", usuario.getContacto()));
		}
		
		this.signUp(usuario);
		System.out.println("regster");
//		return usuario.getConfirmacion().getToken();
		return "";
	}
	
	public void signUp(Usuario usuario) {
		
		Usuario usuario2 = usuarioService.findByContacto(usuario.getContacto());
		if(usuario2!=null) {
			throw new IllegalStateException(String.format("%s ya esta siendo usado por otro usuario", usuario2.getContacto()));
		}
		
		String token = UUID.randomUUID().toString();
		Date hasta = new Date();
		hasta.setTime(hasta.getTime() + 3600000);
		Confirmacion confirmacion = new Confirmacion(token, hasta);
//		usuario.setConfirmacion(confirmacion);
//		usuario.setContrasena(encoder.encode(usuario.getContrasena()));
		usuario.setContrasena(usuario.getContrasena());
		String link = "http://localhost:8080/app/api/confirm?token=" + token;
		
		usuarioService.save(usuario);
		emailSender.send(usuario.getContacto(), buildEmail(usuario.getNombreUsuario(), link));

	}
	
	@Transactional
	public String confirmToken(String token) {
		Date now = new Date();
		Confirmacion confirmacion = confirmacionService.findByToken(token);
		if(confirmacion == null)
			throw new IllegalStateException("No existe ningun contacto vinculado a este link de confirmacion");
		else if(confirmacion.getConfirmado() !=null)
			throw new IllegalStateException("El contacto ya ha sido confirmado");
		else if(now.after(confirmacion.getHasta()))
			throw new IllegalStateException("Tiempo de confirmacion expirado");
		
		confirmacion.setConfirmado(now);
		confirmacion.getUsuario().setHabilitado(true);
		confirmacionService.save(confirmacion);
		return "confirmado";
	}
	
	 private String buildEmail(String name, String link) {
	        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
	                "\n" +
	                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
	                "\n" +
	                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
	                "        \n" +
	                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	                "          <tbody><tr>\n" +
	                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
	                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td style=\"padding-left:10px\">\n" +
	                "                  \n" +
	                "                    </td>\n" +
	                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
	                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
	                "                    </td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "              </a>\n" +
	                "            </td>\n" +
	                "          </tr>\n" +
	                "        </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
	                "      <td>\n" +
	                "        \n" +
	                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
	                "                  <tbody><tr>\n" +
	                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
	                "                  </tr>\n" +
	                "                </tbody></table>\n" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table>\n" +
	                "\n" +
	                "\n" +
	                "\n" +
	                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
	                "    <tbody><tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
	                "        \n" +
	                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
	                "        \n" +
	                "      </td>\n" +
	                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
	                "    </tr>\n" +
	                "    <tr>\n" +
	                "      <td height=\"30\"><br></td>\n" +
	                "    </tr>\n" +
	                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
	                "\n" +
	                "</div></div>";
	    }
	
	
	
}
